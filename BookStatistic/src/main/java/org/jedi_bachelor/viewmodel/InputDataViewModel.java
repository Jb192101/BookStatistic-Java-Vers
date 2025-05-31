package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.view.InputDataWindow;

public class InputDataViewModel extends LocalViewModel implements InteractViewModelInterface {
    private Book newBook;
    private MainViewModel mvm;

    public InputDataViewModel(MainViewModel _mvm) {
        this.mvm = _mvm;
        this.window = new InputDataWindow(this);
    }

    @Override
    public Book getBook() {
        return null;
    }

    @Override
    public void setBook(Book _newBook) {
        this.newBook = _newBook;

        mvm.updateBookModel(this.newBook);

        closeWindow();
    }

    @Override
    public void openWindow() {
        this.window.show();
    }

    @Override
    public void closeWindow() {
        this.window.close();
    }
}
