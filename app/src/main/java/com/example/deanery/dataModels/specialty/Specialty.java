
package com.example.deanery.dataModels.specialty;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.example.deanery.dataModels.common.DeaneryDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specialty extends DeaneryDto implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Parcelable.Creator<Specialty> CREATOR = new Creator<Specialty>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Specialty createFromParcel(Parcel in) {
            return new Specialty(in);
        }

        public Specialty[] newArray(int size) {
            return (new Specialty[size]);
        }

    }
    ;

    protected Specialty(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Specialty() {
    }

    /**
     * 
     * @param updatedAt
     * @param id
     * @param createdAt
     * @param name
     */
    public Specialty(Integer id, String name, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    public int describeContents() {
        return  0;
    }

    public String toString() {
        return getName();
    }
}
