
package com.example.deanery.dataModels.specialty;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllSpecialties {

    @SerializedName("data")
    @Expose
    private List<Specialty> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<Specialty> getData() {
        return data;
    }

    public void setData(List<Specialty> data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
