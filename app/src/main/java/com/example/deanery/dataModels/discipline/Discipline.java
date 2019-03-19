
package com.example.deanery.dataModels.discipline;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Discipline implements Parcelable
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
    private Integer specialtyId;
    @SerializedName("pre_discipline_id")
    @Expose
    private Object preDisciplineId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
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
        this.preDisciplineId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Discipline() {
    }

    /**
     * 
     * @param id
     * @param selfWorkTime
     * @param name
     */
    public Discipline(Integer id, String name, Integer selfWorkTime) {
        super();
        this.id = id;
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

    public Object getPreDisciplineId() {
        return preDisciplineId;
    }

    public void setPreDisciplineId(Object preDisciplineId) {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(selfWorkTime);
        dest.writeValue(specialtyId);
        dest.writeValue(preDisciplineId);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    public int describeContents() {
        return  0;
    }

}
