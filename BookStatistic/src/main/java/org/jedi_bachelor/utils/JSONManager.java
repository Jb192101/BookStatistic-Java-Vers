package org.jedi_bachelor.utils;

import com.google.gson.Gson;
import org.jedi_bachelor.other.AppSettings;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JSONManager {
    private static final String SETTINGS_PATH = "config/settings.json";
    private static AppSettings settings;

    public static AppSettings loadSettings() {
        if (settings != null) return settings;

        try {
            Path path = Paths.get(SETTINGS_PATH);
            if (Files.exists(path)) {
                Gson gson = new Gson();
                settings = gson.fromJson(new FileReader(SETTINGS_PATH), AppSettings.class);
            } else {
                settings = new AppSettings();
                settings.setLanguage("ru");
                saveSettings(settings);
            }
        } catch (Exception e) {
            e.printStackTrace();
            settings = new AppSettings();
        }
        return settings;
    }

    public static void saveSettings(AppSettings newSettings) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(newSettings);
            Files.createDirectories(Paths.get("config"));
            try (FileWriter writer = new FileWriter(SETTINGS_PATH)) {
                writer.write(json);
            }
            settings = newSettings;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
