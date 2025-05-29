package org.jedi_bachelor.view;

import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreen extends Stage {
    private ProgressBar progressBar;

    public SplashScreen() {
        setupUI();
    }

    private void setupUI() {
        String PATH_TO_IMAGE = "/images/splash.png";
        Image image = new Image(getClass().getResourceAsStream(PATH_TO_IMAGE));
        ImageView splashImage = new ImageView(image);
        splashImage.setFitWidth(600);
        splashImage.setFitHeight(400);
        splashImage.setPreserveRatio(true);

        progressBar = new ProgressBar();
        progressBar.setPrefWidth(550);
        progressBar.setTranslateY(120);

        StackPane root = new StackPane(splashImage, progressBar);
        root.setStyle("-fx-background-color: transparent; -fx-padding: 10; -fx-alignment: center;");

        Scene scene = new Scene(root);
        scene.setFill(null);

        initStyle(StageStyle.TRANSPARENT);
        setScene(scene);
    }

    public void updateProgress(double progress) {
        progressBar.setProgress(progress);
    }
}