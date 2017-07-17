package com.mogsev.simpleprojects.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mogsev.basecontent.adapter.BaseRecyclerListAdapter;
import com.mogsev.simpleprojects.BR;
import com.mogsev.simpleprojects.data.entity.Goods;
import com.mogsev.simpleprojects.databinding.ItemGoodsBinding;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class GoodsRvAdapter extends BaseRecyclerListAdapter {
    private static final String TAG = GoodsRvAdapter.class.getSimpleName();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemGoodsBinding binding = ItemGoodsBinding.inflate(inflater, parent, false);
        return new GoodsViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Goods goods = (Goods) mList.get(position);
        if (holder instanceof GoodsViewHolder) {
            ((GoodsViewHolder) holder).binding.setVariable(BR.goods, goods);
        }
    }

    /**
     * Class is like the helper
     */
    public static class GoodsViewHolder extends RecyclerView.ViewHolder {

        public ViewDataBinding binding;

        public GoodsViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
