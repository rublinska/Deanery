package com.example.deanery.dataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStatus {

    @SerializedName("status")
    @Expose
    private Boolean status;

    public GetStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
