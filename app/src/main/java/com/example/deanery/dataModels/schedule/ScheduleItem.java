package com.example.deanery.dataModels.schedule;

import android.os.Parcel;
import android.os.Parcelable;

public class ScheduleItem implements Parcelable {
    private String timeInterval;
    private String lecturer;
    private String group;
    private String discipline;

    public ScheduleItem(String timeInterval, String lecturer, String group, String discipline) {
        this.timeInterval = timeInterval;
        this.lecturer = lecturer;
        this.group = group;
        this.discipline = discipline;
    }

    protected ScheduleItem(Parcel in) {
        timeInterval = in.readString();
        lecturer = in.readString();
        group = in.readString();
        discipline = in.readString();
    }

    public static final Creator<ScheduleItem> CREATOR = new Creator<ScheduleItem>() {
        @Override
        public ScheduleItem createFromParcel(Parcel in) {
            return new ScheduleItem(in);
        }

        @Override
        public ScheduleItem[] newArray(int size) {
            return new ScheduleItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0; // andlys
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // andlys
        dest.writeString(timeInterval);
        dest.writeString(lecturer);
        dest.writeString(group);
        dest.writeString(discipline);
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeIntervalStr) {
        this.timeInterval = timeIntervalStr;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

}
