package main.com.jedi_bachelor;

/*
Класс приложения App
 */

import main.com.jedi_bachelor.model.ApplicationContext;
import main.com.jedi_bachelor.view.MainWindow;
import main.com.jedi_bachelor.viewmodel.MainWindowViewModel;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {
    private final MainWindowViewModel mainWVM = new MainWindowViewModel(new ApplicationContext());

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        ResourceBundle bundle = ResourceBundle.getBundle("loc", Locale.of("en"));

        MainWindow mainWindow = new MainWindow(mainWVM, bundle);
        System.out.println(mainWindow.vm.ac.getBooks());
    }
}