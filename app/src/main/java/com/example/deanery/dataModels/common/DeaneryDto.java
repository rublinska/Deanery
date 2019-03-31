package com.example.deanery.dataModels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeaneryDto {

    @SerializedName("created_at")
    @Expose
    protected String createdAt;
    @SerializedName("updated_at")
    @Expose
    protected String updatedAt;

}
