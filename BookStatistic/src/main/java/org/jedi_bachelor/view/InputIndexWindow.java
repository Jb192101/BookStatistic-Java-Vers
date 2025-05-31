package org.jedi_bachelor.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import org.jedi_bachelor.viewmodel.InputIndexViewModel;
import org.jedi_bachelor.viewmodel.MainViewModel;

public class InputIndexWindow extends View {
    private InputIndexViewModel iivm;
    private MainViewModel mvm;

    public InputIndexWindow(InputIndexViewModel _iivm, MainViewModel _mvm) {
        this.iivm = _iivm;
        this.mvm = _mvm;

        Label label = new Label("Введите индекс книги:");
        TextField textField = new TextField();
        textField.setPromptText("Введите число здесь...");

        Button closeButton = new Button("Редактировать...");
        closeButton.setOnAction(e -> {
            iivm.setIndex(Integer.parseInt(textField.getText()));
        });

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(label, textField, closeButton);

        Scene scene = new Scene(root, 300, 100);

        initStyle(StageStyle.UTILITY);

        setTitle("Ввод индекса");
        setScene(scene);
        setResizable(false);
    }
}