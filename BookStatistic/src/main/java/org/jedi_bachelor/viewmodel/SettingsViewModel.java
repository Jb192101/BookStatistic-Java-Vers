package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.other.AppSettings;
import org.jedi_bachelor.utils.JSONManager;
import org.jedi_bachelor.view.SettingsWindow;

public class SettingsViewModel extends LocalViewModel {
    private MainViewModel mvm;
    private AppSettings settings;
    private JSONManager jsonManager;

    public SettingsViewModel(MainViewModel _mvm) {
        this.mvm = _mvm;
        this.jsonManager = new JSONManager();
        this.settings = JSONManager.loadSettings();

        this.window = new SettingsWindow(this);
    }

    public String getLanguage() {
        switch (settings.getLanguage()) {
            case "ru":
                return "Русский";
            case "en":
                return "English";
            default:
                return "";
        }
    }

    public void setLanguage(String _lan) {
        switch (_lan) {
            case "Русский":
                settings.setLanguage("ru");
                break;
            case "English":
                settings.setLanguage("en");
                break;
        }
        JSONManager.saveSettings(settings);
    }
}
