package com.mogsev.simpleprojects.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableLong;

/**
 * Created by Eugene Sikaylo on 13.07.2017.
 */

public class MainTimerViewModel extends BaseObservable {

    public ObservableBoolean mWorking = new ObservableBoolean();

    public ObservableLong mTimer = new ObservableLong();

}
