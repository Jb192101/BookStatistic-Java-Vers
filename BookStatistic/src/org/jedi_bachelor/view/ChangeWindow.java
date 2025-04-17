package org.jedi_bachelor.view;

import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.viewmodel.BookViewModel;

public class ChangeWindow extends InputDataWindow {
    private BookViewModel bvm;

    public ChangeWindow(int _index, BookViewModel _bvm) {
        super();
        this.bvm = _bvm;
        fillingFields(_index);
    }

    private void fillingFields(int _index) {
        Book searchingBook = bvm.getModel().getBooks().get(_index-1);

        this.titleField.setText(searchingBook.getNameOfBook());
        this.authorField.setText(searchingBook.getAuthorOfBook());
        //this.pagesReadSpinner.setValueFactory(searchingBook.getCompletePages());
        //this.totalPagesSpinner.se
    }
}
