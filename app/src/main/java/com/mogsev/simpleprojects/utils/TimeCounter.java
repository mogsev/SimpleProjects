package com.mogsev.simpleprojects.utils;

import android.databinding.ObservableLong;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Eugene Sikaylo on 29.06.2017.
 */

public final class TimeCounter {
    private static final String TAG = TimeCounter.class.getSimpleName();

    private static final long ONE_SECOND = 1000;
    private static final long SECONDS_IN_MINUTE = 60;

    private final Timer mTimer;
    private TimerTask mTimerTask;

    private long mMillis = 0;
    private final ObservableLong mObservableMillis;

    public TimeCounter() {
        mTimer = new Timer();
        mObservableMillis = new ObservableLong(mMillis);
    }

    private TimerTask createTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                mMillis += ONE_SECOND;
                updateMillis();
                Log.i(TAG, "TimeCounter millis: " + mMillis);
            }
        };
    }

    public void start() {
        mMillis = 0;
        updateMillis();
        mTimerTask = createTimerTask();
        mTimer.scheduleAtFixedRate(mTimerTask, ONE_SECOND, ONE_SECOND);
    }

    public void stop() {
        if (mTimerTask != null) mTimerTask.cancel();
        mTimer.purge();
    }

    public long getMillis() {
        return mMillis;
    }

    public long getMinutes() {
        return mMillis / ONE_SECOND / SECONDS_IN_MINUTE;
    }

    public ObservableLong getObservableMillis() {
        return mObservableMillis;
    }

    private void updateMillis() {
        mObservableMillis.set(mMillis);
    }

}
