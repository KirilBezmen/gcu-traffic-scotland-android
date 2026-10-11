package com.kirilbezmen.trafficscotland.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kirilbezmen.trafficscotland.R;

/** Placeholder for an approved mapping implementation; no map is implemented yet. */
public class TrafficMapFragment extends Fragment {

    public TrafficMapFragment() {
        super(R.layout.fragment_message);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.messageTitle)).setText(R.string.map);
        ((TextView) view.findViewById(R.id.messageBody)).setText(R.string.map_pending);
    }
}
