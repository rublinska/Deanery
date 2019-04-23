package com.example.deanery.dataModels.schedule;

import com.example.deanery.dataModels.common.DeaneryDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UniversityClass extends DeaneryDto {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("class_type")
    @Expose
    private String classType;
    @SerializedName("discipline_id")
    @Expose
    private Integer discipline_id;
    @SerializedName("lecturer_id")
    @Expose
    private Integer lecturer_id;

    public Integer getId() {
        return id;
    }

    public Integer getDisciplineId() {
        return discipline_id;
    }

    public String getClassType() {
        return classType;
    }

    public Integer getLecturerId() {
        return lecturer_id;
    }
}
