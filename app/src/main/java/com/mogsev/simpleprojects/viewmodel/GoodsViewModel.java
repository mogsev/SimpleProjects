package com.mogsev.simpleprojects.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.mogsev.simpleprojects.R;
import com.mogsev.simpleprojects.data.entity.Goods;
import com.squareup.picasso.Picasso;

/**
 * Created by Eugene Sikaylo on 13.07.2017.
 */

public class GoodsViewModel extends BaseObservable {

    public final ObservableField<String> name = new ObservableField<>();

    public final ObservableField<String> description = new ObservableField<>();

    public final ObservableField<String> imageUrl = new ObservableField<>();

    public final ObservableField<String> price = new ObservableField<>();

    public GoodsViewModel() {

    }

    public GoodsViewModel(Goods goods) {
        name.set(goods.getName());
        description.set(goods.getDescription());
        price.set(goods.getPrice());
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_image_broken_gren_24dp)
                .into(view);
    }

}
