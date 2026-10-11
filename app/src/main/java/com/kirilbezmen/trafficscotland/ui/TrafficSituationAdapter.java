package com.kirilbezmen.trafficscotland.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.kirilbezmen.trafficscotland.R;
import com.kirilbezmen.trafficscotland.data.model.TrafficRecord;
import com.kirilbezmen.trafficscotland.data.model.TrafficSituation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** A simple efficient native ListView adapter (no unapproved external frameworks). */
public final class TrafficSituationAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final ArrayList<TrafficSituation> data = new ArrayList<>();
    public TrafficSituationAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }
    public void submit(@NonNull List<TrafficSituation> items) {
        data.clear();
        data.addAll(items);
        notifyDataSetChanged();
    }
    @Override public int getCount() { return data.size(); }
    @Override public TrafficSituation getItem(int index) { return data.get(index); }
    @Override public long getItemId(int index) { return index; }

    private static int iconFor(TrafficRecord record, boolean works) {
        if (works) return R.drawable.ic_cone;
        if (record == null) return R.drawable.ic_warning;
        String kind = (record.getSubtype() + " " + record.getRecordType()).toLowerCase(Locale.ROOT);
        if (kind.contains("roadclosed") || kind.contains("carriagewayclosed"))
            return R.drawable.ic_road_closed;
        if (kind.contains("queuing") || kind.contains("congestion"))
            return R.drawable.ic_queue;
        if (kind.contains("laneclosed") || kind.contains("laneclosure"))
            return R.drawable.ic_lane_closed;
        if (kind.contains("weather") || kind.contains("snow") || kind.contains("ice"))
            return R.drawable.ic_weather;
        if (kind.contains("obstruction") || kind.contains("debris"))
            return R.drawable.ic_obstruction;
        return R.drawable.ic_warning;
    }

    private static String humanType(String value) {
        if (value == null || value.isEmpty()) return "Traffic information";
        String spaced = value.replaceAll("([a-z])([A-Z])", "$1 $2");
        return Character.toUpperCase(spaced.charAt(0)) + spaced.substring(1);
    }

    @Override public View getView(int index, View reusable, ViewGroup parent) {
        View row = reusable == null
                ? inflater.inflate(R.layout.row_traffic_situation, parent, false)
                : reusable;
        TrafficSituation situation = getItem(index);
        TrafficRecord record = situation.getRecords().isEmpty()
                ? null : situation.getRecords().get(0);
        String roads = String.join(", ", situation.getRoadNames());

        ((TextView) row.findViewById(R.id.rowRoad)).setText(
                roads.isEmpty() ? row.getContext().getString(R.string.unknown_road) : roads);
        ((TextView) row.findViewById(R.id.rowDescription)).setText(situation.getSummary());
        ((TextView) row.findViewById(R.id.rowType)).setText(
                record == null ? situation.getType() :
                        humanType(!record.getSubtype().isEmpty() ? record.getSubtype() : record.getRecordType()));
        ImageView icon = row.findViewById(R.id.rowIcon);
        boolean works = situation.getFeed() == TrafficSituation.Feed.CURRENT_ROADWORKS;
        icon.setImageResource(iconFor(record, works));

        TextView delay = row.findViewById(R.id.rowDelay);
        if (works) {
            String type = record == null ? "" : record.getDelaysType();
            if (type.equalsIgnoreCase("veryLongDelays") || type.equalsIgnoreCase("longDelays")) {
                delay.setText(R.string.long_delay);
                delay.setTextColor(Color.parseColor("#B42318"));
            } else if (type.equalsIgnoreCase("delays")) {
                delay.setText(R.string.reported_delay);
                delay.setTextColor(Color.parseColor("#B45309"));
            } else if (type.equalsIgnoreCase("noDelay")) {
                delay.setText(R.string.no_delay);
                delay.setTextColor(Color.parseColor("#087F5B"));
            } else {
                delay.setText(R.string.delay_unknown);
                delay.setTextColor(Color.parseColor("#64748B"));
            }
        } else {
            delay.setText(R.string.incident_label);
            delay.setTextColor(Color.parseColor("#B42318"));
        }
        return row;
    }
}
