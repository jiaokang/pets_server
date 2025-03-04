package com.lwj.pets.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @Author ：焦康
 * @Date ：Created in 22:27 2025/3/4
 * @Desc ：
 */
public class DateUtils {
    /**
     * 根据间隔月数计算下次时间
     */
    public static LocalDate nextDate(LocalDate currentDate, Integer gap) {
        return currentDate.plusMonths(gap);
    }

    public static int getGap(LocalDate currentDate, LocalDate nextDate) {
        if (nextDate.isBefore(currentDate)) {
            throw new IllegalArgumentException("nextDate must be after currentDate");
        }
        return (int) ChronoUnit.MONTHS.between(currentDate, nextDate);
    }
}
