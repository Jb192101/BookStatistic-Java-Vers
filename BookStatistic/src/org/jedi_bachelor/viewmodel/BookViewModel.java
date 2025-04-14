package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.model.Model;
import org.jedi_bachelor.view.InputDataWindow;

import java.util.ArrayList;

public class BookViewModel {

    private Model model;

    public BookViewModel() {
        model = new Model();
    }

    public void openInputDataWindow() {
        InputDataWindow inputWindow = new InputDataWindow();
        Book newBook = inputWindow.showAndWait();
        updateBookModel(newBook);
        System.out.println(newBook);
    }

    // Метод чтения из файла в модель
    public ArrayList<Book> readFromBookModel() {
        return this.model.getBooks();
    }

    private void updateBookModel(Book _newBook) {
        this.model.updateData(_newBook);
    }

}
