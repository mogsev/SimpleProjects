package com.mogsev.basecontent.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Eugene Sikaylo on 13.07.2017.
 */

public abstract class BaseRecyclerListAdapter<M, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements RecyclerCollectionAdapter<M> {
    private static final String TAG = BaseRecyclerListAdapter.class.getSimpleName();

    protected final List<M> mList;

    public BaseRecyclerListAdapter() {
        mList = new ArrayList<M>();
    }

    public BaseRecyclerListAdapter(List<M> list) {
        mList = list;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public M getItem(int position) {
        return mList.get(position);
    }

    @Override
    public void clearAndAddAll(Collection<M> data) {
        mList.clear();

        for (M item : data) {
            addInternal(item);
        }

        notifyDataSetChanged();
    }

    @Override
    public void addAll(Collection<M> data) {
        Log.i(TAG, "addAll");
        for (M item : data) {
            addInternal(item);
        }

        int addedSize = data.size();
        int oldSize = mList.size() - addedSize;
        notifyItemRangeInserted(oldSize, addedSize);
    }

    @Override
    public void addItem(M item) {
        addInternal(item);
        notifyItemInserted(mList.size());
    }

    @Override
    public void updateItem(M item) {
        // Swap the model
        int position = getItemPosition(item);
        if (position >= 0) {
            mList.remove(position);
            mList.add(position, item);
        }

        if (position >= 0) {
            notifyItemChanged(position);
        }
    }

    @Override
    public void removeItem(M item) {
        int position = getItemPosition(item);
        if (position >= 0) {
            mList.remove(item);
        }

        if (position >= 0) {
            notifyItemRemoved(position);
        }
    }

    private int getItemPosition(M item) {
        for (int i = 0; i < mList.size(); i++) {
            M model = mList.get(i);
            if (model.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    private void addInternal(M item) {
        Log.i(TAG, "addInternal " + item);
        mList.add(item);
    }

}
