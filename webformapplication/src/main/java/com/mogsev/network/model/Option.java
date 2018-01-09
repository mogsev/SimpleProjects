package com.mogsev.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Eugene Sikaylo on 1/9/2018.
 * email: mogsev@gmail.com
 */

public class Option {

    @SerializedName("id")
    private int id;

    @SerializedName("value")
    private String value;

    public Option() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
