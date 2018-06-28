package com.ivan.newtechnologies.time;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class Main {

    public static void main(String[] args) {
        final LocalDateTime current = LocalDateTime.now();

        System.out.print("Curent date: ");
        System.out.println(current);

        System.out.println("Adjusted dates:");

        System.out.printf("%-25s", "firstDayOfMonth: ");
        System.out.println(TemporalAdjusters.firstDayOfMonth().adjustInto(current));

        System.out.printf("%-25s", "firstDayOfNextMonth: ");
        System.out.println(TemporalAdjusters.firstDayOfNextMonth().adjustInto(current));

        System.out.printf("%-25s", "firstDayOfNextYear: ");
        System.out.println(TemporalAdjusters.firstDayOfNextYear().adjustInto(current));

        System.out.printf("%-25s", "lastDayOfMonth: ");
        System.out.println(TemporalAdjusters.lastDayOfMonth().adjustInto(current));

        System.out.printf("%-25s", "lastDayOfYear: ");
        System.out.println(TemporalAdjusters.lastDayOfYear().adjustInto(current));

        System.out.printf("%-25s", "next(MONDAY): ");
        System.out.println(TemporalAdjusters.next(DayOfWeek.MONDAY).adjustInto(current));

        System.out.printf("%-25s", "previous(FRIDAY): ");
        System.out.println(TemporalAdjusters.previous(DayOfWeek.FRIDAY).adjustInto(current));
    }

}
