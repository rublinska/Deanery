package com.example.deanery.dataModels.schedule;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.deanery.dataModels.auditory.Auditory;
import com.example.deanery.dataModels.common.DeaneryDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleItem extends DeaneryDto implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("week_day")
    @Expose
    private Integer weekDay;
    @SerializedName("academic_week")
    @Expose
    private AcademicWeek academicWeek;
    @SerializedName("auditory")
    @Expose
    private Auditory auditory;
    @SerializedName("class_time")
    @Expose
    private ClassTime classTime;
    @SerializedName("university_class")
    @Expose
    private UniversityClass universityClass;
    @SerializedName("group")
    @Expose
    private Group group;

    public ScheduleItem(Parcel o) {
        this.id = o.readInt();
        this.weekDay = o.readInt();
        this.academicWeek = o.readParcelable(AcademicWeek.class.getClassLoader());
        this.auditory = o.readParcelable(Auditory.class.getClassLoader());
        this.classTime= o.readParcelable(ClassTime.class.getClassLoader());
        this.universityClass = new UniversityClass(o.readInt());
        this.group = new Group(o.readInt());
        //this.universityClass = o.readParcelable(UniversityClass.class.getClassLoader());
        //this.group = o.readParcelable(Group.class.getClassLoader());
    }

    public Integer getId() {
        return id;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public AcademicWeek getAcademicWeek() {
        return academicWeek;
    }

    public Auditory getAuditory() {
        return auditory;
    }

    public ClassTime getClassTime() {
        return classTime;
    }

    public UniversityClass getUniversityClass() {
        return universityClass;
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ScheduleItem{" +
                "id=" + id +
                ", weekDay=" + weekDay +
                ", academicWeek=" + academicWeek +
                ", auditory=" + auditory +
                ", classTime=" + classTime +
                '}';
    }

    public static final Parcelable.Creator<ScheduleItem> CREATOR = new Parcelable.Creator<ScheduleItem>() {
        @Override
        public ScheduleItem createFromParcel(Parcel in) {
            return new ScheduleItem(in);
        }

        @Override
        public ScheduleItem[] newArray(int size) {
            return new ScheduleItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(weekDay);
        dest.writeParcelable(academicWeek, flags);
        dest.writeParcelable(auditory, flags);
        dest.writeParcelable(classTime, flags);
        dest.writeInt(universityClass.getId());
        dest.writeInt(group.getId());
        //dest.writeParcelable(universityClass, flags);
        //dest.writeParcelable(group, flags);
    }
}
