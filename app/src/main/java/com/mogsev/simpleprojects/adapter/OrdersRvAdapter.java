package com.mogsev.simpleprojects.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mogsev.basecontent.adapter.BaseRecyclerListAdapter;
import com.mogsev.simpleprojects.data.entity.Order;
import com.mogsev.simpleprojects.databinding.ItemOrderBinding;

/**
 * Created by Eugene Sikaylo on 18.07.2017.
 */

public class OrdersRvAdapter extends BaseRecyclerListAdapter {
    private static final String TAG = OrdersRvAdapter.class.getSimpleName();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemOrderBinding binding = ItemOrderBinding.inflate(inflater, parent, false);
        return new OrderViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Order order = (Order) mList.get(position);
        if (holder instanceof OrderViewHolder) {
            ((OrderViewHolder) holder).binding.setOrder(order);
        }
    }

    /**
     * Class is like the helper
     */
    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        private ItemOrderBinding binding;

        public OrderViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.itemOrder.setOnClickListener((v) -> {
                onClickVisibility();
            });
        }

        public boolean onClickVisibility() {
            Log.i(TAG, "onClickOrder");
            boolean boo = binding.recyclerViewOrderTariffs.getVisibility() == View.VISIBLE;
            binding.recyclerViewOrderTariffs.setVisibility(!boo ? View.VISIBLE : View.GONE);
            return true;
        }
    }

}
