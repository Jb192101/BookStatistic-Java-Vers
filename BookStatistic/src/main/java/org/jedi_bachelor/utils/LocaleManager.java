package org.jedi_bachelor.utils;

/*
Класс для установки локализации на приложение
 */

import lombok.Getter;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleManager {
    private static ResourceBundle bundle;
    @Getter
    private static Locale currentLocale;

    public static void setLocale(Locale locale) {
        currentLocale = locale;
        System.out.println(locale);
        bundle = ResourceBundle.getBundle("local", locale);
    }

    public static ResourceBundle getBundle() {
        if (bundle == null) {
            setLocale(Locale.getDefault());
        }
        return bundle;
    }

    public static String getString(String key) {
        return getBundle().getString(key);
    }
}