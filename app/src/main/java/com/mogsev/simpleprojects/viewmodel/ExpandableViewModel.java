package com.mogsev.simpleprojects.viewmodel;

import android.databinding.ObservableBoolean;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mogsev.simpleprojects.gui.animations.Slide;

import timber.log.Timber;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class ExpandableViewModel {

    private final ObservableBoolean mGoodsRvVisible;
    private final ObservableBoolean mUsersRvVisible;
    private final ObservableBoolean mTariffsRvVisible;
    private final ObservableBoolean mOrdersRvVisible;
    private final ObservableBoolean mFifthRvVisible;

    public ExpandableViewModel() {
        mGoodsRvVisible = new ObservableBoolean(false);
        mUsersRvVisible = new ObservableBoolean(false);
        mTariffsRvVisible = new ObservableBoolean(false);
        mOrdersRvVisible = new ObservableBoolean(false);
        mFifthRvVisible = new ObservableBoolean(false);
    }

    public ObservableBoolean getGoodsRvVisible() {
        return mGoodsRvVisible;
    }

    public ObservableBoolean getUsersRvVisible() {
        return mUsersRvVisible;
    }

    public ObservableBoolean getTariffsRvVisible() {
        return mTariffsRvVisible;
    }

    public ObservableBoolean getOrdersRvVisible() {
        return mOrdersRvVisible;
    }

    public ObservableBoolean getFifthRvVisible() {
        return mFifthRvVisible;
    }

    public boolean onClickFirst(View view, RecyclerView recyclerView) {
        Timber.i("onClickFirst");
        if (recyclerView.isShown()) {
            Slide.slide_up(view.getContext(), recyclerView);
            recyclerView.postDelayed(() -> {
                mGoodsRvVisible.set(!mGoodsRvVisible.get());
            }, 600);
        } else {
            mGoodsRvVisible.set(!mGoodsRvVisible.get());
            Slide.slide_down(view.getContext(), recyclerView);
        }

        return true;
    }

    public boolean onClickSecond(View view) {
        Timber.i("onClickSecond");
        mUsersRvVisible.set(!mUsersRvVisible.get());
        return true;
    }

    public boolean onClickTariffs(View view) {
        Timber.i("onClickSecond");
        mTariffsRvVisible.set(!mTariffsRvVisible.get());
        return true;
    }

    public boolean onClickOrders(View view) {
        Timber.i("onClickOrders");
        mOrdersRvVisible.set(!mOrdersRvVisible.get());
        return true;
    }

}
