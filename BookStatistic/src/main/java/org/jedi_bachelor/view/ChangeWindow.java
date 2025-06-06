package org.jedi_bachelor.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;

import javafx.stage.StageStyle;
import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.utils.LocaleManager;
import org.jedi_bachelor.viewmodel.ChangeViewModel;

import java.time.LocalDate;

public class ChangeWindow extends View {
    private final ChangeViewModel cvm;

    protected final TextField titleField = new TextField();
    protected final TextField authorField = new TextField();
    protected Spinner<Integer> pagesReadSpinner;
    protected Spinner<Integer> totalPagesSpinner;
    protected final Label errorLabel = new Label();
    protected Button addButton;

    public ChangeWindow(ChangeViewModel _cvm) {
        this.cvm = _cvm;
        initModality(Modality.WINDOW_MODAL);
        setTitle(LocaleManager.getString("CHANGE_BOOK_TITLE"));
        initStyle(StageStyle.UTILITY);

        setupSpinners();
        setupUI();
        fillingFields();
        setupValidation();
    }

    private void fillingFields() {
        this.titleField.setText(cvm.getBook().getNameOfBook());
        this.authorField.setText(cvm.getBook().getAuthorOfBook());
    }

    protected void setupSpinners() {
        this.pagesReadSpinner = new Spinner<>();
        this.totalPagesSpinner = new Spinner<>();

        SpinnerValueFactory.IntegerSpinnerValueFactory factory1 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, cvm.getBook().getCompletePages());

        pagesReadSpinner.setValueFactory(factory1);

        SpinnerValueFactory.IntegerSpinnerValueFactory factory2 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, cvm.getBook().getAllPages());

        totalPagesSpinner.setValueFactory(factory2);
    }

    protected void validateAndAdd() {
        Book searchingBook = new Book();

        errorLabel.setText("");

        if (titleField.getText().trim().isEmpty()) {
            errorLabel.setText(LocaleManager.getString("ERROR_TITLE_BOOK"));
            return;
        }

        if (authorField.getText().trim().isEmpty()) {
            errorLabel.setText(LocaleManager.getString("ERROR_NAME_AUTHOR_BOOK"));
            return;
        }

        if (pagesReadSpinner.getValue() > totalPagesSpinner.getValue()) {
            errorLabel.setText(LocaleManager.getString("ERROR_PAGES_BOOK"));
            return;
        }

        if(pagesReadSpinner.getValue().equals(totalPagesSpinner.getValue())) {
            searchingBook.setFinishDate(LocalDate.now());
        }

        /*
        searchingBook.setAuthorOfBook(authorField.getText());
        searchingBook.setNameOfBook(titleField.getText());
        searchingBook.setCompletePages(pagesReadSpinner.getValue());
        searchingBook.setAllPages(totalPagesSpinner.getValue());
         */
        searchingBook = new Book(authorField.getText(), titleField.getText(), pagesReadSpinner.getValue(), totalPagesSpinner.getValue());
        searchingBook.changeProcent();

        cvm.setBook(searchingBook);

        close();
    }

    protected void setupUI() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15));

        titleField.setPromptText(LocaleManager.getString("TITLE_BOOK"));
        authorField.setPromptText(LocaleManager.getString("AUTHOR_OF_BOOK"));
        pagesReadSpinner.setEditable(true);
        totalPagesSpinner.setEditable(true);

        grid.add(new Label(LocaleManager.getString("TITLE_BOOK")), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label(LocaleManager.getString("AUTHOR_OF_BOOK")), 0, 1);
        grid.add(authorField, 1, 1);
        grid.add(new Label(LocaleManager.getString("HAD_READED_PAGES_COLON")), 0, 2);
        grid.add(pagesReadSpinner, 1, 2);
        grid.add(new Label(LocaleManager.getString("ALL_PAGES_COLON")), 0, 3);
        grid.add(totalPagesSpinner, 1, 3);

        // Кнопка добавления
        addButton = new Button(LocaleManager.getString("ADD_BOOK_BUTTON"));
        addButton.setOnAction(
                e -> validateAndAdd()
        );
        grid.add(addButton, 0, 4, 2, 1);

        // Метка для ошибок
        errorLabel.setStyle("-fx-text-fill: red;");
        grid.add(errorLabel, 0, 5, 2, 1);

        // Настройка сцены
        Scene scene = new Scene(grid);
        setScene(scene);
        setResizable(false);
    }

    protected void setupValidation() {
        totalPagesSpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (pagesReadSpinner.getValue() > newVal) {
                pagesReadSpinner.getValueFactory().setValue(newVal);
            }
        });
    }
}