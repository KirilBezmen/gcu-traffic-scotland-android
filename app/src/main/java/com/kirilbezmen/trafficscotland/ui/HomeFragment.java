package com.kirilbezmen.trafficscotland.ui;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kirilbezmen.trafficscotland.R;

/** Native Home, both orientation layouts, with explicit source provenance. */
public class HomeFragment extends Fragment {
    public HomeFragment() { super(R.layout.fragment_home); }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
        super.onViewCreated(view, state);
        TrafficViewModel model =
                new ViewModelProvider(requireActivity()).get(TrafficViewModel.class);
        View refresh = view.findViewById(R.id.homeRefresh);
        refresh.setOnClickListener(v -> {
            if (ValueAnimator.areAnimatorsEnabled()) {
                v.animate().rotationBy(360f).setDuration(600).start();
            }
            model.refresh();
        });
        model.getSnapshot().observe(getViewLifecycleOwner(), data -> {
            TextView status = view.findViewById(R.id.homeStatus);
            TextView counts = view.findViewById(R.id.homeCounts);
            TextView incidents = view.findViewById(R.id.homeIncidentCount);
            TextView works = view.findViewById(R.id.homeRoadworkCount);
            boolean ready = !data.loading && !"ERROR".equals(data.source)
                    && !"NONE".equals(data.source);
            int a = data.incidents.size();
            int b = data.roadworks.size();
            counts.setText(ready ? getString(R.string.home_counts, a, b) :
                    getString(R.string.loading));
            status.setText(data.message);
            if (incidents != null) incidents.setText(ready ? String.valueOf(a) : "—");
            if (works != null) works.setText(ready ? String.valueOf(b) : "—");
        });
    }
}
