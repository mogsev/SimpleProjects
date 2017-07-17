package com.mogsev.simpleprojects.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mogsev.basecontent.adapter.BaseRecyclerListAdapter;
import com.mogsev.simpleprojects.BR;
import com.mogsev.simpleprojects.data.entity.Tariff;
import com.mogsev.simpleprojects.data.entity.User;
import com.mogsev.simpleprojects.databinding.ItemTariffBinding;

import java.util.List;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class TariffsRvAdapter extends BaseRecyclerListAdapter {
    private static final String TAG = TariffsRvAdapter.class.getSimpleName();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTariffBinding binding = ItemTariffBinding.inflate(inflater, parent, false);
        return new TariffViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Tariff tariff = (Tariff) mList.get(position);
        if (holder instanceof TariffViewHolder) {
            ((TariffViewHolder) holder).binding.setVariable(BR.tariff, tariff);
        }
    }

    /**
     * Class is like the helper
     */
    public static class TariffViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public TariffViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }


    @BindingAdapter({"bind:users"})
    public static void loadImage(RecyclerView recyclerView, List<User> list) {
        UsersRvAdapter adapter = new UsersRvAdapter();
        adapter.addAll(list);
        recyclerView.setAdapter(adapter);
    }
}
