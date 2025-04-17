package org.jedi_bachelor.view;

import javafx.scene.control.Spinner;
import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.viewmodel.BookViewModel;

import java.time.LocalDate;

public class ChangeWindow extends InputDataWindow {
    private BookViewModel bvm;
    Book searchingBook;

    public ChangeWindow(int _index, BookViewModel _bvm) {
        super();
        this.bvm = _bvm;

        searchingBook = bvm.getModel().getBooks().get(_index-1);

        fillingFields();
    }

    private void fillingFields() {
        this.titleField.setText(searchingBook.getNameOfBook());
        this.authorField.setText(searchingBook.getAuthorOfBook());
        this.pagesReadSpinner = new Spinner<>(0, Integer.MAX_VALUE, searchingBook.getCompletePages());
        this.totalPagesSpinner = new Spinner<>(0, Integer.MAX_VALUE, searchingBook.getAllPages());
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
