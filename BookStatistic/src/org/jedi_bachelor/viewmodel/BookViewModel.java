package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.model.Model;
import org.jedi_bachelor.view.ChangeWindow;
import org.jedi_bachelor.view.InputDataWindow;
import org.jedi_bachelor.view.InputIndexWindow;

import java.util.Map;

public class BookViewModel {

    private Model model;

    public BookViewModel() {
        this.model = new Model();
    }

    public BookViewModel(Model _model) {
        this.model = _model;
    }

    //public Model getModel() {
    //    return model;
    //}

    public void openInputDataWindow() {
        InputDataWindow inputWindow = new InputDataWindow();
        Book newBook = inputWindow.showAndWait();
        updateBookModel(newBook);
        System.out.println(newBook);
    }

    public void openChangeWindow(int _index, BookViewModel _bvm) {
        if(_bvm.searchBookByID(_index) != null) {
            ChangeWindow changeWindow = new ChangeWindow(_index, _bvm);
            Book newBook = changeWindow.showAndWait();
            updateBookModel(newBook);
            System.out.println(newBook);
        } else {
            System.out.println("Кринж");
        }
    }

    public void openInputIndexWindow() {
        InputIndexWindow inputIndexWindow = new InputIndexWindow(new BookViewModel(this.model));
        inputIndexWindow.showAndWait();
    }

    // Метод чтения из файла в модель
    public Map<Integer, Book> readFromBookModel() {
        return this.model.getBooks();
    }

    private void updateBookModel(Book _newBook) {
        this.model.updateDataAddBook(_newBook);
    }

    // Поиск за O(n). Потом придумай, как сделать за O(1)
    public void changeBook(Book _book) { this.model.changeBook(_book); }

    public Book searchBookByID(int _index) {
        return model.searchBook(_index);
    }

}
