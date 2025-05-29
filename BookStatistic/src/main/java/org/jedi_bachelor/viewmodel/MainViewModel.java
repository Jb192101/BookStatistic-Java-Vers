package org.jedi_bachelor.viewmodel;

import javafx.collections.ObservableList;
import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.model.Model;
import org.jedi_bachelor.view.MainWindow;

import java.util.Map;

public class MainViewModel extends LocalViewModel {
    private Model model;

    // ViewModel-ы
    AboutViewModel avm;

    public MainViewModel() {
        this.window = new MainWindow(this);
    }

    @Override
    public Book getBook() {
        return null;
    }

    @Override
    public void setBook() {

    }

    public void openAboutWindow() {
        avm.openWindow();
    }

    public void startApplication() {
        SplashViewModel svm = new SplashViewModel(this);
        svm.openWindow();
    }

    /*
    Методы работы в MainWindow
     */

    public void fillingTable(ObservableList<Book> _data) {
        _data.clear();

        try {
            Map<Integer, Book> bookList = model.getBooks();
            System.out.println(bookList);
            if(bookList.isEmpty()) {
                return;
            }

            for (int i : bookList.keySet()) {
                _data.add(bookList.get(i));
            }
        } catch(NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public void openInputDataWindow() {
        /*
        InputDataWindow inputWindow = new InputDataWindow();
        Book newBook = inputWindow.showAndWait();
        updateBookModel(newBook);
        System.out.println(newBook);
         */
    }

    public void openChangeWindow(int _index, BookViewModel _bvm) {
        /*
        if(_bvm.searchBookByID(_index) != null) {
            ChangeWindow changeWindow = new ChangeWindow(_index, _bvm);
            Book newBook = changeWindow.showAndWait();
            updateBookModel(newBook);
            System.out.println(newBook);
        } else {
            System.out.println("Кринж");
        }
         */
    }

    public void openInputIndexWindow() {
        /*
        InputIndexWindow inputIndexWindow = new InputIndexWindow(this);
        inputIndexWindow.showAndWait();
         */
    }

    private void updateBookModel(Book _newBook) {
        this.model.updateDataAddBook(_newBook);
    }
}
