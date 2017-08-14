package com.mogsev.forservice.engine;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableLong;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Pair;

import com.mogsev.forservice.data.TimeCounterState;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public final class TimeCounter {
    private static final String TAG = TimeCounter.class.getSimpleName();

    private static final String TIME_PATTERN = "%02d:%02d:%02d";
    private static final long ONE_SECOND = 1000;
    private static final long SECONDS_IN_MINUTE = 60;

    private Disposable mDisposable;

    private long mMillis = 0;
    private final ObservableBoolean mWorking;
    private final ObservableLong mObservableMillis;
    private final ObservableField<String> mTimeFormat;

    private TimeCounterState mTimeCounterState;

    private final HandlerThread handlerThread = new HandlerThread("TimeCounter");
    private final Handler mHandler;

    public TimeCounter() {
        // create handler
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());

        mWorking = new ObservableBoolean();
        mObservableMillis = new ObservableLong(mMillis);
        mTimeFormat = new ObservableField<>();

        checkSavedState();
    }

    public void start() {
        start(0);
    }

    public void start(long millis) {
        Log.i(TAG, "Start");
        if (mWorking.get()) {
            stop();
        }
        mWorking.set(true);
        mDisposable = Flowable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((aLong) -> {
                            Log.i(TAG, "Timer: " + aLong);
                            mMillis = aLong.longValue() * 1000 + millis;
                            mObservableMillis.set(mMillis);
                            mHandler.post(() -> {
                                    updateTimeFormat(mMillis);
                                    onSaveInstanceState(mMillis, true);
                            });
                        }, throwable -> {
                            throwable.printStackTrace();
                        }
                );
    }

    public void stop() {
        Log.i(TAG, "stop");
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        mWorking.set(false);
        onSaveInstanceState(0, false);
    }

    public ObservableLong getObservableMillis() {
        return mObservableMillis;
    }

    public ObservableField<String> getTimeFormat() {
        return mTimeFormat;
    }

    public long getMinutes() {
        return mMillis / ONE_SECOND / SECONDS_IN_MINUTE;
    }

    public Pair<Long, Boolean> getSavedInstanceState() {
        Realm realm = Realm.getDefaultInstance();
        TimeCounterState model = realm.where(TimeCounterState.class).findFirst();
        Pair<Long, Boolean> pair = new Pair<>(model.getMillis(), model.isWorking());
        realm.close();
        return pair;
    }

    public void onRestoreInstanceState() {
        Realm realm = Realm.getDefaultInstance();
        mTimeCounterState = realm.where(TimeCounterState.class).findFirst();
        if (mTimeCounterState != null) {
            Log.i(TAG, "TimeCounterModel: " + mTimeCounterState.toString());
            if (mTimeCounterState.isWorking()) {
                start(mTimeCounterState.getMillis());
            }
        }
        realm.close();
    }

    private void updateTimeFormat(long millis) {
        long seconds = millis / ONE_SECOND;
        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        long h = (seconds / 60 / 60) % 24;
        mTimeFormat.set(String.format(TIME_PATTERN, h, m, s));
    }

    private synchronized void onSaveInstanceState(long millis, boolean isWorking) {
        Log.i(TAG, "onSaveInstanceState: " + millis + " / " + isWorking);
        if (millis % 5 == 0) {
            Realm realm = Realm.getDefaultInstance();
            mTimeCounterState = realm.where(TimeCounterState.class).findFirst();
            realm.beginTransaction();
            mTimeCounterState.setMillis(millis);
            mTimeCounterState.setWorking(isWorking);
            realm.commitTransaction();
            realm.close();
        }
    }

    private void checkSavedState() {
        Realm realm = Realm.getDefaultInstance();
        mTimeCounterState = realm.where(TimeCounterState.class).findFirst();
        if (mTimeCounterState == null) {
            realm.beginTransaction();
            realm.insertOrUpdate(new TimeCounterState());
            realm.commitTransaction();
        }
    }

}
