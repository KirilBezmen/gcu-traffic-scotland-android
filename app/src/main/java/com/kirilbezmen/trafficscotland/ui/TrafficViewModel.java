package com.kirilbezmen.trafficscotland.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kirilbezmen.trafficscotland.data.model.TrafficSituation;
import com.kirilbezmen.trafficscotland.data.repository.TrafficRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Shared, orientation-retained UI state for one Activity and all its Fragments. */
public class TrafficViewModel extends AndroidViewModel {
    public enum Section { HOME, INCIDENTS, ROADWORKS, MAP }

    private final TrafficRepository repository;
    private Section section = Section.HOME;
    private String incidentSearch = "";
    private String roadworksSearch = "";

    public TrafficViewModel(@NonNull Application app) {
        super(app);
        repository = new TrafficRepository(app);
        repository.start();
    }
    public LiveData<TrafficRepository.Snapshot> getSnapshot() { return repository.getSnapshot(); }
    public void refresh() { repository.refresh(); }

    public Section getSection() { return section; }
    public void setSection(Section value) { if (value != null) section = value; }

    public void restoreSection(String saved) {
        if (saved == null) return;
        try { section = Section.valueOf(saved); }
        catch (IllegalArgumentException ignored) { section = Section.HOME; }
    }

    public String getSearch(Section section) {
        return section == Section.ROADWORKS ? roadworksSearch : incidentSearch;
    }
    public void setSearch(Section section, String query) {
        if (section == Section.ROADWORKS) roadworksSearch = query == null ? "" : query;
        if (section == Section.INCIDENTS) incidentSearch = query == null ? "" : query;
    }

    /** Road ID is matched as a complete route number; descriptive text is not used as a live fact. */
    public List<TrafficSituation> filtered(Section section, TrafficRepository.Snapshot snapshot) {
        List<TrafficSituation> values = section == Section.ROADWORKS
                ? snapshot.roadworks : snapshot.incidents;
        String query = getSearch(section).trim().toUpperCase(Locale.ROOT);
        if (query.isEmpty()) return values;
        ArrayList<TrafficSituation> filtered = new ArrayList<>();
        for (TrafficSituation situation : values) {
            if (situation.matchesRoad(query)) filtered.add(situation);
        }
        return filtered;
    }

    public TrafficSituation findById(String id) {
        TrafficRepository.Snapshot snapshot = getSnapshot().getValue();
        if (snapshot == null || id == null) return null;
        for (TrafficSituation s : snapshot.incidents) if (id.equals(s.getId())) return s;
        for (TrafficSituation s : snapshot.roadworks) if (id.equals(s.getId())) return s;
        return null;
    }

    @Override protected void onCleared() {
        repository.stop();
        super.onCleared();
    }
}
