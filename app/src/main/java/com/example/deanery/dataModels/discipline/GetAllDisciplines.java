
package com.example.deanery.dataModels.discipline;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllDisciplines {

    @SerializedName("data")
    @Expose
    private List<Discipline> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<Discipline> getData() {
        return data;
    }

    public void setData(List<Discipline> data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
