package com.kirilbezmen.trafficscotland;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.kirilbezmen.trafficscotland.ui.TrafficDetailsFragment;
import com.kirilbezmen.trafficscotland.ui.TrafficListFragment;
import com.kirilbezmen.trafficscotland.ui.TrafficMapFragment;
import com.kirilbezmen.trafficscotland.ui.TrafficViewModel;

/**
 * Single-activity navigation host.
 *
 * Networking, XML parsing and traffic data are deliberately NOT handled here.
 * The retained ViewModel and fragments will own that functionality.
 */
public class MainActivity extends AppCompatActivity {

    private static final String STATE_SECTION = "state_section";

    private TrafficViewModel viewModel;
    private RadioGroup navigationGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
                (view, insets) -> {
                    Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    view.setPadding(bars.left, bars.top, bars.right, bars.bottom);
                    return insets;
                });

        viewModel = new ViewModelProvider(this).get(TrafficViewModel.class);

        // ViewModel survives rotation; this also covers process recreation.
        if (savedInstanceState != null) {
            String name = savedInstanceState.getString(STATE_SECTION);
            viewModel.restoreSection(name);
        }

        navigationGroup = findViewById(R.id.navigationGroup);
        navigationGroup.check(buttonFor(viewModel.getSection()));
        navigationGroup.setOnCheckedChangeListener((group, checkedId) -> {
            TrafficViewModel.Section section = sectionFor(checkedId);
            if (section != null) {
                showSection(section);
            }
        });

        // FragmentManager automatically restores the previous fragment after
        // rotation. Never overwrite it with a fresh one on recreation.
        if (savedInstanceState == null) {
            showSection(viewModel.getSection());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_SECTION, viewModel.getSection().name());
        super.onSaveInstanceState(outState);
    }

    private void showSection(TrafficViewModel.Section section) {
        viewModel.setSection(section);
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        Fragment fragment;
        if (section == TrafficViewModel.Section.MAP) {
            fragment = new TrafficMapFragment();
        } else {
            fragment = TrafficListFragment.newInstance(section);
        }

        manager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentHost, fragment, section.name())
                .commit();
    }

    /**
     * Called by the list UI when a situation is selected.
     * The detail screen is in the same Activity and can be dismissed with Back.
     */
    public void openSituationDetails(String situationId) {
        if (situationId == null || situationId.trim().isEmpty()) {
            return;
        }
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentHost,
                        TrafficDetailsFragment.newInstance(situationId), "details")
                .addToBackStack("details")
                .commit();
    }

    private TrafficViewModel.Section sectionFor(int checkedId) {
        if (checkedId == R.id.navIncidents) {
            return TrafficViewModel.Section.INCIDENTS;
        }
        if (checkedId == R.id.navRoadworks) {
            return TrafficViewModel.Section.ROADWORKS;
        }
        if (checkedId == R.id.navMap) {
            return TrafficViewModel.Section.MAP;
        }
        return null;
    }

    private int buttonFor(TrafficViewModel.Section section) {
        switch (section) {
            case ROADWORKS:
                return R.id.navRoadworks;
            case MAP:
                return R.id.navMap;
            case INCIDENTS:
            default:
                return R.id.navIncidents;
        }
    }
}
