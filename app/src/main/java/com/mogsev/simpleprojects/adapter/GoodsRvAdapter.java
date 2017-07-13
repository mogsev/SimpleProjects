package com.mogsev.simpleprojects.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mogsev.basecontent.adapter.BaseRecyclerListAdapter;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class GoodsRvAdapter extends BaseRecyclerListAdapter {
    private static final String TAG = GoodsRvAdapter.class.getSimpleName();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    /**
     * Class is like the helper
     */
    public static class CatOneViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public CatOneViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
