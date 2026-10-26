package com.kirilbezmen.trafficscotland.ui;

import android.os.Bundle;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kirilbezmen.trafficscotland.R;
import com.kirilbezmen.trafficscotland.data.model.TrafficLocation;
import com.kirilbezmen.trafficscotland.data.model.TrafficRecord;
import com.kirilbezmen.trafficscotland.data.model.TrafficSituation;

import java.util.Locale;

/** Shows original DATEX II fields, clearly marking unavailable optional data. */
public class TrafficDetailsFragment extends Fragment {
    private static final String ARG_ID = "situation_id";
    public TrafficDetailsFragment() { super(R.layout.fragment_traffic_details); }

    public static TrafficDetailsFragment newInstance(String situationId) {
        TrafficDetailsFragment fragment = new TrafficDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID, situationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle saved) {
        super.onViewCreated(view, saved);
        String id = getArguments() == null ? "" : getArguments().getString(ARG_ID, "");
        TrafficViewModel model = new ViewModelProvider(requireActivity()).get(TrafficViewModel.class);
        view.findViewById(R.id.detailsBack).setOnClickListener(
                v -> requireActivity().getOnBackPressedDispatcher().onBackPressed());
        model.getSnapshot().observe(getViewLifecycleOwner(), data -> {
            TrafficSituation item = model.findById(id);
            if (item == null) {
                ((TextView) view.findViewById(R.id.detailsSummary))
                        .setText(data.loading ? R.string.loading : R.string.no_situations);
                return;
            }
            ((TextView) view.findViewById(R.id.detailsRoad)).setText(
                    item.getRoadNames().isEmpty() ? getString(R.string.unknown_road)
                            : String.join(", ", item.getRoadNames()));
            ((TextView) view.findViewById(R.id.detailsSummary)).setText(item.getSummary());
            ((TextView) view.findViewById(R.id.detailsType)).setText(item.getType());
            ((TextView) view.findViewById(R.id.detailsSource)).setText(data.message);
            SpannableStringBuilder body = new SpannableStringBuilder();
            for (TrafficRecord record : item.getRecords()) {
                field(body, "Record", record.getId());
                field(body, "Event subtype", record.getSubtype());
                field(body, "Validity", record.getValidityStatus());
                field(body, "Start", record.getValidFrom());
                field(body, "End", record.getValidTo());
                field(body, "Last updated", record.getUpdatedAt());
                field(body, "Direction", record.getDirection());
                field(body, "Delay type", record.getDelaysType());
                if (record.getRestrictedLanes() != null)
                    field(body, "Restricted lanes", String.valueOf(record.getRestrictedLanes()));
                if (record.getOperationalLanes() != null)
                    field(body, "Operational lanes", String.valueOf(record.getOperationalLanes()));
                for (TrafficLocation point : record.getLocations()) {
                    field(body, "Location (" + point.getEndpoint() + ")",
                            String.join("; ", point.getNames()));
                    if (point.hasCoordinates()) {
                        field(body, "Coordinates (" + point.getEndpoint() + ")",
                                String.format(Locale.UK, "%.6f, %.6f",
                                        point.getLatitude(), point.getLongitude()));
                    }
                }
            }
            ((TextView) view.findViewById(R.id.detailsBody)).setText(body);
        });
    }

    private void field(SpannableStringBuilder body, String label, String value) {
        if (value == null || value.trim().isEmpty()) return;
        int from = body.length();
        body.append(label);
        body.setSpan(new StyleSpan(Typeface.BOLD), from, body.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        body.setSpan(new ForegroundColorSpan(0xFF0B2D5B), from, body.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        body.append("\n").append(value.trim()).append("\n\n");
    }
}
