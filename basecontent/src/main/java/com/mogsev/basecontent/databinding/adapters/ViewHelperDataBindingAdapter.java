package com.mogsev.basecontent.databinding.adapters;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by Eugene Sikaylo on 12/27/2017.
 * email: mogsev@gmail.com
 */

public final class ViewHelperDataBindingAdapter {

    private ViewHelperDataBindingAdapter() {
        // empty
    }

    @BindingAdapter({"handleColorScheme"})
    public static void handleColorScheme(final SwipeRefreshLayout view, int[] colors) {
        view.setColorSchemeColors(colors);
    }

}
