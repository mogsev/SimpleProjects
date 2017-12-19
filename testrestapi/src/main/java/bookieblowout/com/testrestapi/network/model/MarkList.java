package bookieblowout.com.testrestapi.network.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

/**
 * Created by Eugene Sikaylo on 12/19/2017.
 * email: mogsev@gmail.com
 */

public class MarkList {

    @SerializedName("result")
    private boolean result;

    @SerializedName("items")
    private List<CarMark> items;

    public MarkList() {

    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
