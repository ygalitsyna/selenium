package com.solvd.utils;

import java.util.ResourceBundle;

public class ConfigReader {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
