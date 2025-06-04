package org.jedi_bachelor.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import org.jedi_bachelor.utils.LocaleManager;
import org.jedi_bachelor.viewmodel.SettingsViewModel;

public class SettingsWindow extends View {
    private SettingsViewModel svm;
    private ComboBox<String> languageComboBox;

    public SettingsWindow(SettingsViewModel _svm) {
        this.svm = _svm;
        
        setupUI();
    }

    private void setupUI() {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setTitle(LocaleManager.getString("SETTINGS_WINDOW_TITLE"));
        setResizable(false);

        Label languageLabel = new Label(LocaleManager.getString("LANGUAGE_LABEL") + ":");

        languageComboBox = new ComboBox<>();
        languageComboBox.getItems().addAll("Русский", "English");
        languageComboBox.setValue(svm.getLanguage());

        Button saveButton = new Button(LocaleManager.getString("SAVE_BUTTON"));
        saveButton.setOnAction(e -> saveSettings());

        Button cancelButton = new Button(LocaleManager.getString("CANCEL_BUTTON"));
        cancelButton.setOnAction(e -> close());

        HBox languageBox = new HBox(10, languageLabel, languageComboBox);
        languageBox.setAlignment(Pos.CENTER_LEFT);

        HBox buttonBox = new HBox(10, saveButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        VBox root = new VBox(20, languageBox, buttonBox);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root);
        setScene(scene);

        // Устанавливаем размер окна
        setWidth(300);
        setHeight(150);
    }

    private void saveSettings() {
        svm.setLanguage(languageComboBox.getValue());
    }
}