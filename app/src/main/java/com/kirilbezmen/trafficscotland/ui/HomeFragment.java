package com.kirilbezmen.trafficscotland.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kirilbezmen.trafficscotland.R;

/**
 * Home is a fixed-height native fragment. MainActivity already provides all
 * navigation, so this screen deliberately has no duplicate Explore buttons.
 */
public class HomeFragment extends Fragment {
    public HomeFragment() { super(R.layout.fragment_home); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TrafficViewModel model =
                new ViewModelProvider(requireActivity()).get(TrafficViewModel.class);

        view.findViewById(R.id.homeRefresh).setOnClickListener(v -> model.refresh());

        model.getSnapshot().observe(getViewLifecycleOwner(), snapshot -> {
            TextView status = view.findViewById(R.id.homeStatus);
            TextView counts = view.findViewById(R.id.homeCounts);
            if (snapshot.loading) {
                status.setText(R.string.loading);
                counts.setText(R.string.loading);
            } else {
                status.setText(snapshot.message);
                counts.setText(getString(R.string.home_counts,
                        snapshot.incidents.size(), snapshot.roadworks.size()));
            }
        });
    }
}
