package com.mogsev.simpleprojects.data.entity;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;
import com.mogsev.simpleprojects.adapter.UsersRvAdapter;

import java.util.List;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class Tariff {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("info")
    private String info;

    @SerializedName("users")
    private List<User> users;

    public Tariff() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", info='" + info + '\'' +
                ", users=" + users +
                '}';
    }
}
