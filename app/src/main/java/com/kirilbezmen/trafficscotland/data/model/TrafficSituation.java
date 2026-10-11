package com.kirilbezmen.trafficscotland.data.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** One <situation> and its one or more nested DATEX II situationRecords. */
public final class TrafficSituation {
    public enum Feed { UNPLANNED_EVENTS, CURRENT_ROADWORKS }

    // Full identifiers, not substring matching (M8 must not match M80).
    private static final Pattern ROAD_ID = Pattern.compile(
            "(?<![A-Za-z0-9])(?:M|A|B)\\d{1,4}(?:\\([A-Za-z]\\))?(?![A-Za-z0-9])",
            Pattern.CASE_INSENSITIVE);
    private final String id;
    private final Feed feed;
    private final ArrayList<TrafficRecord> records = new ArrayList<>();

    public TrafficSituation(String id, Feed feed) {
        this.id = id == null ? "" : id;
        this.feed = feed;
    }
    public String getId() { return id; }
    public Feed getFeed() { return feed; }
    public List<TrafficRecord> getRecords() { return Collections.unmodifiableList(records); }
    public void addRecord(TrafficRecord record) { if (record != null) records.add(record); }
    public String getSummary() {
        for (TrafficRecord record : records) {
            if (!record.getGeneralPublicComment().isEmpty()) return record.getGeneralPublicComment();
        }
        return "Description unavailable";
    }
    public String getType() { return records.isEmpty() ? "" : records.get(0).getRecordType(); }
    public Set<String> getRoadNames() {
        LinkedHashSet<String> names = new LinkedHashSet<>();
        for (TrafficRecord record : records) {
            for (TrafficLocation location : record.getLocations()) {
                for (String label : location.getRoadDescriptors()) addRoadIds(names, label);
                for (String label : location.getNames()) addRoadIds(names, label);
            }
            // Some situations omit road codes in locations: description is a fallback.
            addRoadIds(names, record.getGeneralPublicComment());
        }
        return Collections.unmodifiableSet(names);
    }
    public boolean matchesRoad(String query) {
        if (query == null || query.trim().isEmpty()) return false;
        String target = query.trim().replaceAll("\\s+", "").toUpperCase(Locale.ROOT);
        return getRoadNames().contains(target);
    }
    private static void addRoadIds(Set<String> names, String text) {
        Matcher matcher = ROAD_ID.matcher(text);
        while (matcher.find()) names.add(matcher.group().toUpperCase(Locale.ROOT));
    }
}
