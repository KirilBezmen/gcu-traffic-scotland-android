package com.kirilbezmen.trafficscotland.util;

import static org.junit.Assert.*;

import com.kirilbezmen.trafficscotland.data.model.TrafficLocation;
import com.kirilbezmen.trafficscotland.data.model.TrafficRecord;
import com.kirilbezmen.trafficscotland.data.model.TrafficSituation;

import org.junit.Test;

public class TrafficDistanceTest {
    @Test public void referencePointIsZeroDistance() {
        assertEquals(0.0, TrafficDistance.kilometres(
                55.8642, -4.2518, 55.8642, -4.2518), 0.00001);
    }
    @Test public void farAwayRecordIsExcludedByReasonableRadius() {
        TrafficSituation item = new TrafficSituation("fixed", TrafficSituation.Feed.UNPLANNED_EVENTS);
        TrafficRecord record = new TrafficRecord("record", "Test");
        TrafficLocation point = new TrafficLocation("to");
        point.setLatitude(56.8);
        point.setLongitude(-4.2);
        record.addLocation(point);
        item.addRecord(record);
        assertTrue(TrafficDistance.nearestKm(item) > 25);
        assertTrue(TrafficDistance.nearestKm(item) < 200);
    }
    @Test public void roadCrossingGlasgowIsNearbyEvenWithDistantEndpoints() {
        assertEquals(0.0, TrafficDistance.toSegmentKm(55.8642, -4.2518,
                56.0, -4.2518, 55.7, -4.2518), 0.1);
    }
    @Test public void missingLocationsHaveInfiniteDistance() {
        TrafficSituation item = new TrafficSituation("empty", TrafficSituation.Feed.UNPLANNED_EVENTS);
        assertEquals(Double.POSITIVE_INFINITY, TrafficDistance.nearestKm(item), 0.0);
    }
}
