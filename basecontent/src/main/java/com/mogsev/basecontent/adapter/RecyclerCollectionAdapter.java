package com.mogsev.basecontent.adapter;

import java.util.Collection;

/**
 * Created by Eugene Sikaylo on 13.07.2017.
 */

public interface RecyclerCollectionAdapter<M> {

    public void clearAndAddAll(Collection<M> data);

    public void addAll(Collection<M> data);

    public void addItem(M item);

    public void updateItem(M item);

    public void removeItem(M item);

    public M getItem(int position);

}
