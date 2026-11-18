package com.kirilbezmen.trafficscotland.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.kirilbezmen.trafficscotland.MainActivity;
import com.kirilbezmen.trafficscotland.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Smoke checks for this UI refactor. Must be executed on an emulator:
 * CI compiles Android instrumentation tests but does NOT run them.
 */
@RunWith(AndroidJUnit4.class)
public class NavigationChromeTest {
    @Rule public ActivityScenarioRule<MainActivity> activity =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test public void homeContainsOnlyPrimaryTabsAndAnAccessibleRefreshAction() {
        onView(withId(R.id.navHome)).check(matches(isDisplayed()));
        onView(withId(R.id.homeRefresh)).check(matches(isDisplayed()));
        onView(withText("Explore traffic")).check(doesNotExist());
    }

    @Test public void listKeepsFiltersSeparateFromScrollableResults() {
        onView(withId(R.id.navIncidents)).perform(click());
        onView(withId(R.id.listRefresh)).check(matches(isDisplayed()));
        onView(withId(R.id.roadSearch)).check(matches(isDisplayed()));
        onView(withId(R.id.situationList)).check(matches(isDisplayed()));
        onView(withId(R.id.nearbyRadiusPanel)).check(
                matches(withEffectiveVisibility(androidx.test.espresso.matcher.ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.nearbyOnly)).perform(click());
        onView(withId(R.id.nearbyRadiusPanel)).check(
                matches(withEffectiveVisibility(androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE)));
    }
    /**
     * Recreating the Activity simulates Android view restoration. This does not
     * substitute for a manual landscape screenshot/layout test.
     */
    @Test public void selectedRoadworksTabSurvivesActivityRecreation() {
        onView(withId(R.id.navRoadworks)).perform(click());
        onView(withId(R.id.listTitle)).check(matches(withText(R.string.roadworks)));
        activity.getScenario().recreate();
        onView(withId(R.id.navRoadworks)).check(matches(isChecked()));
        onView(withId(R.id.listTitle)).check(matches(withText(R.string.roadworks)));
    }

    @Test public void roadSearchTextSurvivesActivityRecreation() {
        onView(withId(R.id.navIncidents)).perform(click());
        onView(withId(R.id.roadSearch)).perform(typeText("M8"), closeSoftKeyboard());
        activity.getScenario().recreate();
        onView(withId(R.id.roadSearch)).check(matches(withText("M8")));
        onView(withId(R.id.navIncidents)).check(matches(isChecked()));
    }

}
