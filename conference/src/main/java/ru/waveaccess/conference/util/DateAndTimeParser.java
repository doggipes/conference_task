package ru.waveaccess.conference.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTimeParser {

    public static Timestamp parseStringToTimestamp(String timeAsString){
        String pattern = "yyyy-MM-dd HH:mm";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(timeAsString.replace("T", " ")));

        return Timestamp.valueOf(localDateTime);
    }
}
