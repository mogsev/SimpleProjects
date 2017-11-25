package com.mogsev.screenblurexample.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.NonNull;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public final class BlurBuilder {

    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 1.0f;

    private BlurBuilder() {
        // empty
    }

    public static Bitmap blur(@NonNull Context context, @NonNull Bitmap image) {
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(BLUR_RADIUS);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }

    public static Bitmap overlayLayer(@NonNull Bitmap bitmap) {
        Bitmap overlay = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(overlay);
        canvas.drawBitmap(bitmap, new Matrix(), null);
        canvas.drawARGB(125, 128, 128, 128);

        bitmap.recycle();

        return overlay;
    }

}
