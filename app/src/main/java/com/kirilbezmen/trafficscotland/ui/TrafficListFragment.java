package com.kirilbezmen.trafficscotland.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kirilbezmen.trafficscotland.MainActivity;
import com.kirilbezmen.trafficscotland.R;
import com.kirilbezmen.trafficscotland.data.repository.TrafficRepository;

import java.util.Collections;

/** Shows actual parsed situations, sorted in the feed's original order. */
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

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String value = getArguments() == null ? "INCIDENTS"
                : getArguments().getString(ARG_SECTION, "INCIDENTS");
        section = "ROADWORKS".equals(value) ? TrafficViewModel.Section.ROADWORKS
                : TrafficViewModel.Section.INCIDENTS;
        model = new ViewModelProvider(requireActivity()).get(TrafficViewModel.class);

        TextView title = view.findViewById(R.id.listTitle);
        title.setText(section == TrafficViewModel.Section.ROADWORKS ?
                R.string.roadworks : R.string.incidents);
        adapter = new TrafficSituationAdapter(requireContext());
        ListView list = view.findViewById(R.id.situationList);
        list.setAdapter(adapter);
        list.setOnItemClickListener((parent, row, position, id) ->
                ((MainActivity) requireActivity()).openSituationDetails(
                        adapter.getItem(position).getId()));

        EditText search = view.findViewById(R.id.roadSearch);
        search.setText(model.getSearch(section));
        search.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                model.setSearch(section, s.toString());
                update(view, model.getSnapshot().getValue());
            }
            @Override public void afterTextChanged(Editable s) {}
        });
        view.findViewById(R.id.listRefresh).setOnClickListener(v -> model.refresh());
        model.getSnapshot().observe(getViewLifecycleOwner(), s -> update(view, s));
    }

    private void update(View view, TrafficRepository.Snapshot data) {
        if (data == null) return;
        TextView status = view.findViewById(R.id.listSource);
        status.setText(data.message);
        TextView count = view.findViewById(R.id.listCount);
        adapter.submit(data.loading ? Collections.emptyList() : model.filtered(section, data));
        count.setText(getString(R.string.results_count, adapter.getCount()));
        TextView empty = view.findViewById(R.id.listEmptyMessage);
        empty.setVisibility(adapter.getCount() == 0 ? View.VISIBLE : View.GONE);
        empty.setText(data.loading ? R.string.loading :
                model.getSearch(section).isEmpty() ? R.string.no_situations : R.string.no_matches);
    }
}
