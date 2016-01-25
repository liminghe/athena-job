package com.vip.athena.job.util;

import com.google.common.base.Preconditions;

import java.util.Properties;

/**
 * @author waying.he
 */
public class PropertiesHelper {

    private Properties properties;

    public PropertiesHelper(Properties properties) {
        Preconditions.checkArgument(properties != null, "properties must not be null");
        this.properties = properties;
    }


    public String getString(String key) {
        return getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        String value = properties.getProperty(key, defaultValue);
        if (value == null) {
            return defaultValue;
        }
        value = value.trim();
        return value.length() == 0 ? defaultValue : value;
    }

    public int getInt(String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return parseInt(key, value);
    }

    public int getInt(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new NumberFormatException(String.format("key is %s, but value is null", key));
        }
        return parseInt(key, value);
    }

    private int parseInt(String key, String value) {
        value = value.trim();
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(String.format("key is %s, but value is %s", key, value));
        }
    }


}
