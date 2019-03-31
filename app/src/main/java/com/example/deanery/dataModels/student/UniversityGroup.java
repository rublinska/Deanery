
package com.example.deanery.dataModels.student;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.deanery.dataModels.common.DeaneryDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UniversityGroup extends DeaneryDto implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("group_number")
    @Expose
    private String groupNumber;

    public final static Parcelable.Creator<UniversityGroup> CREATOR = new Creator<UniversityGroup>() {


        @SuppressWarnings({
            "unchecked"
        })
        public UniversityGroup createFromParcel(Parcel in) {
            return new UniversityGroup(in);
        }

        public UniversityGroup[] newArray(int size) {
            return (new UniversityGroup[size]);
        }

    }
    ;

    protected UniversityGroup(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.groupNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public UniversityGroup() {
    }

    /**
     * 
     * @param updatedAt
     * @param id
     * @param createdAt
     * @param groupNumber
     */
    public UniversityGroup(Integer id, String groupNumber, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.groupNumber = groupNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
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
        dest.writeValue(groupNumber);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    public int describeContents() {
        return  0;
    }

}
