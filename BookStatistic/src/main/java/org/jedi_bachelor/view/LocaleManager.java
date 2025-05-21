package org.jedi_bachelor.view;

/*
Класс для установки локализации на приложение
 */

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleManager {
    private static ResourceBundle bundle;
    private static Locale currentLocale;

    public static void setLocale(Locale locale) {
        currentLocale = locale;
        bundle = ResourceBundle.getBundle("bundles.local", locale);
    }

    public static ResourceBundle getBundle() {
        if (bundle == null) {
            setLocale(Locale.getDefault());
        }
        return bundle;
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static String getString(String key) {
        return getBundle().getString(key);
    }
}