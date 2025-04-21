package org.jedi_bachelor.view;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.viewmodel.BookViewModel;

import java.time.LocalDate;

public class ChangeWindow extends InputDataWindow {
    private BookViewModel bvm;
    private Book searchingBook;

    public ChangeWindow(int _index, BookViewModel _bvm) {
        if(_bvm != null) {
            this.bvm = _bvm;
            searchingBook = bvm.searchBookByID(_index);
            //System.out.println(searchingBook);
            stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Редактировать книгу");

            setupSpinners();
            setupUI();
            setupValidation();

            fillingFields();
        }
    }

    private void fillingFields() {
        this.titleField.setText(searchingBook.getNameOfBook());
        this.authorField.setText(searchingBook.getAuthorOfBook());
    }

    @Override
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

    @Override
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

        bvm.changeBook(searchingBook);

        stage.close();
    }
}
