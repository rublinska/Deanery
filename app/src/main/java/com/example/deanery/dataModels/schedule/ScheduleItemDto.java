package com.example.deanery.dataModels.schedule;

import com.example.deanery.dataModels.common.DeaneryDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleItemDto extends DeaneryDto {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("class_time_id")
    @Expose
    private Integer classTimeId;
    @SerializedName("academic_week_id")
    @Expose
    private Integer academicWeekId;
    @SerializedName("auditory_id")
    @Expose
    private Integer auditoryId;
    @SerializedName("university_class_id")
    @Expose
    private Integer universityClassId;
    @SerializedName("university_group_id")
    @Expose
    private Integer universityGroupId;
    @SerializedName("week_day")
    @Expose
    private Integer weekDay;

    public ScheduleItemDto(Integer id, Integer classTimeId, Integer academicWeekId, Integer auditoryId, Integer universityClassId, Integer universityGroupId, Integer weekDay) {
        this.id = id;
        this.classTimeId = classTimeId;
        this.academicWeekId = academicWeekId;
        this.auditoryId = auditoryId;
        this.universityClassId = universityClassId;
        this.universityGroupId = universityGroupId;
        this.weekDay = weekDay;
    }

    public ScheduleItemDto(Integer classTimeId, Integer academicWeekId, Integer auditoryId, Integer universityClassId, Integer universityGroupId, Integer weekDay) {
        this.classTimeId = classTimeId;
        this.academicWeekId = academicWeekId;
        this.auditoryId = auditoryId;
        this.universityClassId = universityClassId;
        this.universityGroupId = universityGroupId;
        this.weekDay = weekDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassTimeId() {
        return classTimeId;
    }

    public void setClassTimeId(Integer classTimeId) {
        this.classTimeId = classTimeId;
    }

    public Integer getAcademicWeekId() {
        return academicWeekId;
    }

    public void setAcademicWeekId(Integer academicWeekId) {
        this.academicWeekId = academicWeekId;
    }

    public Integer getAuditoryId() {
        return auditoryId;
    }

    public void setAuditoryId(Integer auditoryId) {
        this.auditoryId = auditoryId;
    }

    public Integer getUniversityClassId() {
        return universityClassId;
    }

    public void setUniversityClassId(Integer universityClassId) {
        this.universityClassId = universityClassId;
    }

    public Integer getUniversityGroupId() {
        return universityGroupId;
    }

    public void setUniversityGroupId(Integer universityGroupId) {
        this.universityGroupId = universityGroupId;
    }

    @Override
    public String toString() {
        return "ScheduleItemDto{" +
                "id=" + id +
                ", classTimeId=" + classTimeId +
                ", academicWeekId=" + academicWeekId +
                ", auditoryId=" + auditoryId +
                ", universityClassId=" + universityClassId +
                ", universityGroupId=" + universityGroupId +
                ", weekDay=" + weekDay +
                '}';
    }
}
