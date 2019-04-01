package com.example.deanery.dataModels.schedule;

import android.os.Parcel;
import android.os.Parcelable;

public class TimeSlot implements Parcelable {

    private String timeInterval;
    private String lecturer;
    private String group;
    private String discipline;
    private String auditory;
    private String week;

    public TimeSlot() {}

    public TimeSlot(String timeInterval, String lecturer, String group, String discipline, String auditory, String week) {
        this.timeInterval = timeInterval;
        this.lecturer = lecturer;
        this.group = group;
        this.discipline = discipline;
        this.auditory = auditory;
        this.week = week;
    }

    protected TimeSlot(Parcel in) {
        timeInterval = in.readString();
        lecturer = in.readString();
        group = in.readString();
        discipline = in.readString();
        auditory = in.readString();
        week = in.readString();
    }

    public static final Parcelable.Creator<TimeSlot> CREATOR = new Parcelable.Creator<TimeSlot>() {
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
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(timeInterval);
        dest.writeString(lecturer);
        dest.writeString(group);
        dest.writeString(discipline);
        dest.writeString(auditory);
        dest.writeString(week);
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

    public String getAuditory() {
        return auditory;
    }

    public void setAuditory(String auditory) {
        this.auditory = auditory;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "timeInterval='" + timeInterval + '\'' +
                ", lecturer='" + lecturer + '\'' +
                ", group='" + group + '\'' +
                ", discipline='" + discipline + '\'' +
                ", auditory='" + auditory + '\'' +
                ", week='" + week + '\'' +
                '}';
    }
}
