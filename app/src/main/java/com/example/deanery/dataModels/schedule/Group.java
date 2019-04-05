package com.example.deanery.dataModels.schedule;

import com.example.deanery.dataModels.common.DeaneryDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group extends DeaneryDto {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("group_number")
    @Expose
    private String groupNumber;

    public Integer getId() {
        return id;
    }

    public String getGroupNumber() {
        return groupNumber;
    }
}
