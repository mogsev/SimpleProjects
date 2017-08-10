package com.mogsev.forservice.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableLong;
import android.util.Log;

import com.mogsev.forservice.data.TimeCounterViewState;
import com.mogsev.forservice.engine.TimeCounter;
import com.mogsev.forservice.util.TimeFormatter;

import io.realm.Realm;


/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class TimeCounterViewModel extends BaseObservable {
    private static final String TAG = TimeCounterViewModel.class.getSimpleName();

    private static final long ONE_SECOND = 1000;
    private static final long SECONDS_IN_MINUTE = 60;

    private final TimeCounter mTimeCounter = new TimeCounter();
    // inner state model
    private TimeCounterViewState mTimeCounterViewState;

    private final ObservableBoolean mWorking = new ObservableBoolean(false);
    private final ObservableBoolean mPunchWorking = new ObservableBoolean(false);
    private final ObservableBoolean mPunchStartOffline = new ObservableBoolean(false);
    private final ObservableBoolean mPunchFinishOffline = new ObservableBoolean(false);

    private final ObservableLong mMillis = mTimeCounter.getObservableMillis();

    private final ObservableField<String> mTimeFormat = mTimeCounter.getTimeFormat();

    private final ObservableField<String> mFormattedTime = new ObservableField<>();

    private final ObservableField<String> mTimeStartFormat = new ObservableField<>();

    private String mTextPunchInBefore;
    private String mTextPunchOutBefore;

    public TimeCounterViewModel() {
        // check saved instance state
        checkSavedInstanceState();

        mMillis.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                Log.i(TAG, "onPropertyChanged: " + sender.toString() + " / " + propertyId);
                if (mMillis.get() % 5000 == 0) {
                    onSavedInstanceState();
                }
            }
        });

        mTimeFormat.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                Log.i(TAG, "onPropertyChanged: " + sender.toString() + " / " + propertyId);
                if (mPunchStartOffline.get()) {
                    mFormattedTime.set(String.format(mTextPunchInBefore, mTimeFormat.get()));
                } else if (mPunchFinishOffline.get()) {
                    mFormattedTime.set(String.format(mTextPunchOutBefore, mTimeFormat.get()));
                }
            }
        });
    }

    public ObservableLong getMillis() {
        return mMillis;
    }

    public long getMinutes() {
        return mMillis.get() / ONE_SECOND / SECONDS_IN_MINUTE;
    }

    public ObservableField<String> getTimeFormat() {
        return mTimeFormat;
    }

    public boolean isWorking() {
        return mWorking.get();
    }

    public ObservableBoolean getWorking() {
        return mWorking;
    }

    public ObservableBoolean getPunchWorking() {
        return mPunchWorking;
    }

    public ObservableBoolean getPunchFinishOffline() {
        return mPunchFinishOffline;
    }

    public ObservableBoolean getPunchStartOffline() {
        return mPunchStartOffline;
    }

    public ObservableField<String> getTimeStartFormat() {
        return mTimeStartFormat;
    }

    public ObservableField<String> getFormattedTime() {
        return mFormattedTime;
    }

    public void setTextPunchInBefore(String mTextPunchInBefore) {
        this.mTextPunchInBefore = mTextPunchInBefore;
    }

    public void setTextPunchOutBefore(String mTextPunchOutBefore) {
        this.mTextPunchOutBefore = mTextPunchOutBefore;
    }

    public void setPunchInTimeStart(long millis) {
        String timeFormat = TimeFormatter.getTime(millis, TimeFormatter.DATE_FORMAT_HHMMSS);
        mTimeStartFormat.set(timeFormat);
    }

    public void startPunchIn() {
        startPunchIn(0);
    }

    public void startPunchIn(long millis) {
        Log.i(TAG, "startPunchIn");
        mPunchWorking.set(true);
        mPunchStartOffline.set(false);
        mPunchFinishOffline.set(false);
        start(millis);
    }

    public void stopPunchIn() {
        Log.i(TAG, "stopPunchIn");
        mPunchWorking.set(false);
        mPunchStartOffline.set(false);
        mPunchFinishOffline.set(false);
        stop();
    }

    public void startPunchOffline() {
        Log.i(TAG, "stopPunchOffline");
        mPunchWorking.set(false);
        mPunchStartOffline.set(true);
        mPunchFinishOffline.set(false);
        start();
    }

    public void stopPunchOffline() {
        Log.i(TAG, "stopPunchOffline");
        mPunchWorking.set(false);
        mPunchStartOffline.set(false);
        mPunchFinishOffline.set(true);
        start();
    }

    private void start() {
        start(0);
    }

    private void start(long millis) {
        Log.i(TAG, "start");
        mWorking.set(true);
        mTimeCounter.start(millis);
    }

    private void stop() {
        Log.i(TAG, "stop");
        mWorking.set(false);
        onSavedInstanceState();
        mTimeCounter.stop();
    }

    private synchronized void checkSavedInstanceState() {
        Log.i(TAG, "checkSavedInstanceState");
        Realm realm = Realm.getDefaultInstance();
        mTimeCounterViewState = realm.where(TimeCounterViewState.class).findFirst();
        if (mTimeCounterViewState != null) { //check TimeCounterViewState
            if (mTimeCounterViewState.isWorking()) {
                // restore view state
                mWorking.set(mTimeCounterViewState.isWorking());
                mPunchWorking.set(mTimeCounterViewState.isPunchWorking());
                mTimeStartFormat.set(mTimeCounterViewState.getTimeStartFormat());
                mPunchStartOffline.set(mTimeCounterViewState.isPunchStartOffline());
                mPunchFinishOffline.set(mTimeCounterViewState.isPunchFinishOffline());
                // restore timeCounter
                mTimeCounter.onRestoreInstanceState();
            }
        } else { // create new instance and save to realm noSql
            mTimeCounterViewState = new TimeCounterViewState();
            mTimeCounterViewState.setMillis(0);
            mTimeCounterViewState.setWorking(false);
            mTimeCounterViewState.setPunchWorking(false);
            mTimeCounterViewState.setPunchStartOffline(false);
            mTimeCounterViewState.setPunchFinishOffline(false);
            realm.beginTransaction();
            realm.insertOrUpdate(mTimeCounterViewState);
            realm.commitTransaction();
        }
        realm.close();
    }

    private synchronized void onSavedInstanceState() {
        Log.i(TAG, "onSavedInstanceState");
        Realm realm = Realm.getDefaultInstance();
        mTimeCounterViewState = realm.where(TimeCounterViewState.class).findFirst();
        realm.executeTransaction((r) -> {
            mTimeCounterViewState.setWorking(mWorking.get());
            mTimeCounterViewState.setPunchWorking(mPunchWorking.get());
            mTimeCounterViewState.setPunchStartOffline(mPunchStartOffline.get());
            mTimeCounterViewState.setPunchFinishOffline(mPunchFinishOffline.get());
            mTimeCounterViewState.setTimeStartFormat(mTimeStartFormat.get());
        });
        realm.close();
    }

}
