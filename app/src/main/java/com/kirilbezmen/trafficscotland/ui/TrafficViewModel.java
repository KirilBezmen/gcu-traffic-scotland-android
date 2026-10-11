package com.kirilbezmen.trafficscotland.ui;

import androidx.lifecycle.ViewModel;

/**
 * Retains the selected section across orientation changes.
 *
 * The traffic records, loading/errors and filtering state will be introduced
 * here (or in dedicated ViewModels) after the XmlPullParser has been tested.
 */
public class TrafficViewModel extends ViewModel {

    public enum Section {
        INCIDENTS,
        ROADWORKS,
        MAP
    }

    private Section section = Section.INCIDENTS;

    public Section getSection() {
        return section;
    }

    public void setSection(Section newSection) {
        if (newSection != null) {
            section = newSection;
        }
    }

    public void restoreSection(String savedName) {
        if (savedName == null) {
            return;
        }
        try {
            section = Section.valueOf(savedName);
        } catch (IllegalArgumentException ignored) {
            section = Section.INCIDENTS;
        }
    }
}
