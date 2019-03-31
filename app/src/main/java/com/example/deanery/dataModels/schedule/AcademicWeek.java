package com.example.deanery.dataModels.schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcademicWeek {
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
        return "AcademicWeek{" +
                "id=" + id +
                ", weekNum=" + weekNum +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }
}
