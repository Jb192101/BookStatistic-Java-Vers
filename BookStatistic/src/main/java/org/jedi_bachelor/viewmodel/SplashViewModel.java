package org.jedi_bachelor.viewmodel;

import javafx.application.Platform;
import javafx.concurrent.Task;
import org.jedi_bachelor.view.SplashScreen;

public class SplashViewModel implements InteractWindowsInterface{
    private SplashScreen splashScreen;
    private MainViewModel mvm;

    public SplashViewModel(MainViewModel _mvm) {
        this.mvm = _mvm;
        splashScreen = new SplashScreen();
    }

    @Override
    public void openWindow() {
        splashScreen.show();
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
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
            mvm.openWindow();
        });

        new Thread(task).start();
    }

    @Override
    public void closeWindow() {
        splashScreen.close();
    }
}
