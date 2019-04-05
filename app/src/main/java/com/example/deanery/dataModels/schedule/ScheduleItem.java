package com.example.deanery.dataModels.schedule;

import com.example.deanery.dataModels.auditory.Auditory;
import com.example.deanery.dataModels.common.DeaneryDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleItem extends DeaneryDto {

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
    @SerializedName("university_class")
    @Expose
    private UniversityClass universityClass;
    @SerializedName("group")
    @Expose
    private Group group;

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

    public UniversityClass getUniversityClass() {
        return universityClass;
    }

    public Group getGroup() {
        return group;
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
