package com.mogsev.simpleprojects.viewmodel;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mogsev.simpleprojects.adapter.UsersRvAdapter;
import com.mogsev.simpleprojects.data.entity.Tariff;

import io.reactivex.Observable;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class TariffViewModel {
    private static final String TAG = TariffViewModel.class.getSimpleName();

    @BindingAdapter({"bind:setListOfUsers"})
    public static void setListOfUsers(RecyclerView recyclerView, Tariff tariff) {
        Log.i(TAG, "setListOfUsers");
        UsersRvAdapter adapter = new UsersRvAdapter();
        adapter.addAll(tariff.getUsers());
        recyclerView.setAdapter(adapter);
    }

}
