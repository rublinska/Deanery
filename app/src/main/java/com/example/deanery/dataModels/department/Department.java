package com.example.deanery.dataModels.department;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.deanery.dataModels.auditory.Auditory;
import com.example.deanery.dataModels.common.DeaneryDto;
import com.example.deanery.dataModels.lecturer.Lecturer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Department extends DeaneryDto implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("auditories")
    @Expose
    private List<Auditory> auditories = null;
    @SerializedName("lecturers")
    @Expose
    private List<Lecturer> lecturers = null;

    public Department(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Auditory> getAuditories() {
        return auditories;
    }

    public void setAuditories(List<Auditory> auditories) {
        this.auditories = auditories;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public String toString() {

        return this.getName();
    }

    protected Department(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        name = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        if (in.readByte() == 0x01) {
            auditories = new ArrayList<Auditory>();
            in.readList(auditories, Auditory.class.getClassLoader());
        } else {
            auditories = null;
        }
        if (in.readByte() == 0x01) {
            lecturers = new ArrayList<Lecturer>();
            in.readList(lecturers, Lecturer.class.getClassLoader());
        } else {
            lecturers = null;
        }
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
        dest.writeString(name);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        if (auditories == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(auditories);
        }
        if (lecturers == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(lecturers);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Department> CREATOR = new Parcelable.Creator<Department>() {
        @Override
        public Department createFromParcel(Parcel in) {
            return new Department(in);
        }

        @Override
        public Department[] newArray(int size) {
            return new Department[size];
        }
    };
}