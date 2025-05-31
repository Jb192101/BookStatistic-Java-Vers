package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.view.ChangeWindow;

public class ChangeViewModel extends LocalViewModel implements InteractViewModelInterface {
    MainViewModel mvm;

    public ChangeViewModel(MainViewModel _mvm) {
        this.mvm = _mvm;
        this.window = new ChangeWindow(this);
    }

    @Override
    public Book getBook() {
        return null;
    }

    @Override
    public void setBook(Book _newBook) {
        mvm.changeBook(_newBook);
        closeWindow();
    }
}
