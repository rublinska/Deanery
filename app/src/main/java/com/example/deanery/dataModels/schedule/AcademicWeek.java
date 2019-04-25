package com.example.deanery.dataModels.schedule;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.deanery.dataModels.common.DeaneryDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcademicWeek extends DeaneryDto implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("week_num")
    @Expose
    private Integer weekNum;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("semester")
    @Expose
    private String semester;

    public AcademicWeek(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<AcademicWeek> CREATOR = new Creator<AcademicWeek>() {
        @Override
        public AcademicWeek createFromParcel(Parcel in) {
            return new AcademicWeek(in);
        }

        @Override
        public AcademicWeek[] newArray(int size) {
            return new AcademicWeek[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public Integer getWeekNum() {
        return weekNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return String.format("sem %s, week № %s, %s - %s",
                getSemester(),
                getWeekNum(),
                getStartDate(),
                getEndDate());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
    }
}
