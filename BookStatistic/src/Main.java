/*

 */

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.application.Platform;

import org.jedi_bachelor.view.MainWindow;
import org.jedi_bachelor.view.SplashScreen;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.show();

        // Имитируем загрузку в фоновом потоке
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                // Имитация загрузки ресурсов
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(30);
                    final int progress = i;
                    Platform.runLater(() -> splashScreen.updateProgress(progress / 100.0));
                }
                return null;
            }
        };

        task.setOnSucceeded(e -> {
            splashScreen.close();
            MainWindow mainWindow = new MainWindow();
        });

        new Thread(task).start();
    }
}