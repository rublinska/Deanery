package com.example.deanery.dataModels.schedule;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.deanery.dataModels.common.DeaneryDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassTime extends DeaneryDto implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;

    public ClassTime(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<ClassTime> CREATOR = new Creator<ClassTime>() {
        @Override
        public ClassTime createFromParcel(Parcel in) {
            return new ClassTime(in);
        }

        @Override
        public ClassTime[] newArray(int size) {
            return new ClassTime[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", getStartTime(), getEndTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        id = dest.readInt();
    }
}
