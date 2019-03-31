
package com.example.deanery.dataModels.discipline;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.deanery.dataModels.common.DeaneryDto;
import com.example.deanery.dataModels.specialty.Specialty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Discipline extends DeaneryDto implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("selfWorkTime")
    @Expose
    private Integer selfWorkTime;
    @SerializedName("specialty_id")
    @Expose
    private Integer specialtyId = null;
    @SerializedName("pre_discipline_id")
    @Expose
    private Integer preDisciplineId = null;
    @SerializedName("pre_discipline")
    @Expose
    private Discipline preDiscipline = null;
    @SerializedName("specialty")
    @Expose
    private Specialty specialty = null;
    public final static Parcelable.Creator<Discipline> CREATOR = new Creator<Discipline>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Discipline createFromParcel(Parcel in) {
            return new Discipline(in);
        }

        public Discipline[] newArray(int size) {
            return (new Discipline[size]);
        }

    }
            ;

    protected Discipline(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.selfWorkTime = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.specialtyId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.preDisciplineId = ((Integer) in.readValue((Object.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.preDiscipline = ((Discipline) in.readValue((Discipline.class.getClassLoader())));
        this.specialty = ((Specialty) in.readValue((Specialty.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Discipline() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param selfWorkTime
     * @param createdAt
     * @param name
     * @param specialtyId
     * @param preDisciplineId
     */
    public Discipline(Integer id, String name, Integer selfWorkTime, Integer specialtyId, Integer preDisciplineId, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.name = name;
        this.selfWorkTime = selfWorkTime;
        this.specialtyId = specialtyId;
        this.preDisciplineId = preDisciplineId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Discipline(String name, Integer selfWorkTime) {
        super();
        this.name = name;
        this.selfWorkTime = selfWorkTime;
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

    public Integer getSelfWorkTime() {
        return selfWorkTime;
    }

    public void setSelfWorkTime(Integer selfWorkTime) {
        this.selfWorkTime = selfWorkTime;
    }

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
    }

    public Integer getPreDisciplineId() {
        return preDisciplineId;
    }

    public void setPreDisciplineId(Integer preDisciplineId) {
        this.preDisciplineId = preDisciplineId;
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

    public Discipline getPreDiscipline() {
        return preDiscipline;
    }

    public void setPreDiscipline(Discipline preDiscipline) {
        this.preDiscipline = preDiscipline;
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
        dest.writeValue(selfWorkTime);
        dest.writeValue(specialtyId);
        dest.writeValue(preDisciplineId);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(preDiscipline);
        dest.writeValue(specialty);
    }

    public int describeContents() {
        return  0;
    }

    public String toString() {
        return getName();
    }
}
