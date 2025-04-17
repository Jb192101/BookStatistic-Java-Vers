package org.jedi_bachelor.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jedi_bachelor.viewmodel.BookViewModel;

public class InputIndexWindow extends Stage {
    private BookViewModel bvm;
    private Stage primaryStage;

    public InputIndexWindow(BookViewModel _bvm) {
        this.bvm = _bvm;

        primaryStage = new Stage();

        Label label = new Label("Введите индекс книги:");
        TextField textField = new TextField();
        textField.setPromptText("Введите число здесь...");

        Button closeButton = new Button("Редактировать...");
        closeButton.setOnAction(e -> {
            bvm.openChangeWindow(Integer.parseInt(textField.getText()), this.bvm);
            primaryStage.close();
        });

        // Настраиваем layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(label, textField, closeButton);

        // Создаем сцену и настраиваем stage
        Scene scene = new Scene(root, 300, 100);
        primaryStage.setTitle("Ввод индекса");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
    }

    public void showAndWait() {
        primaryStage.show();
    }
}
