package com.example.deanery.dataModels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeaneryGet<T> {

    @SerializedName("data")
    @Expose
    private T data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        data.getClass().getTypeName().toLowerCase();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
