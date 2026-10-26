package com.kirilbezmen.trafficscotland.data.parser;

import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.kirilbezmen.trafficscotland.data.model.TrafficRecord;
import com.kirilbezmen.trafficscotland.data.model.TrafficSituation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.xmlpull.v1.XmlPullParserException;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class TrafficXmlParserTest {
    // Small representative DATEX II fragments; no network or client credentials needed.
    private static final String HEADER =
            "<d2LogicalModel xmlns='http://datex2.eu/schema/2/2_0' " +
            "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>";
    private static final String FOOTER = "</d2LogicalModel>";

    @Test
    public void incidentCommentImpactAndTwoPointsAreExtracted() throws Exception {
        String xml = HEADER +
                "<situation id='I_20260810_248_S'><headerInformation><informationStatus>real" +
                "</informationStatus></headerInformation>" +
                "<situationRecord id='I_20260810_248' xsi:type='RoadOrCarriagewayOrLaneManagement'>" +
                "<validity><validityTimeSpecification><overallStartTime>2026-08-10T11:37:42+01:00" +
                "</overallStartTime></validityTimeSpecification></validity>" +
                "<impact><numberOfOperationalLanes>0</numberOfOperationalLanes></impact>" +
                "<generalPublicComment><comment><values><value lang='en'>A74(M) slip closed" +
                "</value></values></comment></generalPublicComment>" +
                "<groupOfLocations><tpegLinearLocation><tpegDirection>southBound</tpegDirection>" +
                "<to><pointCoordinates><latitude>55.065432</latitude><longitude>-3.273135" +
                "</longitude></pointCoordinates><ilc><descriptor><values><value lang='en'>A74(M)" +
                "</value></values></descriptor></ilc></to>" +
                "<from><pointCoordinates><latitude>55.103477</latitude><longitude>-3.349595" +
                "</longitude></pointCoordinates></from></tpegLinearLocation></groupOfLocations>" +
                "</situationRecord></situation>" + FOOTER;

        ArrayList<TrafficSituation> situations = new TrafficXmlParser().parse(
                xml, TrafficSituation.Feed.UNPLANNED_EVENTS);
        assertEquals(1, situations.size());
        TrafficSituation situation = situations.get(0);
        assertEquals("I_20260810_248_S", situation.getId());
        assertTrue(situation.matchesRoad("a74(m)"));
        assertTrue(situation.getSummary().contains("slip closed"));
        assertEquals("RoadOrCarriagewayOrLaneManagement", situation.getType());
        TrafficRecord record = situation.getRecords().get(0);
        assertEquals("southBound", record.getDirection());
        assertEquals(Integer.valueOf(0), record.getOperationalLanes());
        assertEquals("", record.getValidTo()); // incident has no end time
        assertEquals(2, record.getLocations().size());
        assertEquals(55.065432, record.getLocations().get(0).getLatitude(), 0.000001);
        assertEquals(-3.349595, record.getLocations().get(1).getLongitude(), 0.000001);
    }

    @Test
    public void roadworksDurationAndDelayCanBeParsed() throws Exception {
        String xml = HEADER + "<situation id='D_159301_S'>" +
                "<situationRecord id='D_159301' xsi:type='MaintenanceWorks'>" +
                "<validity><validityTimeSpecification><overallStartTime>2023-07-22T00:00:00+01:00" +
                "</overallStartTime><overallEndTime>2027-12-31T23:59:00+00:00</overallEndTime>" +
                "</validityTimeSpecification></validity>" +
                "<impact><delays><delaysType>longDelays</delaysType></delays></impact>" +
                "<generalPublicComment><comment><values><value lang='en'>M8 WB closure</value>" +
                "</values></comment></generalPublicComment>" +
                "<groupOfLocations><tpegLinearLocation><to><name><descriptor><values>" +
                "<value lang='en'>M8</value></values></descriptor></name></to></tpegLinearLocation>" +
                "</groupOfLocations><roadMaintenanceType>roadworks</roadMaintenanceType>" +
                "</situationRecord></situation>" + FOOTER;

        TrafficSituation situation = new TrafficXmlParser().parse(
                xml, TrafficSituation.Feed.CURRENT_ROADWORKS).get(0);
        assertEquals("MaintenanceWorks", situation.getType());
        assertTrue(situation.matchesRoad("M8"));
        assertFalse(situation.matchesRoad("M80"));
        TrafficRecord record = situation.getRecords().get(0);
        assertEquals("longDelays", record.getDelaysType());
        assertEquals("roadworks", record.getSubtype());
        assertEquals("2027-12-31T23:59:00+00:00", record.getValidTo());
    }

    @Test(expected = XmlPullParserException.class)
    public void emptyDownloadIsNotSilentlyAccepted() throws Exception {
        new TrafficXmlParser().parse("", TrafficSituation.Feed.UNPLANNED_EVENTS);
    }

    @Test
    public void unknownFieldsAndMissingOptionalElementsDoNotCrash() throws Exception {
        String xml = HEADER + "<situation id='test'><headerInformation><future>new tag" +
                "</future></headerInformation><situationRecord id='r'><unknown>" +
                "<nested>value</nested></unknown><generalPublicComment><comment>" +
                "<values><value lang='en'>M80 restriction</value></values></comment>" +
                "</generalPublicComment></situationRecord></situation>" + FOOTER;
        ArrayList<TrafficSituation> data = new TrafficXmlParser().parse(
                xml, TrafficSituation.Feed.UNPLANNED_EVENTS);
        assertEquals(1, data.size());
        assertTrue(data.get(0).matchesRoad("M80"));
        assertFalse(data.get(0).matchesRoad("M8"));
        assertEquals(0, data.get(0).getRecords().get(0).getLocations().size());
    }

    /**
     * A partially downloaded official DATEX II document must not be silently
     * accepted as an empty or incomplete list of live events.
     */
    @Test(expected = XmlPullParserException.class)
    public void truncatedXmlIsRejected() throws Exception {
        String incomplete = HEADER + "<situation id='partial'><situationRecord id='record'>" +
                "<generalPublicComment><comment><values><value lang='en'>M8 works";
        new TrafficXmlParser().parse(incomplete, TrafficSituation.Feed.CURRENT_ROADWORKS);
    }

    /** Multiple records of one situation remain grouped in the model. */
    @Test
    public void multipleSituationRecordsAreRetainedWithSeparateValues() throws Exception {
        String xml = HEADER + "<situation id='multiple'>" +
                "<situationRecord id='r1' xsi:type='MaintenanceWorks'>" +
                "<generalPublicComment><comment><values><value lang='en'>M8 lane works</value>" +
                "</values></comment></generalPublicComment>" +
                "<impact><delays><delaysType>longDelays</delaysType></delays></impact>" +
                "</situationRecord>" +
                "<situationRecord id='r2' xsi:type='MaintenanceWorks'>" +
                "<generalPublicComment><comment><values><value lang='en'>M80 bridge inspection</value>" +
                "</values></comment></generalPublicComment>" +
                "<impact><delays><delaysType>delays</delaysType></delays></impact>" +
                "</situationRecord></situation>" + FOOTER;
        ArrayList<TrafficSituation> result = new TrafficXmlParser().parse(
                xml, TrafficSituation.Feed.CURRENT_ROADWORKS);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getRecords().size());
        assertEquals("r1", result.get(0).getRecords().get(0).getId());
        assertEquals("longDelays", result.get(0).getRecords().get(0).getDelaysType());
        assertEquals("r2", result.get(0).getRecords().get(1).getId());
        assertEquals("delays", result.get(0).getRecords().get(1).getDelaysType());
        assertTrue(result.get(0).matchesRoad("M8"));
        assertTrue(result.get(0).matchesRoad("M80"));
    }

    /** Optional impact, coordinates and validity must stay absent, not invented. */
    @Test
    public void missingOptionalValuesRemainAbsent() throws Exception {
        String xml = HEADER + "<situation id='minimal'>" +
                "<situationRecord id='bare' xsi:type='MaintenanceWorks'>" +
                "<generalPublicComment><comment><values><value lang='en'>A9 roadworks</value>" +
                "</values></comment></generalPublicComment>" +
                "</situationRecord></situation>" + FOOTER;
        TrafficSituation situation = new TrafficXmlParser().parse(
                xml, TrafficSituation.Feed.CURRENT_ROADWORKS).get(0);
        TrafficRecord record = situation.getRecords().get(0);
        assertEquals("", record.getValidFrom());
        assertEquals("", record.getValidTo());
        assertEquals("", record.getDelaysType());
        assertNull(record.getOperationalLanes());
        assertTrue(record.getLocations().isEmpty());
        assertEquals("A9 roadworks", situation.getSummary());
        assertTrue(situation.matchesRoad("A9"));
        assertFalse(situation.matchesRoad("A90"));
    }

}
