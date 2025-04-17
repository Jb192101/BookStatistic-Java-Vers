package org.jedi_bachelor.view;

import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class SplashScreen {
    private final String PATH_TO_IMAGE = "src/resources/splash.png";

    private Stage splashStage;
    private ProgressBar progressBar;

    public void show() {
        ImageView splashImage = new ImageView(new Image(new File(PATH_TO_IMAGE).toURI().toString()));
        splashImage.setFitWidth(400);
        splashImage.setFitHeight(300);
        splashImage.setPreserveRatio(true);

        progressBar = new ProgressBar();
        progressBar.setPrefWidth(400);

        VBox root = new VBox(progressBar);
        root.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-alignment: center;");

        Scene scene = new Scene(root);

        splashStage = new Stage();
        splashStage.initStyle(StageStyle.UNDECORATED);
        splashStage.setScene(scene);
        splashStage.show();
    }

    public void updateProgress(double progress) {
        progressBar.setProgress(progress);
    }

    public void close() {
        splashStage.close();
    }
}