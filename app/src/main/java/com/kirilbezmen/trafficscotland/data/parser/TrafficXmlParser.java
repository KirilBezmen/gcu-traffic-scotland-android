package com.kirilbezmen.trafficscotland.data.parser;

import com.kirilbezmen.trafficscotland.data.model.TrafficLocation;
import com.kirilbezmen.trafficscotland.data.model.TrafficRecord;
import com.kirilbezmen.trafficscotland.data.model.TrafficSituation;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Parses an official DATEX II downloaded XML String in the Android app using PullParser.
 * Each parser instance is local, so background jobs do not share mutable parser state.
 */
public final class TrafficXmlParser {
    private static final String XSI = "http://www.w3.org/2001/XMLSchema-instance";

    public ArrayList<TrafficSituation> parse(String downloadedXml, TrafficSituation.Feed feed)
            throws XmlPullParserException, IOException {
        if (downloadedXml == null || downloadedXml.trim().isEmpty()) {
            throw new XmlPullParserException("Empty Traffic Scotland XML response");
        }
        if (feed == null) throw new IllegalArgumentException("Feed category is required");

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xml = factory.newPullParser();
        xml.setInput(new StringReader(downloadedXml));
        ArrayList<TrafficSituation> situations = new ArrayList<>();

        int event = xml.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG && "situation".equals(xml.getName())) {
                situations.add(readSituation(xml, feed));
            }
            event = xml.next();
        }
        return situations;
    }

    private TrafficSituation readSituation(XmlPullParser xml, TrafficSituation.Feed feed)
            throws IOException, XmlPullParserException {
        TrafficSituation situation = new TrafficSituation(xml.getAttributeValue(null, "id"), feed);
        while (xml.next() != XmlPullParser.END_DOCUMENT) {
            if (xml.getEventType() == XmlPullParser.END_TAG && "situation".equals(xml.getName())) break;
            if (xml.getEventType() != XmlPullParser.START_TAG) continue;
            if ("situationRecord".equals(xml.getName())) {
                String recordType = xml.getAttributeValue(XSI, "type");
                situation.addRecord(readRecord(xml, xml.getAttributeValue(null, "id"), recordType));
            } else {
                skipSubtree(xml); // situation metadata and unknown situation-level tags
            }
        }
        return situation;
    }

    private TrafficRecord readRecord(XmlPullParser xml, String id, String type)
            throws IOException, XmlPullParserException {
        TrafficRecord record = new TrafficRecord(id, type);
        while (xml.next() != XmlPullParser.END_DOCUMENT) {
            if (xml.getEventType() == XmlPullParser.END_TAG && "situationRecord".equals(xml.getName())) break;
            if (xml.getEventType() != XmlPullParser.START_TAG) continue;
            switch (xml.getName()) {
                case "generalPublicComment": record.setGeneralPublicComment(readPublicComment(xml)); break;
                case "validity": readValidity(xml, record); break;
                case "impact": readImpact(xml, record); break;
                case "groupOfLocations": readLocations(xml, record); break;
                case "situationRecordCreationTime": record.setCreatedAt(xml.nextText()); break;
                case "situationRecordVersionTime": record.setUpdatedAt(xml.nextText()); break;
                case "roadMaintenanceType":
                case "roadOrCarriagewayOrLaneManagementType":
                case "abnormalTrafficType": record.setSubtype(xml.nextText()); break;
                default: skipSubtree(xml); // unknown future DATEX fields
            }
        }
        return record;
    }

    private String readPublicComment(XmlPullParser xml)
            throws IOException, XmlPullParserException {
        StringBuilder comment = new StringBuilder();
        while (xml.next() != XmlPullParser.END_DOCUMENT) {
            if (xml.getEventType() == XmlPullParser.END_TAG && "generalPublicComment".equals(xml.getName())) break;
            if (xml.getEventType() != XmlPullParser.START_TAG || !"value".equals(xml.getName())) continue;
            String lang = xml.getAttributeValue(null, "lang");
            String value = xml.nextText().trim();
            if (!value.isEmpty() && (lang == null || lang.toLowerCase(java.util.Locale.ROOT).startsWith("en"))) {
                if (comment.length() > 0) comment.append("; ");
                comment.append(value);
            }
        }
        return comment.toString();
    }

    private void readValidity(XmlPullParser xml, TrafficRecord record)
            throws IOException, XmlPullParserException {
        while (xml.next() != XmlPullParser.END_DOCUMENT) {
            if (xml.getEventType() == XmlPullParser.END_TAG && "validity".equals(xml.getName())) break;
            if (xml.getEventType() != XmlPullParser.START_TAG) continue;
            switch (xml.getName()) {
                case "validityStatus": record.setValidityStatus(xml.nextText()); break;
                case "overallStartTime": record.setValidFrom(xml.nextText()); break;
                case "overallEndTime": record.setValidTo(xml.nextText()); break;
                default: break; // traverse validityTimeSpecification
            }
        }
    }

    private void readImpact(XmlPullParser xml, TrafficRecord record)
            throws IOException, XmlPullParserException {
        while (xml.next() != XmlPullParser.END_DOCUMENT) {
            if (xml.getEventType() == XmlPullParser.END_TAG && "impact".equals(xml.getName())) break;
            if (xml.getEventType() != XmlPullParser.START_TAG) continue;
            switch (xml.getName()) {
                case "delaysType": record.setDelaysType(xml.nextText()); break;
                case "numberOfLanesRestricted": record.setRestrictedLanes(parseInteger(xml.nextText())); break;
                case "numberOfOperationalLanes": record.setOperationalLanes(parseInteger(xml.nextText())); break;
                default: break; // traverse delays etc.
            }
        }
    }

    private void readLocations(XmlPullParser xml, TrafficRecord record)
            throws IOException, XmlPullParserException {
        while (xml.next() != XmlPullParser.END_DOCUMENT) {
            if (xml.getEventType() == XmlPullParser.END_TAG && "groupOfLocations".equals(xml.getName())) break;
            if (xml.getEventType() != XmlPullParser.START_TAG) continue;
            switch (xml.getName()) {
                case "tpegDirection": record.setDirection(xml.nextText()); break;
                case "from":
                case "to": record.addLocation(readPoint(xml, xml.getName())); break;
                default: break; // traverse tpegLinearLocation
            }
        }
    }

    private TrafficLocation readPoint(XmlPullParser xml, String endpoint)
            throws IOException, XmlPullParserException {
        TrafficLocation location = new TrafficLocation(endpoint);
        while (xml.next() != XmlPullParser.END_DOCUMENT) {
            if (xml.getEventType() == XmlPullParser.END_TAG && endpoint.equals(xml.getName())) break;
            if (xml.getEventType() != XmlPullParser.START_TAG) continue;
            switch (xml.getName()) {
                case "pointCoordinates": readCoordinates(xml, location); break;
                case "name":
                case "otherName": readLabels(xml, xml.getName(), location, false); break;
                case "ilc": readLabels(xml, "ilc", location, true); break;
                default: break;
            }
        }
        return location;
    }

    private void readCoordinates(XmlPullParser xml, TrafficLocation location)
            throws IOException, XmlPullParserException {
        while (xml.next() != XmlPullParser.END_DOCUMENT) {
            if (xml.getEventType() == XmlPullParser.END_TAG && "pointCoordinates".equals(xml.getName())) break;
            if (xml.getEventType() != XmlPullParser.START_TAG) continue;
            switch (xml.getName()) {
                case "latitude": location.setLatitude(parseDouble(xml.nextText())); break;
                case "longitude": location.setLongitude(parseDouble(xml.nextText())); break;
                default: break;
            }
        }
    }

    private void readLabels(XmlPullParser xml, String closingTag, TrafficLocation location, boolean road)
            throws IOException, XmlPullParserException {
        while (xml.next() != XmlPullParser.END_DOCUMENT) {
            if (xml.getEventType() == XmlPullParser.END_TAG && closingTag.equals(xml.getName())) break;
            if (xml.getEventType() != XmlPullParser.START_TAG || !"value".equals(xml.getName())) continue;
            String label = xml.nextText();
            if (road) location.addRoadDescriptor(label);
            else location.addName(label);
        }
    }

    /** Skips a whole unknown element without consuming its parent's closing tag. */
    private void skipSubtree(XmlPullParser xml) throws IOException, XmlPullParserException {
        int depth = 1;
        while (depth > 0) {
            int event = xml.next();
            if (event == XmlPullParser.END_DOCUMENT) break;
            if (event == XmlPullParser.START_TAG) depth++;
            if (event == XmlPullParser.END_TAG) depth--;
        }
    }

    private Integer parseInteger(String raw) {
        try { return Integer.valueOf(raw.trim()); }
        catch (NumberFormatException e) { return null; }
    }
    private Double parseDouble(String raw) {
        try { return Double.valueOf(raw.trim()); }
        catch (NumberFormatException e) { return null; }
    }
}
