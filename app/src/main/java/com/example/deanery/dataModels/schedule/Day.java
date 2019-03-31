package com.example.deanery.dataModels.schedule;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Day {
    private final String dayName;
    private final List<TimeSlot> items;

    public Day(String dayName, List<TimeSlot> items) {
        this.dayName = dayName;
        this.items = items;
        Collections.sort(this.items, new Comparator<TimeSlot>() {
            @Override
            public int compare(TimeSlot slot1, TimeSlot slot2) {
                return slot1.getTimeInterval().compareTo(slot2.getTimeInterval());
            }
        });
    }

    public String getDayName() {
        return dayName;
    }

    public List<TimeSlot> getTimeSlots() {
        return items;
    }

    @Override
    public String toString() {
        return "Day{" +
                "dayName='" + dayName + '\'' +
                ", items=" + items +
                '}';
    }
}
