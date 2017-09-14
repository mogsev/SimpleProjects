package com.mogsev.simpleprojects.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Eugene Sikaylo on 08.09.2017.
 */

public class Passport implements Parcelable {

    private String id;

    protected Passport(Parcel parcel) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Creator<Passport> CREATOR = new Creator<Passport>() {
        @Override
        public Passport createFromParcel(Parcel in) {
            return new Passport(in);
        }

        @Override
        public Passport[] newArray(int size) {
            return new Passport[size];
        }
    };


}
