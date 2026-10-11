package com.kirilbezmen.trafficscotland.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import com.kirilbezmen.trafficscotland.BuildConfig;
import com.kirilbezmen.trafficscotland.MainActivity;
import com.kirilbezmen.trafficscotland.R;
import com.kirilbezmen.trafficscotland.data.model.TrafficLocation;
import com.kirilbezmen.trafficscotland.data.model.TrafficRecord;
import com.kirilbezmen.trafficscotland.data.model.TrafficSituation;
import com.kirilbezmen.trafficscotland.data.repository.TrafficRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/** Google Maps SDK is expressly allowed by coursework. API key stays local. */
public class TrafficMapFragment extends Fragment {
    private MapView mapView;
    private GoogleMap map;
    private TrafficRepository.Snapshot latest;
    private TrafficViewModel model;

    public TrafficMapFragment() { super(R.layout.fragment_map); }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
        super.onViewCreated(view, state);
        model = new ViewModelProvider(requireActivity()).get(TrafficViewModel.class);
        view.findViewById(R.id.clearMapSelection).setOnClickListener(v -> {
            model.clearMapSelection();
            drawMarkers();
        });
        if (BuildConfig.MAPS_API_KEY.trim().isEmpty()) {
            ((TextView) view.findViewById(R.id.mapHint)).setText(R.string.map_key_required);
        } else {
            FrameLayout container = view.findViewById(R.id.mapContainer);
            mapView = new MapView(requireContext());
            container.addView(mapView, new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mapView.onCreate(state);
            mapView.getMapAsync(googleMap -> {
                map = googleMap;
                map.getUiSettings().setZoomControlsEnabled(true);
                map.setOnInfoWindowClickListener(marker -> {
                    Object situationId = marker.getTag();
                    if (situationId instanceof String) {
                        ((MainActivity) requireActivity()).openSituationDetails((String) situationId);
                    }
                });
                drawMarkers();
            });
        }
        model.getSnapshot().observe(getViewLifecycleOwner(), data -> {
            latest = data;
            ((TextView) view.findViewById(R.id.mapSource)).setText(data.message);
            drawMarkers();
        });
        updateSelectionLabel();
    }

    private void updateSelectionLabel() {
        if (getView() == null || model == null) return;
        TextView label = getView().findViewById(R.id.mapSelection);
        int n = model.mapSelection().size();
        label.setText(n == 0 ? getString(R.string.map_all_count)
                : getString(R.string.map_selection_count, n));
    }

    private void drawMarkers() {
        updateSelectionLabel();
        if (map == null || latest == null || latest.loading) return;
        map.clear();
        List<TrafficSituation> visible = new ArrayList<>(latest.incidents);
        visible.addAll(latest.roadworks);
        Set<String> selected = model.mapSelection();
        LatLng first = null;
        int added = 0;
        for (TrafficSituation situation : visible) {
            if (!selected.isEmpty() && !selected.contains(situation.getId())) continue;
            for (TrafficRecord record : situation.getRecords()) {
                List<LatLng> path = new ArrayList<>();
                for (TrafficLocation loc : record.getLocations()) {
                    if (loc.hasCoordinates()) {
                        path.add(new LatLng(loc.getLatitude(), loc.getLongitude()));
                    }
                }
                if (path.isEmpty()) continue;
                LatLng location = path.get(0);
                if (first == null) first = location;
                boolean works = situation.getFeed() == TrafficSituation.Feed.CURRENT_ROADWORKS;
                float hue = works ? BitmapDescriptorFactory.HUE_ORANGE : BitmapDescriptorFactory.HUE_RED;
                String roads = String.join(", ", situation.getRoadNames());
                if (roads.isEmpty()) roads = getString(R.string.unknown_road);
                Marker marker = map.addMarker(new MarkerOptions()
                        .position(location).title(roads).snippet(situation.getSummary())
                        .icon(BitmapDescriptorFactory.defaultMarker(hue)));
                if (marker != null) marker.setTag(situation.getId());
                if (path.size() > 1) {
                    map.addPolyline(new PolylineOptions().addAll(path).width(5f)
                            .color(works ? 0xFFF59E0B : 0xFFEF4444));
                }
                added++;
            }
        }
        if (first != null) {
            // Camera starts in Scotland even before any marker is clicked.
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(first,
                    selected.isEmpty() ? 6.0f : 8.0f));
        } else {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(56.5, -4.3), 5.8f));
        }
    }

    @Override public void onStart() { super.onStart(); if (mapView != null) mapView.onStart(); }
    @Override public void onResume() { super.onResume(); if (mapView != null) mapView.onResume(); }
    @Override public void onPause() { if (mapView != null) mapView.onPause(); super.onPause(); }
    @Override public void onStop() { if (mapView != null) mapView.onStop(); super.onStop(); }
    @Override public void onLowMemory() { super.onLowMemory(); if (mapView != null) mapView.onLowMemory(); }
    @Override public void onSaveInstanceState(@NonNull Bundle state) {
        if (mapView != null) mapView.onSaveInstanceState(state);
        super.onSaveInstanceState(state);
    }
    @Override public void onDestroyView() {
        if (mapView != null) {
            mapView.onDestroy();
            mapView = null;
        }
        map = null;
        latest = null;
        super.onDestroyView();
    }
}
