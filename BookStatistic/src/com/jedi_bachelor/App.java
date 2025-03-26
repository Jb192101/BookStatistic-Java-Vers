package com.jedi_bachelor;

/*
Класс приложения App
 */

import com.jedi_bachelor.view.MainWindow;
import com.jedi_bachelor.viewmodel.MainWindowViewModel;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow(new MainWindowViewModel());
    }
}