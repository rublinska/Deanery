
package com.example.deanery.dataModels.auditory;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.deanery.dataModels.common.DeaneryDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Auditory extends DeaneryDto implements Serializable, Parcelable {

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

    public Auditory(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<Auditory> CREATOR = new Creator<Auditory>() {
        @Override
        public Auditory createFromParcel(Parcel in) {
            return new Auditory(in);
        }

        @Override
        public Auditory[] newArray(int size) {
            return new Auditory[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
    }
}
