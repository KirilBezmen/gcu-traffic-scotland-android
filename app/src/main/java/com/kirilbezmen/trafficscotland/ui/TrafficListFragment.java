package com.kirilbezmen.trafficscotland.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kirilbezmen.trafficscotland.R;

/**
 * The same list screen is reused for incidents and current roadworks.
 * The actual adapter will be added after real XML parsing is verified.
 */
public class TrafficListFragment extends Fragment {

    private static final String ARG_SECTION = "section";

    public TrafficListFragment() {
        super(R.layout.fragment_traffic_list);
    }

    public static TrafficListFragment newInstance(TrafficViewModel.Section section) {
        TrafficListFragment fragment = new TrafficListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION, section.name());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String section = getArguments() == null
                ? TrafficViewModel.Section.INCIDENTS.name()
                : getArguments().getString(ARG_SECTION,
                        TrafficViewModel.Section.INCIDENTS.name());

        boolean roadworks = TrafficViewModel.Section.ROADWORKS.name().equals(section);
        TextView title = view.findViewById(R.id.listTitle);
        title.setText(roadworks ? R.string.roadworks : R.string.incidents);
    }
}
