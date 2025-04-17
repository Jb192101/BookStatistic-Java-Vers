package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.model.Model;
import org.jedi_bachelor.view.ChangeWindow;
import org.jedi_bachelor.view.InputDataWindow;
import org.jedi_bachelor.view.InputIndexWindow;

import java.util.ArrayList;

public class BookViewModel {

    private Model model;

    public BookViewModel() {
        this.model = new Model();
    }

    public BookViewModel(Model _model) {
        this.model = _model;
    }

    public Model getModel() {
        return model;
    }

    public void openInputDataWindow() {
        InputDataWindow inputWindow = new InputDataWindow();
        Book newBook = inputWindow.showAndWait();
        updateBookModel(newBook);
        System.out.println(newBook);
    }

    public void openChangeWindow(int _index, BookViewModel _bvm) {
        ChangeWindow changeWindow = new ChangeWindow(_index, _bvm);
        changeWindow.showAndWait();
    }

    public void openInputIndexWindow() {
        InputIndexWindow inputIndexWindow = new InputIndexWindow(new BookViewModel(this.model));
        inputIndexWindow.showAndWait();
    }

    // Метод чтения из файла в модель
    public ArrayList<Book> readFromBookModel() {
        return this.model.getBooks();
    }

    private void updateBookModel(Book _newBook) {
        this.model.updateData(_newBook);
    }

}
