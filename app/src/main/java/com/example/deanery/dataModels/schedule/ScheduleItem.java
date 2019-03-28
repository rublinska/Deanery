package com.example.deanery.dataModels.schedule;

public class ScheduleItem {
    private String timeInterval;
    private String lecturer;
    private String group;
    private String discipline;

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeIntervalStr) {
        this.timeInterval = timeIntervalStr;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
}
