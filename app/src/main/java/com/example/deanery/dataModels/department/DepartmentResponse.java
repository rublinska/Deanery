
package com.example.deanery.dataModels.department;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartmentResponse {

    @SerializedName("department")
    @Expose
    private Department data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Department getData() {
        return data;
    }

    public void setData(Department data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
