package com.mercury.util;

import com.mercury.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataUtils {
    private static DateFormat STANDARD_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static DateFormat timeStampFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static String POST_METHOD = "post";
    private static String PUT_METHOD = "put";
    private static String DELETE_METHOD = "delete";

    public static String formatDate(Date date) {
        return STANDARD_DATE_FORMAT.format(date);
    }

    public static Integer parseInt(String string) throws BadRequestException {
        try {
            return Integer.parseInt(StringUtils.trim(string));
        } catch (NumberFormatException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public static Boolean parseBoolean(String string) throws BadRequestException {
        if (StringUtils.equalsIgnoreCase("true", StringUtils.trim(string))) {
            return true;
        } else if (StringUtils.equalsIgnoreCase("false", StringUtils.trim(string))) {
            return false;
        } else {
            throw new BadRequestException("Unknown boolean value");
        }
    }

    public static boolean isAnyNull(Object... params) {
        for (Object param: params) {
            if (param == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPostMethod(String method) {
        return StringUtils.equalsIgnoreCase(method, POST_METHOD);
    }

    public static boolean isPutMethod(String method) {
        return StringUtils.equalsIgnoreCase(method, PUT_METHOD);
    }

    public static boolean isDeleteMethod(String method) {
        return StringUtils.equalsIgnoreCase(method, DELETE_METHOD);
    }
}
