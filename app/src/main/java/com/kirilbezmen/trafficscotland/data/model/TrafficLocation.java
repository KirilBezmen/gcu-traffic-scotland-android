package com.kirilbezmen.trafficscotland.data.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** A DATEX II from/to location, with optional coordinates and descriptors. */
public final class TrafficLocation {
    private final String endpoint;
    private Double latitude;
    private Double longitude;
    private final ArrayList<String> names = new ArrayList<>();
    private final ArrayList<String> roadDescriptors = new ArrayList<>();

    public TrafficLocation(String endpoint) { this.endpoint = endpoint; }
    public String getEndpoint() { return endpoint; }
    public Double getLatitude() { return latitude; }
    public Double getLongitude() { return longitude; }
    public boolean hasCoordinates() { return latitude != null && longitude != null; }
    public List<String> getNames() { return Collections.unmodifiableList(names); }
    public List<String> getRoadDescriptors() { return Collections.unmodifiableList(roadDescriptors); }

    public void setLatitude(Double latitude) {
        if (latitude != null && latitude >= -90 && latitude <= 90) this.latitude = latitude;
    }
    public void setLongitude(Double longitude) {
        if (longitude != null && longitude >= -180 && longitude <= 180) this.longitude = longitude;
    }
    public void addName(String value) { addUnique(names, value); }
    public void addRoadDescriptor(String value) { addUnique(roadDescriptors, value); }

    private static void addUnique(ArrayList<String> values, String value) {
        if (value != null && !value.trim().isEmpty() && !values.contains(value.trim())) {
            values.add(value.trim());
        }
    }
}
