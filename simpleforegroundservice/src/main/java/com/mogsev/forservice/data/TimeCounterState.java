package com.mogsev.forservice.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Eugene Sikaylo on 26.07.2017.
 */

public class TimeCounterState extends RealmObject {

    @PrimaryKey
    private int id;

    private long millis;
    private boolean isWorking;

    public TimeCounterState() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    @Override
    public String toString() {
        return "TimeCounterModel{" +
                "id=" + id +
                ", millis=" + millis +
                ", isWorking=" + isWorking +
                "} " + super.toString();
    }
}
