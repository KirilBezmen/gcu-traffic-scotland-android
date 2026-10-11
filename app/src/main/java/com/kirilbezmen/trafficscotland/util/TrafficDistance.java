package com.kirilbezmen.trafficscotland.util;

import com.kirilbezmen.trafficscotland.data.model.TrafficLocation;
import com.kirilbezmen.trafficscotland.data.model.TrafficRecord;
import com.kirilbezmen.trafficscotland.data.model.TrafficSituation;

/** Radius search about a permitted fixed reference: Glasgow city centre. */
public final class TrafficDistance {
    public static final double GLASGOW_LAT = 55.8642;
    public static final double GLASGOW_LON = -4.2518;
    private static final double EARTH_KM = 6371.0;

    private TrafficDistance() {}

    public static double nearestKm(TrafficSituation situation) {
        double nearest = Double.POSITIVE_INFINITY;
        for (TrafficRecord record : situation.getRecords()) {
            TrafficLocation previous = null;
            for (TrafficLocation point : record.getLocations()) {
                if (!point.hasCoordinates()) continue;
                nearest = Math.min(nearest, kilometres(
                        GLASGOW_LAT, GLASGOW_LON, point.getLatitude(), point.getLongitude()));
                // A situation usually covers a from/to line, not just two unrelated pins.
                if (previous != null) nearest = Math.min(nearest, toSegmentKm(
                        GLASGOW_LAT, GLASGOW_LON,
                        previous.getLatitude(), previous.getLongitude(),
                        point.getLatitude(), point.getLongitude()));
                previous = point;
            }
        }
        return nearest;
    }

    public static double kilometres(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.pow(Math.sin(dLon / 2), 2);
        return 2.0 * EARTH_KM * Math.asin(Math.min(1.0, Math.sqrt(a)));
    }

    /** Short traffic-road segments approximated on local tangent plane around user centre. */
    public static double toSegmentKm(double lat, double lon,
                                     double aLat, double aLon, double bLat, double bLon) {
        double scale = EARTH_KM * Math.cos(Math.toRadians(lat));
        double ax = Math.toRadians(aLon - lon) * scale;
        double ay = Math.toRadians(aLat - lat) * EARTH_KM;
        double bx = Math.toRadians(bLon - lon) * scale;
        double by = Math.toRadians(bLat - lat) * EARTH_KM;
        double dx = bx - ax, dy = by - ay;
        double denom = dx * dx + dy * dy;
        if (denom <= 1e-12) return Math.hypot(ax, ay);
        double t = Math.max(0.0, Math.min(1.0, -(ax * dx + ay * dy) / denom));
        return Math.hypot(ax + t * dx, ay + t * dy);
    }
}
