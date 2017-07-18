package com.mogsev.simpleprojects.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class Order {

    @SerializedName("id")
    private int id;

    @SerializedName("status")
    private String status;

    @SerializedName("price")
    private String price;

    @SerializedName("description")
    private String description;

    @SerializedName("tariffs")
    private List<Tariff> tariffs;

    public Order() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", tariffs=" + tariffs +
                '}';
    }
}
