package com.zycus.validator.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Utils {

    private static final long DAY_MILLIS = 86_400_000L;

    public static LocalDateTime getDateTimeFromExcel(double days, boolean isDate1904) {
        int wholeDays = (int) Math.floor(days);
        long millisInDay = (long) (((days - wholeDays) * DAY_MILLIS) + 0.5D);
        /* Excel has 2 date systems: 1900 and 1904
           Refer: https://support.microsoft.com/en-in/help/214330/differences-between-the-1900-and-the-1904-date-system-in-excel
           Leap year : Divisible by 4. If year is divisible by 100, it must also be divisible by 400.
           So, 1900 is not a leap year (divisible by 100 but not by 400. Divisible by 4 but 2nd rule has more priority)
           Excel thinks 1900 is leap year (compatibility with other programs). Assumes 2/29/1900 is valid. Not for java representation.
           2/29/1900 in excel becomes 1/3/1900 in java. But if days are less than 61 (date prior to 2/29/1900), no adjusting.
         */
        int dayAdjust = wholeDays < 61 ? 0 : -1;
        int startYear = isDate1904 ? 1904 : 1900;
        if (isDate1904)
            dayAdjust = 1; // 1904 system uses 1/2/1904 as first date
        LocalDate localDate = LocalDate.of(startYear, 1, 1).plusDays((long) (wholeDays + dayAdjust - 1));
        LocalTime localTime = LocalTime.ofNanoOfDay(millisInDay * 1_000_000);
        return LocalDateTime.of(localDate, localTime);

    }





}
