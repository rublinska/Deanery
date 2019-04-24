
package com.example.deanery.dataModels.auditory;

import com.example.deanery.dataModels.common.DeaneryDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Auditory extends DeaneryDto implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("building_number")
    @Expose
    private String buildingNumber;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;
    @SerializedName("department_id")
    @Expose
    private Integer departmentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
        return getLocation();
    }

    public String getLocation() {
        return String.format("%s - %s (places: %s)",
                getBuildingNumber(),
                getNumber(),
                getCapacity());
    }
}
