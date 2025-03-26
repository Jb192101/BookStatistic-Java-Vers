package com.jedi_bachelor.view;

/*
Класс MainWindow для главного окна
 */

import com.jedi_bachelor.model.Book;
import com.jedi_bachelor.viewmodel.MainWindowViewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow extends Stage {
    MainWindowViewModel vm;

    public MainWindow(MainWindowViewModel vm) {
        this.vm = vm;

        FlowPane root1 = showTable();

        Stage stage = new Stage();
        Label label = new Label("Some text");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root1, 1200, 700);
        stage.setScene(scene);
        stage.setTitle("BookStatistic Java Edition v.1.0");
        stage.show();
    }

    private FlowPane showTable() {
        ObservableList<Book> books = FXCollections.observableArrayList(
                new Book("Sieg", "А. Гитлер", 666, 1488),
                new Book("ааа", "ооо", 12, 13)
        );

        TableView<Book> table = new TableView<>(books);
        table.setPrefWidth(1200);
        table.setPrefHeight(200);

        // Определение столбцов
        TableColumn<Book, String> nameColumn = new TableColumn<Book, String>("Название книги");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("nameOfBook"));
        table.getColumns().add(nameColumn);

        TableColumn<Book, String> authorColumn = new TableColumn<Book, String>("Автор книги");
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("authorOfBook"));
        table.getColumns().add(authorColumn);

        TableColumn<Book, Integer> completePagesColumn = new TableColumn<Book, Integer>("Прочитанные страницы");
        completePagesColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("completePages"));
        table.getColumns().add(completePagesColumn);

        TableColumn<Book, Integer> allPagesColumn = new TableColumn<Book, Integer>("Всего страниц");
        allPagesColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("allPages"));
        table.getColumns().add(allPagesColumn);
        //

        //
        return new FlowPane(10, 10, table);
    }
}
