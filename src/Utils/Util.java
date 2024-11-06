package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static String getPrefix(String taskName) {

        return  "[" + taskName + "]" +
                "[" + Thread.currentThread().getName() + "] " +
                "[" + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) + "] " +
                "Start !!!";
    }

}
