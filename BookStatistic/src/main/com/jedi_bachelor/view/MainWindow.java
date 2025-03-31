package main.com.jedi_bachelor.view;

/*
Класс MainWindow для главного окна
 */

import main.com.jedi_bachelor.model.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.com.jedi_bachelor.viewmodel.BookViewModel;

import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainWindow extends Stage {
    public BookViewModel vm;
    private final ResourceBundle bundle;

    public MainWindow(BookViewModel _vm, ResourceBundle _bundle) {
        this.vm = _vm;
        this.bundle = _bundle;

        TableView table = showTable();
        HBox buttons = showButtons();

        Stage stage = new Stage();

        VBox root = new VBox();
        root.setSpacing(10);
        root.getChildren().addAll(table, buttons);

        Scene scene = new Scene(root, 601, 400);
        stage.setScene(scene);
        stage.setTitle("BookStatistic Java Edition v.1.0");
        stage.show();
    }

    private TableView showTable() {
        ObservableList<Book> books = FXCollections.observableArrayList(
                vm.getBooks()
        );

        TableView<Book> table = new TableView<>(books);
        table.setPrefWidth(1200);
        table.setPrefHeight(200);

        // Определение столбцов
        TableColumn<Book, String> nameColumn = new TableColumn<Book, String>("Название книги");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("nameOfBook"));
        nameColumn.setPrefWidth(100);
        table.getColumns().add(nameColumn);

        TableColumn<Book, String> authorColumn = new TableColumn<Book, String>("Автор книги");
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("authorOfBook"));
        authorColumn.setPrefWidth(100);
        table.getColumns().add(authorColumn);

        TableColumn<Book, Integer> completePagesColumn = new TableColumn<Book, Integer>("Прочитанные страницы");
        completePagesColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("completePages"));
        completePagesColumn.setPrefWidth(100);
        table.getColumns().add(completePagesColumn);

        TableColumn<Book, Integer> allPagesColumn = new TableColumn<Book, Integer>("Всего страниц");
        allPagesColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("allPages"));
        allPagesColumn.setPrefWidth(100);
        table.getColumns().add(allPagesColumn);

        TableColumn<Book, LocalDate> dataStartColumn = new TableColumn<Book, LocalDate>("Старт");
        dataStartColumn.setCellValueFactory(new PropertyValueFactory<Book, LocalDate>("startDate"));
        dataStartColumn.setPrefWidth(100);
        table.getColumns().add(dataStartColumn);

        TableColumn<Book, LocalDate> dataEndColumn = new TableColumn<Book, LocalDate>("Конец");
        dataEndColumn.setCellValueFactory(new PropertyValueFactory<Book, LocalDate>("finishDate"));
        dataEndColumn.setPrefWidth(100);
        table.getColumns().add(dataEndColumn);
        //

        //
        return table;
    }

    private HBox showButtons() {
        HBox buttonPanel = new HBox();
        buttonPanel.setSpacing(20);

        // Создаем 4 кнопки
        Button addBookButton = new Button(this.bundle.getString("buttonAddBook"));
        Button changeDataButton = new Button(this.bundle.getString("buttonChangeData"));
        Button monthStatButton = new Button(this.bundle.getString("buttonMonthStat"));
        Button tempsStatButton = new Button(this.bundle.getString("buttonTempsStat"));

        // Функционал кнопок
        addBookButton.setOnAction(e -> vm.addBook());
        changeDataButton.setOnAction(e -> vm.changeData());
        monthStatButton.setOnAction(e -> vm.showStatMonth());
        tempsStatButton.setOnAction(e -> vm.showStatTemps());

        // Добавляем кнопки на панель
        buttonPanel.getChildren().addAll(addBookButton, changeDataButton, monthStatButton, tempsStatButton);
        return buttonPanel;
    }
}
