package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.view.ChangeWindow;

public class ChangeViewModel extends LocalViewModel implements InteractViewModelInterface {
    private final MainViewModel mvm;
    private Book changedBook = null;

    public ChangeViewModel(MainViewModel _mvm) {
        this.mvm = _mvm;
    }

    @Override
    public void openWindow() {
        this.window = new ChangeWindow(this);
        this.window.show();
    }

    @Override
    public void closeWindow() {
        this.window.close();
        this.window = null;
    }

    @Override
    public Book getBook() {
        return this.changedBook;
    }

    @Override
    public void setBook(Book _newBook) {
        mvm.changeBook(_newBook);
        closeWindow();
        changedBook = null;
    }

    public void setBookWithoutClosingWindow(Book _newBook) {
        this.changedBook = _newBook;
    }
}
