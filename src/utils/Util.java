package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static String getPrefix(String taskName, String action) {
        return  "[" + taskName + "]" +
                "[" + Thread.currentThread().getName() + "] " +
                "[" + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + "] " +
                 action + " !!!";
    }
}
