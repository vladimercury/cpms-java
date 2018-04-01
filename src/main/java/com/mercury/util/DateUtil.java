package com.mercury.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static DateFormat standardFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static DateFormat timeStampFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String format(Date date) {
        return standardFormat.format(date);
    }
}
