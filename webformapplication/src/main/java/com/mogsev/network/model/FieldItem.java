package com.mogsev.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Eugene Sikaylo on 1/9/2018.
 * email: mogsev@gmail.com
 */

public class FieldItem {

    @SerializedName("id")
    private String id;

    @SerializedName("option")
    private Option option;

    public FieldItem() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "FieldItem{" +
                "id=" + id +
                ", option=" + option +
                '}';
    }

}
