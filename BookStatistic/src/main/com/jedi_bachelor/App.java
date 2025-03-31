package main.com.jedi_bachelor;

/*
Класс приложения App
 */

import main.com.jedi_bachelor.repository.BookRepository;
import main.com.jedi_bachelor.repository.SQLBookRepository;
import main.com.jedi_bachelor.view.MainWindow;

import javafx.application.Application;
import javafx.stage.Stage;
import main.com.jedi_bachelor.viewmodel.BookViewModel;

import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {
    BookRepository repository = new SQLBookRepository();
    BookViewModel viewModel = new BookViewModel(repository);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        ResourceBundle bundle = ResourceBundle.getBundle("loc", Locale.of("en"));

        MainWindow mainWindow = new MainWindow(viewModel, bundle);
    }
}