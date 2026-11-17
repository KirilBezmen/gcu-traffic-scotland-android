package com.kirilbezmen.trafficscotland.data.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kirilbezmen.trafficscotland.BuildConfig;
import com.kirilbezmen.trafficscotland.data.model.TrafficSituation;
import com.kirilbezmen.trafficscotland.data.parser.TrafficXmlParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Official-source DATEX II fetch + parsing. Always downloads on a worker thread.
 * In DEBUG builds without credentials, labelled lecturer XML fixtures enable UI tests.
 * RELEASE builds never silently replace live data with bundled historic samples.
 */
public final class TrafficRepository {
    private static final long REFRESH_MILLIS = 60 * 60 * 1000L;
    private static final String INCIDENT_URL =
            "https://datex2.trafficscotland.org/rest/2.3/publications/UnplannedEvents/Content.xml";
    // CurrentRoadworks URL must be copied from the authorised DATEX II guide.
    // Never silently guess an alternative provider or endpoint.
    private static final String CACHE_INCIDENTS = "last_live_unplanned.xml";
    private static final String CACHE_ROADWORKS = "last_live_roadworks.xml";

    public static final class Snapshot {
        public final List<TrafficSituation> incidents;
        public final List<TrafficSituation> roadworks;
        public final String source;
        public final String message;
        public final long retrievedAt;
        public final boolean loading;

        private Snapshot(List<TrafficSituation> incidents, List<TrafficSituation> roadworks,
                         String source, String message, long retrievedAt, boolean loading) {
            this.incidents = Collections.unmodifiableList(new ArrayList<>(incidents));
            this.roadworks = Collections.unmodifiableList(new ArrayList<>(roadworks));
            this.source = source;
            this.message = message;
            this.retrievedAt = retrievedAt;
            this.loading = loading;
        }

        public static Snapshot empty() {
            return new Snapshot(Collections.emptyList(), Collections.emptyList(),
                    "NONE", "Waiting for data", 0, true);
        }
    }

    private final Application app;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final MutableLiveData<Snapshot> observable = new MutableLiveData<>(Snapshot.empty());
    private final AtomicBoolean working = new AtomicBoolean(false);
    private boolean started;
    private final Runnable periodic = new Runnable() {
        @Override public void run() {
            refresh();
            mainHandler.postDelayed(this, REFRESH_MILLIS);
        }
    };

    public TrafficRepository(Application application) { this.app = application; }
    public LiveData<Snapshot> getSnapshot() { return observable; }

    public void start() {
        if (started) return;
        started = true;
        refresh();
        mainHandler.postDelayed(periodic, REFRESH_MILLIS);
    }

