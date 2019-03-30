package com.example.deanery.dataModels.schedule;

import android.os.Parcel;
import android.os.Parcelable;

public class TimeSlot implements Parcelable {
    private String timeInterval;
    private String lecturer;
    private String group;
    private String discipline;

    public TimeSlot(String timeInterval, String lecturer, String group, String discipline) {
        this.timeInterval = timeInterval;
        this.lecturer = lecturer;
        this.group = group;
        this.discipline = discipline;
    }

    protected TimeSlot(Parcel in) {
        timeInterval = in.readString();
        lecturer = in.readString();
        group = in.readString();
        discipline = in.readString();
    }

    public static final Creator<TimeSlot> CREATOR = new Creator<TimeSlot>() {
        @Override
        public TimeSlot createFromParcel(Parcel in) {
            return new TimeSlot(in);
        }

        @Override
        public TimeSlot[] newArray(int size) {
            return new TimeSlot[size];
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
