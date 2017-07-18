package com.mogsev.simpleprojects.viewmodel;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mogsev.simpleprojects.adapter.TariffsRvAdapter;
import com.mogsev.simpleprojects.data.entity.Order;

/**
 * Created by Eugene Sikaylo on 18.07.2017.
 */

public class OrderViewModel {
    private static final String TAG = OrderViewModel.class.getSimpleName();

    @BindingAdapter({"bind:setListOfTariffs"})
    public static void setListOfTariffs(RecyclerView recyclerView, Order order) {
        Log.i(TAG, "setListOfUsers");
        TariffsRvAdapter adapter = new TariffsRvAdapter();
        adapter.addAll(order.getTariffs());
        recyclerView.setAdapter(adapter);
    }

}
