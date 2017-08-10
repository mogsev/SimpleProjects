package com.mogsev.forservice.aidlmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.mogsev.forservice.util.BooleanHelper;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class Punch implements Parcelable {

    public static final String BUNDLE_NAME = Punch.class.getSimpleName();

    private int id;
    private boolean isWorking;
    private boolean isStartOffline;
    private boolean isFinishOffline;
    private long millis;

    public Punch() {

    }

    protected Punch(final Parcel parcel) {
        id = parcel.readInt();
        isWorking = BooleanHelper.toBoolean(parcel.readByte());
        isStartOffline = BooleanHelper.toBoolean(parcel.readByte());
        isFinishOffline = BooleanHelper.toBoolean(parcel.readByte());
        millis = parcel.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeByte(BooleanHelper.toByte(isWorking));
        parcel.writeByte(BooleanHelper.toByte(isStartOffline));
        parcel.writeByte(BooleanHelper.toByte(isFinishOffline));
        parcel.writeLong(millis);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public boolean isStartOffline() {
        return isStartOffline;
    }

    public void setStartOffline(boolean startOffline) {
        isStartOffline = startOffline;
    }

    public boolean isFinishOffline() {
        return isFinishOffline;
    }

    public void setFinishOffline(boolean finishOffline) {
        isFinishOffline = finishOffline;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public static final Creator<Punch> CREATOR = new Creator<Punch>() {
        @Override
        public Punch createFromParcel(Parcel in) {
            return new Punch(in);
        }

        @Override
        public Punch[] newArray(int size) {
            return new Punch[size];
        }
    };

    @Override
    public String toString() {
        return "Punch{" +
                "id=" + id +
                ", isWorking=" + isWorking +
                ", isStartOffline=" + isStartOffline +
                ", isFinishOffline=" + isFinishOffline +
                ", millis=" + millis +
                '}';
    }
}
