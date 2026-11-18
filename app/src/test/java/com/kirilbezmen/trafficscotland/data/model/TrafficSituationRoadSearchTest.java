package com.kirilbezmen.trafficscotland.data.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Rubric: exact road search, avoiding substring matches such as M8 within M80.
 * These are plain JVM tests and therefore execute in GitHub Actions.
 */
public class TrafficSituationRoadSearchTest {

    private TrafficSituation situation(String roadText) {
        TrafficSituation value = new TrafficSituation(
                "example", TrafficSituation.Feed.UNPLANNED_EVENTS);
        TrafficRecord record = new TrafficRecord("record", "RoadOrCarriagewayOrLaneManagement");
        TrafficLocation location = new TrafficLocation("to");
        location.addRoadDescriptor(roadText);
        record.addLocation(location);
        value.addRecord(record);
        return value;
    }

    @Test public void exactMotorwayRouteDoesNotMatchLongerRoute() {
        TrafficSituation m80 = situation("M80 J1 to J2");
        assertTrue(m80.matchesRoad("M80"));
        assertFalse(m80.matchesRoad("M8"));
        assertFalse(m80.matchesRoad("M800"));
        assertTrue(situation("M8 J11").matchesRoad("M8"));
        assertFalse(situation("M8 J11").matchesRoad("M80"));
    }

    @Test public void queriesAreCaseInsensitiveAndTrimWhitespace() {
        TrafficSituation m8 = situation("M8 motorway (westbound)");
        assertTrue(m8.matchesRoad("m8"));
        assertTrue(m8.matchesRoad(" M8 "));
        assertTrue(m8.matchesRoad("m 8"));
        assertFalse(m8.matchesRoad("A8"));
        assertFalse(m8.matchesRoad(""));
    }

    @Test public void suffixedRoutesAndPunctuationRemainDistinct() {
        TrafficSituation a74m = situation("A74(M), junction 19");
        assertTrue(a74m.matchesRoad("A74(M)"));
        assertTrue(a74m.matchesRoad("a74(m)"));
        assertFalse(a74m.matchesRoad("A74"));
        assertFalse(a74m.matchesRoad("A7"));
    }

    @Test public void commentFallbackIncludesRoutesAcrossMultipleRecords() {
        TrafficSituation value = new TrafficSituation(
                "two-records", TrafficSituation.Feed.CURRENT_ROADWORKS);
        TrafficRecord one = new TrafficRecord("first", "MaintenanceWorks");
        one.setGeneralPublicComment("Roadworks on M8 westbound");
        TrafficRecord two = new TrafficRecord("second", "MaintenanceWorks");
        two.setGeneralPublicComment("Closure affecting A82");
        value.addRecord(one);
        value.addRecord(two);

        assertTrue(value.matchesRoad("M8"));
        assertTrue(value.matchesRoad("A82"));
        assertFalse(value.matchesRoad("M80"));
        assertFalse(value.matchesRoad("A8"));
    }
}
