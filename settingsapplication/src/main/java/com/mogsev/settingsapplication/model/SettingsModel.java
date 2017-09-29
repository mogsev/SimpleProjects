package com.mogsev.settingsapplication.model;

import android.databinding.ObservableInt;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class SettingsModel {

    public final ObservableInt mType;

    public SettingsModel() {
        mType = new ObservableInt();
    }


}
