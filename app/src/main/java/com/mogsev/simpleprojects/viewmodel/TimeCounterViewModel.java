package com.mogsev.simpleprojects.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableLong;

import com.mogsev.simpleprojects.engine.TimeCounter;

/**
 * Created by Eugene Sikaylo on 12.07.2017.
 */
public class TimeCounterViewModel extends BaseObservable {
    private static final String TAG = TimeCounterViewModel.class.getSimpleName();

    public final TimeCounter mTimeCounter = new TimeCounter();

    public final ObservableBoolean mWorking = new ObservableBoolean(false);

    public final ObservableLong mMillis = mTimeCounter.getObservableMillis();

    public TimeCounterViewModel() {

    }

    public ObservableLong getMillis() {
        return mMillis;
    }

    public void start() {
        mTimeCounter.start();
        mWorking.set(true);
    }

    public void stop() {
        mTimeCounter.stop();
        mWorking.set(false);
    }

}
