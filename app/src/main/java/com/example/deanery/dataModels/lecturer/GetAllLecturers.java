
package com.example.deanery.dataModels.lecturer;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllLecturers {

    @SerializedName("data")
    @Expose
    private List<Lecturer> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public GetAllLecturers(List<Lecturer> data, Boolean status) {
        this.data = data;
        this.status = status;
    }

    public List<Lecturer> getData() {
        return data;
    }

    public void setData(List<Lecturer> data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}


