package com.example.deanery.dataModels.schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassTime {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("start_time")
    @Expose
    private String start_time;
//    @SerializedName("end_time")
//    @Expose
//    private String end_time;
    // TODO andlys

    public Integer getId() {
        return id;
    }

    public String getStart_time() {
        return start_time;
    }
}