package org.jedi_bachelor.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.viewmodel.BookViewModel;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainWindow extends Stage {
    private BookViewModel bvm;

    public MainWindow() {
        BorderPane root = new BorderPane();
        this.bvm = new BookViewModel();

        HBox topPanel = new HBox();
        topPanel.setPadding(new Insets(10));
        topPanel.setAlignment(Pos.TOP_RIGHT);

        Button aboutButton = new Button("О проекте");
        aboutButton.setOnAction(e ->
            System.out.println("Кнопка 'О проекте' нажата")
        );

        topPanel.getChildren().add(aboutButton);
        root.setTop(topPanel);

        TableView<Book> table = new TableView<>();

        TableColumn<Book, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> titleColumn = new TableColumn<>("Название книги");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("nameOfBook"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Авторство книги");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("authorOfBook"));

        TableColumn<Book, Integer> pagesReadColumn = new TableColumn<>("Прочитано страниц");
        pagesReadColumn.setCellValueFactory(new PropertyValueFactory<>("completePages"));

        TableColumn<Book, Integer> totalPagesColumn = new TableColumn<>("Всего страниц");
        totalPagesColumn.setCellValueFactory(new PropertyValueFactory<>("allPages"));

        TableColumn<Book, LocalDate> startDateColumn = new TableColumn<>("Дата начала чтения");
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn<Book, LocalDate> endDateColumn = new TableColumn<>("Дата окончания чтения");
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("finishDate"));

        TableColumn<Book, Float> procentColumn = new TableColumn<>("% прочитан.");
        procentColumn.setCellValueFactory(new PropertyValueFactory<>("procentOfReaded"));

        table.getColumns().addAll(idColumn, titleColumn, authorColumn, pagesReadColumn,
                totalPagesColumn, startDateColumn, endDateColumn, procentColumn);

        // Заполнение таблицы
        ObservableList<Book> data = FXCollections.observableArrayList();
        fillingTable(data);
        table.setItems(data);

        root.setCenter(table);

        HBox buttonPanel = new HBox(10);
        buttonPanel.setPadding(new Insets(10));
        buttonPanel.setAlignment(Pos.CENTER);

        Button addButton = new Button("Добавить");
        Button editButton = new Button("Редактировать");
        Button deleteButton = new Button("Удалить");
        Button statsButton = new Button("Статистика");

        addButton.setOnAction(e -> {
            bvm.openInputDataWindow();
            fillingTable(data);});

        buttonPanel.getChildren().addAll(addButton, editButton, deleteButton, statsButton);

        HBox bottomPanel = new HBox();
        bottomPanel.setPadding(new Insets(10));
        bottomPanel.setAlignment(Pos.BOTTOM_RIGHT);

        Label booksCountLabel = new Label("Прочитано книг: " + data.size());
        bottomPanel.getChildren().add(booksCountLabel);

        VBox bottomContainer = new VBox(buttonPanel);
        root.setBottom(bottomContainer);

        Stage stage = new Stage();
        Scene scene = new Scene(root, 850, 600);
        stage.setTitle("BookStatistic v1.0");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void fillingTable(ObservableList<Book> _data) {
        _data.clear();
        ArrayList<Book> bookArrayList = bvm.readFromBookModel();

        int count = bookArrayList.size();
        if(count == 0) {
            return;
        }

        for(int i = 0; i < count; i++) {
            _data.add(bookArrayList.get(i));
        }
    }

}
