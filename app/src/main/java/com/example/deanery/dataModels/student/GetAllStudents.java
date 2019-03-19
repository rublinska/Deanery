
package com.example.deanery.dataModels.student;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllStudents {

    @SerializedName("data")
    @Expose
    private List<Student> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<Student> getData() {
        return data;
    }

    public void setData(List<Student> data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
