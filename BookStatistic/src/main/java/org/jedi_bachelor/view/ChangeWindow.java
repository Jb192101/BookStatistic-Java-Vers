package org.jedi_bachelor.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.stage.StageStyle;
import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.viewmodel.BookViewModel;

import java.time.LocalDate;

public class ChangeWindow {
    private BookViewModel bvm;
    private Book searchingBook;

    protected Stage stage;
    private Book resultBook;
    protected final TextField titleField = new TextField();
    protected final TextField authorField = new TextField();
    protected Spinner<Integer> pagesReadSpinner;
    protected Spinner<Integer> totalPagesSpinner;
    protected final Label errorLabel = new Label();
    protected Button addButton;

    public ChangeWindow(int _index, BookViewModel _bvm) {
        this.bvm = _bvm;
        searchingBook = bvm.searchBookByID(_index);
        stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Редактировать книгу");
        stage.initStyle(StageStyle.UTILITY);

        setupSpinners();
        setupUI();
        setupValidation();

        fillingFields();
    }

    private void fillingFields() {
        this.titleField.setText(searchingBook.getNameOfBook());
        this.authorField.setText(searchingBook.getAuthorOfBook());
    }

    protected void setupSpinners() {
        this.pagesReadSpinner = new Spinner<>();
        this.totalPagesSpinner = new Spinner<>();

        SpinnerValueFactory.IntegerSpinnerValueFactory factory1 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, searchingBook.getCompletePages());

        pagesReadSpinner.setValueFactory(factory1);

        SpinnerValueFactory.IntegerSpinnerValueFactory factory2 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, searchingBook.getAllPages());

        totalPagesSpinner.setValueFactory(factory2);
    }

    protected void validateAndAdd() {
        errorLabel.setText("");

        if (titleField.getText().trim().isEmpty()) {
            errorLabel.setText("Название книги не может быть пустым!");
            return;
        }

        if (authorField.getText().trim().isEmpty()) {
            errorLabel.setText("Имя автора не может быть пустым!");
            return;
        }

        if (pagesReadSpinner.getValue() > totalPagesSpinner.getValue()) {
            errorLabel.setText("Прочитано страниц не может быть больше общего количества!");
            return;
        }

        if(pagesReadSpinner.getValue().equals(totalPagesSpinner.getValue())) {
            searchingBook.setFinishDate(LocalDate.now());
        }

        searchingBook.setAuthorOfBook(authorField.getText());
        searchingBook.setNameOfBook(titleField.getText());
        searchingBook.setCompletePages(pagesReadSpinner.getValue());
        searchingBook.setAllPages(totalPagesSpinner.getValue());
        searchingBook.changeProcent();

        bvm.changeBook(searchingBook);

        stage.close();
    }

    protected void setupUI() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15));

        titleField.setPromptText("Название книги");
        authorField.setPromptText("Автор книги");
        pagesReadSpinner.setEditable(true);
        totalPagesSpinner.setEditable(true);

        grid.add(new Label("Название книги:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Автор книги:"), 0, 1);
        grid.add(authorField, 1, 1);
        grid.add(new Label("Прочитано страниц:"), 0, 2);
        grid.add(pagesReadSpinner, 1, 2);
        grid.add(new Label("Всего страниц:"), 0, 3);
        grid.add(totalPagesSpinner, 1, 3);

        // Кнопка добавления
        addButton = new Button("Добавить");
        addButton.setOnAction(e -> validateAndAdd());
        grid.add(addButton, 0, 4, 2, 1);

        // Метка для ошибок
        errorLabel.setStyle("-fx-text-fill: red;");
        grid.add(errorLabel, 0, 5, 2, 1);

        // Настройка сцены
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.setResizable(false);
    }

    protected void setupValidation() {
        totalPagesSpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (pagesReadSpinner.getValue() > newVal) {
                pagesReadSpinner.getValueFactory().setValue(newVal);
            }
        });
    }

    public Book showAndWait() {
        stage.showAndWait();
        return resultBook;
    }
}