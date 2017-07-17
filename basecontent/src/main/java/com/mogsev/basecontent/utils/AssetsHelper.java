package com.mogsev.basecontent.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Eugene Sikaylo on 17.07.2017.
 */

public final class AssetsHelper {
    private static final String TAG = AssetsHelper.class.getSimpleName();

    public static String loadJsonFromAssets(@NonNull Context context, @NonNull String fileName) {
        Log.i(TAG, "loadJsonFromAssets");
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        if (TextUtils.isEmpty(fileName)) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}
