package com.example.deanery.populators;

import com.example.deanery.dataModels.discipline.Discipline;
import com.example.deanery.dataModels.schedule.ClassTime;
import com.example.deanery.dataModels.schedule.Day;
import com.example.deanery.dataModels.schedule.ScheduleItem;
import com.example.deanery.dataModels.schedule.TimeSlot;
import com.example.deanery.dataModels.schedule.UniversityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SchedulePopulator {

    private static List<Discipline> allDisciplines;

    private SchedulePopulator() {
    }

    public static List<Day> populateScheduleDays(final List<ScheduleItem> scheduleItems,
                                                 final List<Discipline> discs) {
        allDisciplines = discs;
        return scheduleItems.stream()
                .map(ScheduleItem::getWeekDay)
                .distinct()
                .sorted()
                .map(dayNumber -> populateDayOfWeek(scheduleItems, dayNumber))
                .collect(Collectors.toList());
    }

    private static Day populateDayOfWeek(final List<ScheduleItem> scheduleItems,
                                         final Integer weekDayNumber) {
        final List<TimeSlot> timeSlots = scheduleItems.stream()
                .filter(item -> item.getWeekDay().equals(weekDayNumber))
                .map(SchedulePopulator::convertScheduleToTimeSlot)
                .collect(Collectors.toList());
        return new Day(getWeekDayName(weekDayNumber), timeSlots);
    }

    private static TimeSlot convertScheduleToTimeSlot(final ScheduleItem item) {
        final TimeSlot result = new TimeSlot();
        final UniversityClass universityClass = item.getUniversityClass();
        final Discipline discipline = getDisciplineById(universityClass.getDisciplineId());
        result.setDiscipline(String.format("%s (%s)", discipline.getName(), universityClass.getClassType()));
        result.setLecturer("Some lecturer"); // TODO andlys
        result.setGroup(item.getGroup().getGroupNumber());
        result.setTimeInterval(getTimeInterval(item.getClassTime()));
        result.setWeek("1-12w"); // TODO andlys
        result.setAuditory(item.getAuditory().getLocation());
        return result;
    }

    private static String getTimeInterval(ClassTime classTime) {
        final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Function<String, String> dateToTimeStr = time -> {
            String result = "";
            try {
                result = timeFormat.format(dateFormat.parse(time));
            } catch (ParseException e) {
                System.out.println(e);
            }
            return result;
        };
        final String startTime = dateToTimeStr.apply(classTime.getStartTime());
        final String endTime = dateToTimeStr.apply(classTime.getEndTime());
        return String.format("%s - %s", startTime, endTime);
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

    private static Discipline getDisciplineById(final Integer id) {
        return allDisciplines.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .get();
    }
}
