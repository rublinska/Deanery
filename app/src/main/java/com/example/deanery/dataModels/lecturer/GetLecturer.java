
package com.example.deanery.dataModels.lecturer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLecturer {

    @SerializedName("data")
    @Expose
    private Lecturer lecturer;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public GetLecturer(Lecturer lecturer, Boolean status) {
        this.lecturer = lecturer;
        this.status = status;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
