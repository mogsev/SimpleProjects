package com.mogsev.simpleprojects.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Eugene Sikaylo on 08.09.2017.
 */

public class User implements Parcelable {

    private int id;
    private String firstName;
    private String lastName;

    private Passport passport;

    public User() {

    }

    protected User(final Parcel parcel) {
//        id = parcel.readInt();
//        firstName = parcel.readString();
//        lastName = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(id);
//        dest.writeParcelable(passport, flags);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
