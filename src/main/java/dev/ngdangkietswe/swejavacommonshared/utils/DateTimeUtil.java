package dev.ngdangkietswe.swejavacommonshared.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author ngdangkietswe
 * @since 2/26/2025
 */

public class DateTimeUtil {

    public static final String YYYY_MM_DD_PATTERN = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String HH_MM_SS_PATTERN = "HH:mm:ss";

    public static DateTimeFormatter createDateTimeFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

    public static ZoneId getZoneId(String timezone) {
        return StringUtils.isEmpty(timezone) ? getDefaultZoneId() : ZoneId.of(timezone);
    }

    public static ZoneId getDefaultZoneId() {
        return ZoneId.systemDefault();
    }

    public static String timestampAsStringWithDefaultZone(Timestamp timestamp) {
        return Objects.nonNull(timestamp) ? timestampAsStringWithDefaultZone(timestamp, HH_MM_SS_PATTERN) : EMPTY;
    }

    public static String timestampAsStringWithDefaultZone(Timestamp timestamp, String pattern) {
        return timestamp.toInstant().atZone(getDefaultZoneId())
                .format(createDateTimeFormatter(pattern));
    }

    public static String timestampAsStringWithDefaultZoneAndFormat(Timestamp timestamp) {
        return timestampAsStringWithDefaultZone(timestamp, HH_MM_SS_PATTERN);
    }

    public static String timestampAsString(Timestamp timestamp, ZoneId zoneId) {
        return timestampAsString(timestamp, HH_MM_SS_PATTERN, zoneId);
    }

    public static String timestampAsString(Timestamp timestamp, String pattern, ZoneId zoneId) {
        return Objects.nonNull(timestamp)
                ? timestamp.toInstant().atZone(zoneId).format(createDateTimeFormatter(pattern))
                : EMPTY;
    }

    public static Date firstDateOfMonth() {
        return Date.valueOf(LocalDate.now().withDayOfMonth(1));
    }

    public static Date lastDateOfMonth() {
        return Date.valueOf(LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()));
    }
}
