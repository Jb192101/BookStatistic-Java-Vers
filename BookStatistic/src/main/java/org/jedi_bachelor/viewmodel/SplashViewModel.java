package org.jedi_bachelor.viewmodel;

import javafx.application.Platform;
import javafx.concurrent.Task;
import org.jedi_bachelor.view.SplashScreen;

public class SplashViewModel implements InteractWindowsInterface{
    private final SplashScreen splashScreen;
    private final MainViewModel mvm;

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
                    if(i < 70)
                        Thread.sleep(30);
                    else
                        Thread.sleep(60);
                    final int progress = i;
                    Platform.runLater(() -> splashScreen.updateProgress(progress / 100.0));
                }
                Thread.sleep(1000);
                return null;
            }
        };

        task.setOnSucceeded(e -> {
            this.closeWindow();
            mvm.openWindow();
        });

        new Thread(task).start();
    }

    @Override
    public void closeWindow() {
        splashScreen.close();
    }
}
