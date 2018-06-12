package org.trustnote.activity.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static final String dayPattern = "yyyy-MM-dd";

    public static final String secondPattern = "yyyy-MM-dd HH:mm:ss";

    public static Long localDateTimeParseLong(final LocalDateTime localDateTime) {
        return DateTimeUtils.localDateTimeParseLong(localDateTime, "+8");
    }

    public static Long localDateTimeParseLong(final LocalDateTime localDateTime, final String zone) {
        Long result = 0L;
        if (localDateTime != null) {
            result = localDateTime.toInstant(ZoneOffset.of(zone)).toEpochMilli();
        }
        return result;
    }

    public static LocalDateTime longParseLocalDateTime(final Long timestamp) {
        return DateTimeUtils.longParseLocalDateTime(timestamp, "+8");
    }

    public static LocalDateTime longParseLocalDateTime(final Long timestamp, final String zone) {
        LocalDateTime result = null;
        if (timestamp != null) {
            result = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of(zone));
        }
        return result;
    }

    public static String formatDateTime(final LocalDateTime time, final String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatDateTime(final String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

}