    public void refresh() {
        if (!working.compareAndSet(false, true)) return;

        // Display a real loading state without throwing away already verified
        // LIVE, CACHED or clearly labelled historic SAMPLE results.
        Snapshot previous = observable.getValue();
        if (previous == null) previous = Snapshot.empty();
        String message = "NONE".equals(previous.source)
                ? "Loading traffic information…"
                : "Refreshing… " + previous.message;
        Snapshot loading = new Snapshot(previous.incidents, previous.roadworks,
                previous.source, message, previous.retrievedAt, true);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            observable.setValue(loading);
        } else {
            mainHandler.post(() -> observable.setValue(loading));
        }
        executor.execute(() -> {
            try {
                Snapshot next;
                if (hasCredentials()) {
                    next = loadLive();
                } else if (BuildConfig.DEBUG) {
                    next = loadSamples();
                } else {
                    next = new Snapshot(Collections.emptyList(), Collections.emptyList(), "NONE",
                            "Live data requires Traffic Scotland API approval and local credentials.",
                            0L, false);
                }
                observable.postValue(next);
            } catch (Exception error) {
                Snapshot cached = loadCached(error);
                observable.postValue(cached);
            } finally {
                working.set(false);
            }
        });
    }

    private boolean hasCredentials() {
        return !BuildConfig.TRAFFIC_CLIENT_ID.trim().isEmpty()
                && !BuildConfig.TRAFFIC_CLIENT_KEY.trim().isEmpty()
                && !BuildConfig.TRAFFIC_ROADWORKS_URL.trim().isEmpty();
    }

    private Snapshot loadLive() throws Exception {
        String incidentsXml = download(INCIDENT_URL);
        String roadworksXml = download(BuildConfig.TRAFFIC_ROADWORKS_URL);
        TrafficXmlParser parser = new TrafficXmlParser();
        ArrayList<TrafficSituation> incidents = parser.parse(
                incidentsXml, TrafficSituation.Feed.UNPLANNED_EVENTS);
        ArrayList<TrafficSituation> roadworks = parser.parse(
                roadworksXml, TrafficSituation.Feed.CURRENT_ROADWORKS);
        if (incidents.isEmpty() && roadworks.isEmpty()) {
            throw new IOException("Both official XML streams were empty");
        }
        // Only save a pair after both authenticated downloads and parses succeed.
        save(CACHE_INCIDENTS, incidentsXml);
        save(CACHE_ROADWORKS, roadworksXml);
        return new Snapshot(incidents, roadworks, "LIVE", "Official Traffic Scotland data",
                System.currentTimeMillis(), false);
    }

    private Snapshot loadSamples() throws IOException, XmlPullParserException {
        String incidents = readAsset("sample_unplanned.xml");
        String roadworks = readAsset("sample_roadworks.xml");
        TrafficXmlParser parser = new TrafficXmlParser();
        return new Snapshot(
                parser.parse(incidents, TrafficSituation.Feed.UNPLANNED_EVENTS),
                parser.parse(roadworks, TrafficSituation.Feed.CURRENT_ROADWORKS),
                "SAMPLE", "Historic lecturer XML samples — NOT current traffic conditions",
                0, false);
    }

    private Snapshot loadCached(Exception failure) {
        try {
            TrafficXmlParser parser = new TrafficXmlParser();
            ArrayList<TrafficSituation> incidents = parser.parse(
                    readSaved(CACHE_INCIDENTS), TrafficSituation.Feed.UNPLANNED_EVENTS);
            ArrayList<TrafficSituation> roadworks = parser.parse(
                    readSaved(CACHE_ROADWORKS), TrafficSituation.Feed.CURRENT_ROADWORKS);
            return new Snapshot(incidents, roadworks, "CACHED",
                    "Offline: showing last successfully downloaded Traffic Scotland data",
                    0, false);
        } catch (Exception noCache) {
            return new Snapshot(Collections.emptyList(), Collections.emptyList(),
                    "ERROR", "Traffic data unavailable. " + concise(failure), 0, false);
        }
    }

    private String concise(Exception error) {
        return error.getMessage() == null ? error.getClass().getSimpleName()
                : error.getMessage();
    }

    private String download(String endpoint) throws IOException {
        if (!endpoint.startsWith("https://datex2.trafficscotland.org/")) {
            throw new IOException("The configured URL is not the permitted DATEX II host");
        }
        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(25000);
        connection.setRequestProperty("Accept", "application/xml");
        String credentials = BuildConfig.TRAFFIC_CLIENT_ID + ":" + BuildConfig.TRAFFIC_CLIENT_KEY;
        // The lecturer starter sends Base64(clientId:clientKey), without a Basic prefix.
        connection.setRequestProperty("Authorization",
                Base64.encodeToString(credentials.getBytes(StandardCharsets.UTF_8), Base64.NO_WRAP));
        try {
            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                throw new IOException("Traffic Scotland HTTP " + status);
            }
            try (InputStream stream = connection.getInputStream()) {
                return readUtf8(stream);
            }
        } finally {
            connection.disconnect();
        }
    }

    private String readAsset(String filename) throws IOException {
        try (InputStream stream = app.getAssets().open(filename)) {
            return readUtf8(stream);
        }
    }
    private String readSaved(String filename) throws IOException {
        try (FileInputStream stream = app.openFileInput(filename)) {
            return readUtf8(stream);
        }
    }
    private void save(String filename, String value) throws IOException {
        File temp = new File(app.getFilesDir(), filename + ".tmp");
        File target = new File(app.getFilesDir(), filename);
        try (FileOutputStream out = new FileOutputStream(temp)) {
            out.write(value.getBytes(StandardCharsets.UTF_8));
            out.getFD().sync();
        }
        if (!temp.renameTo(target)) {
            throw new IOException("Cannot save verified XML cache");
        }
    }
    private static String readUtf8(InputStream stream) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] chunk = new byte[8192];
        int n;
        while ((n = stream.read(chunk)) != -1) output.write(chunk, 0, n);
        return output.toString(StandardCharsets.UTF_8.name());
    }

    public void stop() {
        mainHandler.removeCallbacks(periodic);
        executor.shutdownNow();
    }
}
