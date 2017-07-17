package com.mogsev.simpleprojects.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mogsev.basecontent.adapter.BaseRecyclerListAdapter;
import com.mogsev.simpleprojects.BR;
import com.mogsev.simpleprojects.data.entity.User;
import com.mogsev.simpleprojects.databinding.ItemUserBinding;

/**
 * Created by Eugene Sikaylo on 13.07.2017.
 */

public class UsersRvAdapter extends BaseRecyclerListAdapter {
    private static final String TAG = UsersRvAdapter.class.getSimpleName();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemUserBinding binding = ItemUserBinding.inflate(inflater, parent, false);
        return new UserViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        User user = (User) mList.get(position);
        if (holder instanceof UserViewHolder) {
            ((UserViewHolder) holder).binding.setVariable(BR.user, user);
        }
    }

    /**
     * Class is like the helper
     */
    public static class UserViewHolder extends RecyclerView.ViewHolder {

        public ViewDataBinding binding;

        public UserViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

}
