package com.kirilbezmen.trafficscotland.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kirilbezmen.trafficscotland.R;

/**
 * Detail destination and argument contract. Populated from parsed model data
 * once the source XML is represented as Java objects.
 */
public class TrafficDetailsFragment extends Fragment {

    private static final String ARG_ID = "situation_id";

    public TrafficDetailsFragment() {
        super(R.layout.fragment_message);
    }

    public static TrafficDetailsFragment newInstance(String situationId) {
        TrafficDetailsFragment fragment = new TrafficDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID, situationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id = getArguments() == null ? "" : getArguments().getString(ARG_ID, "");
        ((TextView) view.findViewById(R.id.messageTitle)).setText(R.string.details);
        ((TextView) view.findViewById(R.id.messageBody))
                .setText(getString(R.string.details_pending, id));
    }
}
