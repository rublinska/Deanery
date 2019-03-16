
package com.example.deanery.dataModels;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lecturer implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("department_id")
    @Expose
    private Integer departmentId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("department")
    @Expose
    private Department department = null;

    public Lecturer(String fullName, String position, String phoneNumber, Integer departmentId) {
        this.fullName = fullName;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    protected Lecturer(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        fullName = in.readString();
        position = in.readString();
        phoneNumber = in.readString();
        departmentId = in.readByte() == 0x00 ? null : in.readInt();
        createdAt = in.readString();
        updatedAt = in.readString();
        department = (Department) in.readValue(Department.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(fullName);
        dest.writeString(position);
        dest.writeString(phoneNumber);
        if (departmentId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(departmentId);
        }
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeValue(department);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Lecturer> CREATOR = new Parcelable.Creator<Lecturer>() {
        @Override
        public Lecturer createFromParcel(Parcel in) {
            return new Lecturer(in);
        }

        @Override
        public Lecturer[] newArray(int size) {
            return new Lecturer[size];
        }
    };
}
