
package com.example.deanery.dataModels.student;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.deanery.dataModels.specialty.Specialty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("start_university")
    @Expose
    private String startUniversity;
    @SerializedName("end_university")
    @Expose
    private String endUniversity;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("end_reason")
    @Expose
    private String endReason;
    @SerializedName("specialty_id")
    @Expose
    private Integer specialtyId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("specialty")
    @Expose
    private Specialty specialty;
    public final static Parcelable.Creator<Student> CREATOR = new Creator<Student>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return (new Student[size]);
        }

    }
    ;

    protected Student(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.startUniversity = ((String) in.readValue((String.class.getClassLoader())));
        this.endUniversity = ((String) in.readValue((String.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.endReason = ((String) in.readValue((String.class.getClassLoader())));
        this.specialtyId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.specialty = ((Specialty) in.readValue((Specialty.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Student() {
    }

    /**
     * 
     * @param updatedAt
     * @param id
     * @param startUniversity
     * @param phone
     * @param endReason
     * @param createdAt
     * @param name
     * @param specialtyId
     * @param endUniversity
     * @param specialty
     */
    public Student(Integer id, String name, String startUniversity, String endUniversity, String phone, String endReason, Integer specialtyId, String createdAt, String updatedAt, Specialty specialty) {
        super();
        this.id = id;
        this.name = name;
        this.startUniversity = startUniversity;
        this.endUniversity = endUniversity;
        this.phone = phone;
        this.endReason = endReason;
        this.specialtyId = specialtyId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.specialty = specialty;
    }


    public Student(String name, String startUniversity, String endUniversity, String phone, String endReason, Integer specialtyId) {
        this.name = name;
        this.startUniversity = startUniversity;
        this.endUniversity = endUniversity;
        this.phone = phone;
        this.endReason = endReason;
        this.specialtyId = specialtyId;
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

    public String getStartUniversity() {
        return startUniversity;
    }

    public void setStartUniversity(String startUniversity) {
        this.startUniversity = startUniversity;
    }

    public String getEndUniversity() {
        return endUniversity;
    }

    public void setEndUniversity(String endUniversity) {
        this.endUniversity = endUniversity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEndReason() {
        return endReason;
    }

    public void setEndReason(String endReason) {
        this.endReason = endReason;
    }

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
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

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(startUniversity);
        dest.writeValue(endUniversity);
        dest.writeValue(phone);
        dest.writeValue(endReason);
        dest.writeValue(specialtyId);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(specialty);
    }

    public int describeContents() {
        return  0;
    }

}
