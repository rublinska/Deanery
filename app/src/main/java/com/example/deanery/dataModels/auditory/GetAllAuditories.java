
package com.example.deanery.dataModels.auditory;

import com.example.deanery.dataModels.department.Department;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllAuditories {

    @SerializedName("data")
    @Expose
    private List<Auditory> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<Auditory> getData() {
        return data;
    }

    public void setData(List<Auditory> data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
