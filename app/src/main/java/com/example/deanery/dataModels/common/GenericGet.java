package com.example.deanery.dataModels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenericGet<T> {

    @SerializedName("data")
    @Expose
    private List<T> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
