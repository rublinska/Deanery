package com.example.deanery.dataModels.schedule;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.deanery.dataModels.common.DeaneryDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group extends DeaneryDto implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("group_number")
    @Expose
    private String groupNumber;

    public Group(int id ) { this.id = id;}

    public Group(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getGroupNumber() {
        return groupNumber;
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
