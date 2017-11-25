package com.mogsev.screenblurexample.asynctasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.view.View;

import com.mogsev.screenblurexample.R;
import com.mogsev.screenblurexample.utils.BlurBuilder;

import timber.log.Timber;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class BlurAsyncTask extends AsyncTask<Void, Void, Bitmap> {

    private final View mView;
    private Bitmap mBitmap;

    public BlurAsyncTask(@NonNull View view, @NonNull Bitmap bitmap) {
        if (view == null) {
            throw new NullPointerException("View cannot be null");
        }
        if (bitmap == null) {
            throw new NullPointerException("Bitmap cannot be null");
        }
        mView = view;
        mBitmap = bitmap;
    }

    public BlurAsyncTask(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("View cannot be null");
        }
        mView = view;
        Bitmap bitmap = BitmapFactory.decodeResource(mView.getResources(), R.mipmap.ic_blur);
        mBitmap = bitmap;

        // execute
        BlurAsyncTask.this.execute();
    }

    protected Bitmap doInBackground(Void... arg0) {
        Timber.i("doInBackground");
        Bitmap blurBitmap = BlurBuilder.blur(mView.getContext(), mBitmap);
        Bitmap overlay = BlurBuilder.overlayLayer(blurBitmap);

        return overlay;
    }

    protected void onPostExecute(Bitmap result) {
        Timber.i("onPostExecute");
        if (result != null) {
            final Drawable drawable = new BitmapDrawable(mView.getResources(), result);
            mView.setBackground(drawable);
        }
    }

}
