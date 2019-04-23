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
        this.id = (Integer) o.readValue(Integer.class.getClassLoader());
        this.weekDay = (Integer) o.readValue(Integer.class.getClassLoader());
        this.academicWeek = (AcademicWeek) o.readValue(AcademicWeek.class.getClassLoader());
        this.auditory = (Auditory) o.readValue(Auditory.class.getClassLoader());
        this.classTime= (ClassTime) o.readValue(ClassTime.class.getClassLoader());
        this.universityClass = (UniversityClass) o.readValue(UniversityClass.class.getClassLoader());
        this.group = (Group) o.readValue(Group.class.getClassLoader());
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

    public static final Parcelable.Creator<TimeSlot> CREATOR = new Parcelable.Creator<TimeSlot>() {
        @Override
        public TimeSlot createFromParcel(Parcel in) {
            return new TimeSlot(in);
        }

        @Override
        public TimeSlot[] newArray(int size) {
            return new TimeSlot[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(weekDay);
        dest.writeValue(academicWeek);
        dest.writeValue(auditory);
        dest.writeValue(classTime);
        dest.writeValue(universityClass);
        dest.writeValue(group);
    }
}
