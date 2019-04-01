package com.example.deanery.populators;

import com.example.deanery.dataModels.schedule.Day;
import com.example.deanery.dataModels.schedule.ScheduleItem;
import com.example.deanery.dataModels.schedule.TimeSlot;

import java.util.List;
import java.util.stream.Collectors;

public class SchedulePopulator {

    private SchedulePopulator() {}

    public static List<Day> populateScheduleDays(final List<ScheduleItem> scheduleItems) {
        return scheduleItems.stream()
                .map(ScheduleItem::getWeekDay)
                .distinct()
                .map(dayNumber -> populateDayOfWeek(scheduleItems, dayNumber))
                .collect(Collectors.toList());
    }

    private static Day populateDayOfWeek(final List<ScheduleItem> scheduleItems,
                                         final Integer weekDayNumber) {
        final List<TimeSlot> timeSlots = scheduleItems.stream()
                .filter(item -> item.getWeekDay().equals(weekDayNumber))
                .map(SchedulePopulator::convertScheduleToTimeSlot)
                .collect(Collectors.toList());
        return new Day(getWeekDayName(weekDayNumber), null);
    }

    private static TimeSlot convertScheduleToTimeSlot(final ScheduleItem item) {
        final TimeSlot result = new TimeSlot();
        result.setAuditory(item.getAuditory().getLocation());
        result.setDiscipline("Some discipline"); // TODO andlys
        result.setLecturer("Some lecturer"); // TODO andlys
        result.setGroup("Some group"); // TODO andlys
        result.setTimeInterval(item.getClassTime().getStart_time()); // TODO andlys
        result.setWeek("1-12w"); // TODO andlys
        return result;
    }

    private static String getWeekDayName(final int weekDayNumber) {
        switch (weekDayNumber) {
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
            case 7: return "Sunday";
            default: throw new IllegalStateException(
                    String.format("Unrecognized day number: %s",
                            String.valueOf(weekDayNumber)));
        }
    }
}
