package com.mogsev.basecontent.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.mogsev.basecontent.R;


/**
 * Created by Eugene Sikaylo on 19.05.2017.
 */

public final class MainHelper {
    private static final String TAG = MainHelper.class.getSimpleName();

    private MainHelper() {
        // empty
    }

    /**
     * Check network available
     */
    public static boolean isNetworkAvailable(@NonNull final Context context) {
        Log.i(TAG, "isNetworkAvailable");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * Hide keyboard
     */
    public static void hideKeyboard(@NonNull final Activity activity) {
        Log.i(TAG, "hideKeyboard");
        if (activity == null) {
            throw new NullPointerException("Activity == null");
        }
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Show keyboard
     */
    public static void requestFocus(@NonNull final Activity activity, @NonNull final View view) {
        Log.i(TAG, "requestFocus");
        if (activity == null) {
            throw new NullPointerException("Activity == null");
        }
        if (view == null) {
            throw new NullPointerException("View == null");
        }
        if (view.requestFocus()) {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public static void showResourceLayoutType(@NonNull final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        Log.i(TAG, "Configuration: " + context.getResources().getConfiguration().toString());
        Log.i(TAG, "Resource layout: " + context.getString(R.string.app_resource_values_type));
    }
}
