package com.kirilbezmen.trafficscotland.data.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Fields from a DATEX II situationRecord. Missing XML elements stay null/empty. */
public final class TrafficRecord {
    private final String id;
    private final String recordType;
    private String subtype = "";
    private String generalPublicComment = "";
    private String createdAt = "";
    private String updatedAt = "";
    private String validFrom = "";
    private String validTo = "";
    private String validityStatus = "";
    private String delaysType = "";
    private String direction = "";
    private Integer restrictedLanes;
    private Integer operationalLanes;
    private final ArrayList<TrafficLocation> locations = new ArrayList<>();

    public TrafficRecord(String id, String recordType) {
        this.id = id == null ? "" : id;
        this.recordType = recordType == null ? "" : recordType;
    }
    public String getId() { return id; }
    public String getRecordType() { return recordType; }
    public String getSubtype() { return subtype; }
    public String getGeneralPublicComment() { return generalPublicComment; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public String getValidFrom() { return validFrom; }
    public String getValidTo() { return validTo; }
    public String getValidityStatus() { return validityStatus; }
    public String getDelaysType() { return delaysType; }
    public String getDirection() { return direction; }
    public Integer getRestrictedLanes() { return restrictedLanes; }
    public Integer getOperationalLanes() { return operationalLanes; }
    public List<TrafficLocation> getLocations() { return Collections.unmodifiableList(locations); }

    public void setSubtype(String x) { subtype = safe(x); }
    public void setGeneralPublicComment(String x) { generalPublicComment = safe(x); }
    public void setCreatedAt(String x) { createdAt = safe(x); }
    public void setUpdatedAt(String x) { updatedAt = safe(x); }
    public void setValidFrom(String x) { validFrom = safe(x); }
    public void setValidTo(String x) { validTo = safe(x); }
    public void setValidityStatus(String x) { validityStatus = safe(x); }
    public void setDelaysType(String x) { delaysType = safe(x); }
    public void setDirection(String x) { direction = safe(x); }
    public void setRestrictedLanes(Integer x) { restrictedLanes = x; }
    public void setOperationalLanes(Integer x) { operationalLanes = x; }
    public void addLocation(TrafficLocation x) { if (x != null) locations.add(x); }
    private static String safe(String x) { return x == null ? "" : x.trim(); }
}
