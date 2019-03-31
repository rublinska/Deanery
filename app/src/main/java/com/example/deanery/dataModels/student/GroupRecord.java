
package com.example.deanery.dataModels.student;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.deanery.dataModels.common.DeaneryDto;
import com.example.deanery.dataModels.discipline.Discipline;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupRecord extends DeaneryDto implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("student_id")
    @Expose
    private Integer studentId;
    @SerializedName("university_group_id")
    @Expose
    private Integer universityGroupId;
    @SerializedName("discipline_id")
    @Expose
    private Integer disciplineId;
    @SerializedName("student")
    @Expose
    private Student student;
    @SerializedName("university_group")
    @Expose
    private UniversityGroup universityGroup;
    @SerializedName("Discipline")
    @Expose
    private Discipline discipline;
    public final static Parcelable.Creator<GroupRecord> CREATOR = new Creator<GroupRecord>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GroupRecord createFromParcel(Parcel in) {
            return new GroupRecord(in);
        }

        public GroupRecord[] newArray(int size) {
            return (new GroupRecord[size]);
        }

    }
    ;

    protected GroupRecord(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.studentId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.universityGroupId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.disciplineId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.student = ((Student) in.readValue((Student.class.getClassLoader())));
        this.universityGroup = ((UniversityGroup) in.readValue((UniversityGroup.class.getClassLoader())));
        this.discipline = ((Discipline) in.readValue((Discipline.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public GroupRecord() {
    }

    /**
     * 
     * @param updatedAt
     * @param id
     * @param student
     * @param studentId
     * @param createdAt
     * @param universityGroupId
     * @param disciplineId
     * @param discipline
     * @param universityGroup
     */
    public GroupRecord(Integer id, Integer studentId, Integer universityGroupId, Integer disciplineId, String createdAt, String updatedAt, Student student, UniversityGroup universityGroup, Discipline discipline) {
        super();
        this.id = id;
        this.studentId = studentId;
        this.universityGroupId = universityGroupId;
        this.disciplineId = disciplineId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.student = student;
        this.universityGroup = universityGroup;
        this.discipline = discipline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getUniversityGroupId() {
        return universityGroupId;
    }

    public void setUniversityGroupId(Integer universityGroupId) {
        this.universityGroupId = universityGroupId;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public UniversityGroup getUniversityGroup() {
        return universityGroup;
    }

    public void setUniversityGroup(UniversityGroup universityGroup) {
        this.universityGroup = universityGroup;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(studentId);
        dest.writeValue(universityGroupId);
        dest.writeValue(disciplineId);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(student);
        dest.writeValue(universityGroup);
        dest.writeValue(discipline);
    }

    public int describeContents() {
        return  0;
    }

}
