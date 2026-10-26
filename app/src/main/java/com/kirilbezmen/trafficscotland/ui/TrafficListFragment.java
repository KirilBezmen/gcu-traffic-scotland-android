package com.kirilbezmen.trafficscotland.ui;

import android.os.Bundle;
import android.animation.ValueAnimator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kirilbezmen.trafficscotland.MainActivity;
import com.kirilbezmen.trafficscotland.R;
import com.kirilbezmen.trafficscotland.data.repository.TrafficRepository;

import java.util.Collections;

/**
 * Static header and filters; only the ListView viewport scrolls.
 * ListView already supports efficient view recycling, so no migration is
 * required simply to implement the requested scrolling behaviour.
 */
public class TrafficListFragment extends Fragment {
    private static final String ARG_SECTION = "section";
    private TrafficViewModel.Section section;
    private TrafficViewModel model;
    private TrafficSituationAdapter adapter;

    public TrafficListFragment() { super(R.layout.fragment_traffic_list); }

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
        String value = getArguments() == null
                ? "INCIDENTS" : getArguments().getString(ARG_SECTION, "INCIDENTS");
        section = "ROADWORKS".equals(value) ? TrafficViewModel.Section.ROADWORKS
                : TrafficViewModel.Section.INCIDENTS;
        model = new ViewModelProvider(requireActivity()).get(TrafficViewModel.class);

        TextView title = view.findViewById(R.id.listTitle);
        title.setText(section == TrafficViewModel.Section.ROADWORKS
                ? R.string.roadworks : R.string.incidents);

        adapter = new TrafficSituationAdapter(requireContext());
        ListView list = view.findViewById(R.id.situationList);
        list.setAdapter(adapter);
        list.setOnItemClickListener((parent, row, position, id) ->
                ((MainActivity) requireActivity())
                        .openSituationDetails(adapter.getItem(position).getId()));

        EditText search = view.findViewById(R.id.roadSearch);
        search.setText(model.getSearch(section));
        search.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                model.setSearch(section, s.toString());
                update(view, model.getSnapshot().getValue());
                list.setSelection(0);
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        CheckBox nearby = view.findViewById(R.id.nearbyOnly);
        View radiusPanel = view.findViewById(R.id.nearbyRadiusPanel);
        nearby.setChecked(model.isNearbyOnly());
        radiusPanel.setVisibility(model.isNearbyOnly() ? View.VISIBLE : View.GONE);
        nearby.setOnCheckedChangeListener((button, checked) -> {
            model.setNearbyOnly(checked);
            radiusPanel.setVisibility(checked ? View.VISIBLE : View.GONE);
            update(view, model.getSnapshot().getValue());
            list.setSelection(0);
        });

        SeekBar radius = view.findViewById(R.id.nearbyRadius);
        radius.setMax(195); // 5–200 km from the fixed reference location
        radius.setProgress(model.getRadiusKm() - 5);
        TextView radiusLabel = view.findViewById(R.id.radiusLabel);
        radiusLabel.setText(getString(R.string.nearby_radius, model.getRadiusKm()));
        radius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar bar, int value, boolean fromUser) {
                if (!fromUser) return;
                model.setRadiusKm(value + 5);
                radiusLabel.setText(getString(R.string.nearby_radius, model.getRadiusKm()));
                if (model.isNearbyOnly()) {
                    update(view, model.getSnapshot().getValue());
                    list.setSelection(0);
                }
            }
            @Override public void onStartTrackingTouch(SeekBar bar) {}
            @Override public void onStopTrackingTouch(SeekBar bar) {}
        });

        view.findViewById(R.id.mapResults).setOnClickListener(v -> {
            TrafficRepository.Snapshot snapshot = model.getSnapshot().getValue();
            if (snapshot == null || snapshot.loading || model.filtered(section, snapshot).isEmpty()) {
                Toast.makeText(requireContext(), R.string.no_situations, Toast.LENGTH_SHORT).show();
                return;
            }
            model.selectForMap(model.filtered(section, snapshot));
            ((MainActivity) requireActivity()).navigate(TrafficViewModel.Section.MAP);
        });

        // ImageButton has a 48dp touch target and an accessibility description.
        view.findViewById(R.id.listRefresh).setOnClickListener(v -> {
            if (ValueAnimator.areAnimatorsEnabled()) {
                v.animate().rotationBy(360f).setDuration(600).start();
            }
            model.refresh();
        });
        model.getSnapshot().observe(getViewLifecycleOwner(), snapshot -> update(view, snapshot));
    }

    private void update(View view, TrafficRepository.Snapshot data) {
        if (data == null || adapter == null) return;
        ((TextView) view.findViewById(R.id.listSource)).setText(data.message);
        adapter.submit(data.loading ? Collections.emptyList() : model.filtered(section, data));
        ((TextView) view.findViewById(R.id.listCount))
                .setText(getString(R.string.results_count, adapter.getCount()));

        TextView empty = view.findViewById(R.id.listEmptyMessage);
        empty.setVisibility(adapter.getCount() == 0 ? View.VISIBLE : View.GONE);
        if (data.loading) empty.setText(R.string.loading);
        else if ("ERROR".equals(data.source) || "NONE".equals(data.source)) {
            empty.setText(data.message);
        } else {
            empty.setText(model.getSearch(section).trim().isEmpty() && !model.isNearbyOnly()
                    ? R.string.no_situations : R.string.no_matches);
        }
    }

    @Override public void onDestroyView() {
        adapter = null;
        super.onDestroyView();
    }
}
