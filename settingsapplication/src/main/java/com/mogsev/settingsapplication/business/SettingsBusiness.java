package com.mogsev.settingsapplication.business;

import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.util.Log;


/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class SettingsBusiness {
    private static final String TAG = SettingsBusiness.class.getSimpleName();

    public static final int TYPE_FIRST = 1;
    public static final int TYPE_SECOND = 2;
    public static final int TYPE_THIRD = 3;

    public final ObservableBoolean mFirstType = new ObservableBoolean(false);
    public final ObservableBoolean mSecondType = new ObservableBoolean(false);
    public final ObservableBoolean mThirdType = new ObservableBoolean(false);

    public final ObservableInt mType;

    public SettingsBusiness() {
        mType = new ObservableInt();
        mType.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                Log.i(TAG, "onPropertyChanged");
                switch (mType.get()) {
                    case TYPE_FIRST:
                        mFirstType.set(true);
                        mSecondType.set(false);
                        mThirdType.set(false);
                        break;
                    case TYPE_SECOND:
                        mFirstType.set(false);
                        mSecondType.set(true);
                        mThirdType.set(false);
                        break;
                    case TYPE_THIRD:
                        mFirstType.set(false);
                        mSecondType.set(false);
                        mThirdType.set(true);
                        break;
                    default:
                        Log.i(TAG, "onPropertyChanged default");
                        break;
                }
            }
        });
        changeType(TYPE_FIRST);
    }

    public boolean changeType(int type) {
        mType.set(type);
        return true;
    }

    public boolean onCheckedChanged(boolean checked, int type) {
        Log.i(TAG, String.format("onCheckedChanged: %s, %s", checked, type));
        if (checked) {
            mType.set(type);
        }
        return true;
    }
}
