package com.miauau.platform.utils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    private MapUtils() {}

    public static String tryGetStringValue(Map<String, Object> map, String key) {
        try {
            return (String) map.get(key);
        } catch (Exception ex) {
            return "";
        }
    }

    public static boolean tryGetBooleanValue(Map<String, ?> map, String key) {
        try {
            return (boolean) map.get(key);
        } catch (Exception ex) {
            return false;
        }
    }

    public static int tryGetIntValue(Map<String, Object> map, String key) {
        try {
            return (int) map.get(key);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static LocalDate tryGetLocalDateValue(Map<String, Object> map, String key) {
        try {
            return (LocalDate) map.get(key);
        } catch (Exception ex) {
            return LocalDate.MIN;
        }
    }

    public static Map<String, Object> tryGetMapValue(Map<String, Object> map, String key) {
        try {
            return (Map<String, Object>) map.get(key);
        } catch (Exception ex) {
            return new HashMap<>();
        }
    }
}
