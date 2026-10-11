package com.kirilbezmen.trafficscotland.data.parser;

import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.kirilbezmen.trafficscotland.data.model.TrafficRecord;
import com.kirilbezmen.trafficscotland.data.model.TrafficSituation;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/** Full lecturer-supplied samples: the same XML bytes used in the DEBUG UI. */
@RunWith(AndroidJUnit4.class)
public class OfficialXmlFixturesTest {
    private String fixture(String name) throws Exception {
        try (InputStream input = InstrumentationRegistry.getInstrumentation()
                .getTargetContext().getAssets().open(name)) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int n;
            while ((n = input.read(buffer)) != -1) output.write(buffer, 0, n);
            return output.toString(StandardCharsets.UTF_8.name());
        }
    }

    @Test public void originalIncidentsContainFourSituations() throws Exception {
        ArrayList<TrafficSituation> items = new TrafficXmlParser().parse(
                fixture("sample_unplanned.xml"), TrafficSituation.Feed.UNPLANNED_EVENTS);
        assertEquals(4, items.size());
        for (TrafficSituation s : items) {
            assertFalse(s.getId().isEmpty());
            assertFalse(s.getRecords().isEmpty());
            assertFalse(s.getSummary().isEmpty());
            TrafficRecord r = s.getRecords().get(0);
            assertFalse(r.getValidFrom().isEmpty());
            assertEquals("", r.getValidTo()); // optional in supplied incidents
            assertEquals(2, r.getLocations().size());
            assertTrue(r.getLocations().get(0).hasCoordinates());
        }
    }

    @Test public void originalRoadworksContain405Situations() throws Exception {
        ArrayList<TrafficSituation> items = new TrafficXmlParser().parse(
                fixture("sample_roadworks.xml"), TrafficSituation.Feed.CURRENT_ROADWORKS);
        assertEquals(405, items.size());
        int withCoordinates = 0;
        for (TrafficSituation s : items) {
            assertFalse(s.getRecords().isEmpty());
            TrafficRecord r = s.getRecords().get(0);
            assertFalse(r.getValidFrom().isEmpty());
            assertFalse(r.getValidTo().isEmpty());
            assertFalse(s.getSummary().isEmpty());
            if (r.getLocations().size() == 2 && r.getLocations().get(0).hasCoordinates()) {
                withCoordinates++;
            }
        }
        assertTrue("Coordinates should be extracted from official records", withCoordinates > 390);
    }
}
