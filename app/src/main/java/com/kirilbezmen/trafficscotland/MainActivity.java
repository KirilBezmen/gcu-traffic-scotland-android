package com.kirilbezmen.trafficscotland;

import android.os.Bundle;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.kirilbezmen.trafficscotland.ui.HomeFragment;
import com.kirilbezmen.trafficscotland.ui.TrafficDetailsFragment;
import com.kirilbezmen.trafficscotland.ui.TrafficListFragment;
import com.kirilbezmen.trafficscotland.ui.TrafficMapFragment;
import com.kirilbezmen.trafficscotland.ui.TrafficViewModel;

/** One Activity hosts all app destinations: Fragments + ViewModel (rubric target). */
public class MainActivity extends AppCompatActivity {
    private static final String STATE_SECTION = "state_section";
    private TrafficViewModel viewModel;
    private RadioGroup navigationGroup;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(TrafficViewModel.class);
        if (savedInstanceState != null) {
            viewModel.restoreSection(savedInstanceState.getString(STATE_SECTION));
        }
        navigationGroup = findViewById(R.id.navigationGroup);
        navigationGroup.check(buttonFor(viewModel.getSection()));
        navigationGroup.setOnCheckedChangeListener((group, id) -> {
            TrafficViewModel.Section target = sectionFor(id);
            if (target != null) {
                tintNavigation();
                showSection(target);
            }
        });

        tintNavigation();
        // FragmentManager itself restores selected/back-stack fragment on rotation.
        if (savedInstanceState == null) showSection(viewModel.getSection());
    }

    @Override protected void onSaveInstanceState(Bundle state) {
        state.putString(STATE_SECTION, viewModel.getSection().name());
        super.onSaveInstanceState(state);
    }

    public void navigate(TrafficViewModel.Section target) {
        if (target == null) return;
        int id = buttonFor(target);
        if (navigationGroup.getCheckedRadioButtonId() == id) showSection(target);
        else navigationGroup.check(id);
    }

    private void showSection(TrafficViewModel.Section section) {
        viewModel.setSection(section);
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        Fragment page;
        switch (section) {
            case INCIDENTS:
            case ROADWORKS: page = TrafficListFragment.newInstance(section); break;
            case MAP: page = new TrafficMapFragment(); break;
            case HOME:
            default: page = new HomeFragment(); break;
        }
        manager.beginTransaction().setReorderingAllowed(true)
                .setCustomAnimations(R.anim.ux_fade_in, R.anim.ux_fade_out)
                .replace(R.id.fragmentHost, page, section.name()).commit();
    }

    public void openSituationDetails(String id) {
        if (id == null || id.isEmpty()) return;
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .setCustomAnimations(R.anim.ux_fade_in, R.anim.ux_fade_out,
                        R.anim.ux_fade_in, R.anim.ux_fade_out)
                .replace(R.id.fragmentHost, TrafficDetailsFragment.newInstance(id), "details")
                .addToBackStack("details").commit();
    }


    /** Checked tabs have distinct icon/text colour in both native orientations. */
    private void tintNavigation() {
        boolean landscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        int[] ids = { R.id.navHome, R.id.navIncidents, R.id.navRoadworks, R.id.navMap };
        for (int id : ids) {
            RadioButton button = findViewById(id);
            if (button == null) continue;
            int color = button.isChecked() ? getColor(landscape ? R.color.navy : R.color.accent)
                    : getColor(landscape ? R.color.white : R.color.subtitle);
            button.setTextColor(color);
            Drawable[] icons = button.getCompoundDrawablesRelative();
            for (Drawable drawable : icons) {
                if (drawable != null) drawable.mutate().setTint(color);
            }
        }
    }

    private TrafficViewModel.Section sectionFor(int id) {
        if (id == R.id.navHome) return TrafficViewModel.Section.HOME;
        if (id == R.id.navIncidents) return TrafficViewModel.Section.INCIDENTS;
        if (id == R.id.navRoadworks) return TrafficViewModel.Section.ROADWORKS;
        if (id == R.id.navMap) return TrafficViewModel.Section.MAP;
        return null;
    }
    private int buttonFor(TrafficViewModel.Section section) {
        switch (section) {
            case INCIDENTS: return R.id.navIncidents;
            case ROADWORKS: return R.id.navRoadworks;
            case MAP: return R.id.navMap;
            case HOME:
            default: return R.id.navHome;
        }
    }
}
