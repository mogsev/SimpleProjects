package bookieblowout.com.testrestapi.network.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by Eugene Sikaylo on 12/19/2017.
 * email: mogsev@gmail.com
 */

public class CarMark {

    @SerializedName("id_car_mark")
    private int id;

    @SerializedName("name")
    private String name;

    public CarMark() {

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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarMark carMark = (CarMark) o;

        return id == carMark.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
