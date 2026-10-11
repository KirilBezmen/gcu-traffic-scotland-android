package com.kirilbezmen.trafficscotland.util;

import com.kirilbezmen.trafficscotland.data.model.TrafficLocation;
import com.kirilbezmen.trafficscotland.data.model.TrafficRecord;
import com.kirilbezmen.trafficscotland.data.model.TrafficSituation;

/** Distance search from an allowed fixed reference point: Glasgow city centre. */
public final class TrafficDistance {
    public static final double GLASGOW_LAT = 55.8642;
    public static final double GLASGOW_LON = -4.2518;
    private static final double EARTH_KM = 6371.0;

    private TrafficDistance() {}

    public static double nearestKm(TrafficSituation situation) {
        double closest = Double.POSITIVE_INFINITY;
        for (TrafficRecord record : situation.getRecords()) {
            for (TrafficLocation point : record.getLocations()) {
                if (point.hasCoordinates()) {
                    double km = kilometres(GLASGOW_LAT, GLASGOW_LON,
                            point.getLatitude(), point.getLongitude());
                    closest = Math.min(closest, km);
                }
            }
        }
        return closest;
    }

    public static double kilometres(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.pow(Math.sin(dLon / 2), 2);
        return 2.0 * EARTH_KM * Math.asin(Math.min(1.0, Math.sqrt(a)));
    }
}
