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
                        (!record.getSubtype().isEmpty() ? record.getSubtype() : record.getRecordType()));
        ImageView icon = row.findViewById(R.id.rowIcon);
        boolean works = situation.getFeed() == TrafficSituation.Feed.CURRENT_ROADWORKS;
        icon.setImageResource(works ? R.drawable.ic_cone : R.drawable.ic_warning);

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
