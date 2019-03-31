package com.example.deanery.dataModels.schedule;

import com.example.deanery.dataModels.auditory.Auditory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("week_day")
    @Expose
    private Integer weekDay;
    @SerializedName("academic_week")
    @Expose
    private AcademicWeek academicWeek;
    @SerializedName("auditory")
    @Expose
    private Auditory auditory;
    @SerializedName("class_time")
    @Expose
    private ClassTime classTime;
    // TODO UniversityClass field; update when db is fixed
    // andlys

    public Integer getId() {
        return id;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public AcademicWeek getAcademicWeek() {
        return academicWeek;
    }

    public Auditory getAuditory() {
        return auditory;
    }

    public ClassTime getClassTime() {
        return classTime;
    }

    @Override
    public String toString() {
        return "ScheduleItem{" +
                "id=" + id +
                ", weekDay=" + weekDay +
                ", academicWeek=" + academicWeek +
                ", auditory=" + auditory +
                ", classTime=" + classTime +
                '}';
    }
}
