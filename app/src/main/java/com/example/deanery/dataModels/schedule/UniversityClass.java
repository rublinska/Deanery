package com.example.deanery.dataModels.schedule;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.deanery.dataModels.common.DeaneryDto;
import com.example.deanery.dataModels.discipline.Discipline;
import com.example.deanery.dataModels.lecturer.Lecturer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UniversityClass extends DeaneryDto implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("class_type")
    @Expose
    private String classType;
    @SerializedName("discipline_id")
    @Expose
    private Integer discipline_id;
    @SerializedName("lecturer_id")
    @Expose
    private Integer lecturer_id;

    private Discipline discipline;
    private Lecturer lecturer;

    public UniversityClass(int id ) { this.id = id;}

    public UniversityClass(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<UniversityClass> CREATOR = new Creator<UniversityClass>() {
        @Override
        public UniversityClass createFromParcel(Parcel in) {
            return new UniversityClass(in);
        }

        @Override
        public UniversityClass[] newArray(int size) {
            return new UniversityClass[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public Integer getDisciplineId() {
        return discipline_id;
    }

    public String getClassType() {
        return classType;
    }

    public Integer getLecturerId() {
        return lecturer_id;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
    }

    @Override
    public String toString() {
        return String.format("'%s' (%s), '%s'",
                discipline.getName(),
                classType,
                lecturer.getFullName());
    }
}
