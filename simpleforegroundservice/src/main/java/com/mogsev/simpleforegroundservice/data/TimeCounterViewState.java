package com.mogsev.simpleforegroundservice.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Eugene Sikaylo on 27.07.2017.
 */

public class TimeCounterViewState extends RealmObject {

    @PrimaryKey
    private int id;

    private boolean isWorking;
    private boolean isPunchWorking;
    private boolean isPunchStartOffline;
    private boolean isPunchFinishOffline;

    private long millis;
    private String timeStartFormat;

    public TimeCounterViewState() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPunchWorking() {
        return isPunchWorking;
    }

    public void setPunchWorking(boolean boo) {
        isPunchWorking = boo;
    }

    public boolean isPunchStartOffline() {
        return isPunchStartOffline;
    }

    public void setPunchStartOffline(boolean boo) {
        isPunchStartOffline = boo;
    }

    public boolean isPunchFinishOffline() {
        return isPunchFinishOffline;
    }

    public void setPunchFinishOffline(boolean boo) {
        isPunchFinishOffline = boo;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public String getTimeStartFormat() {
        return timeStartFormat;
    }

    public void setTimeStartFormat(String timeStartFormat) {
        this.timeStartFormat = timeStartFormat;
    }

    @Override
    public String toString() {
        return "TimeCounterViewState{" +
                "id=" + id +
                ", isWorking=" + isWorking +
                ", isPunchWorking=" + isPunchWorking +
                ", isPunchStartOffline=" + isPunchStartOffline +
                ", isPunchFinishOffline=" + isPunchFinishOffline +
                ", millis=" + millis +
                ", timeStartFormat='" + timeStartFormat + '\'' +
                "} " + super.toString();
    }
}
