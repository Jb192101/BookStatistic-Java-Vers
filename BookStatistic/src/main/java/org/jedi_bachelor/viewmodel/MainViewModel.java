package org.jedi_bachelor.viewmodel;

import javafx.collections.ObservableList;
import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.model.Model;
import org.jedi_bachelor.view.MainWindow;
import org.jedi_bachelor.view.View;

import java.util.Map;

public class MainViewModel extends LocalViewModel {
    private final Model model;

    // ViewModel-ы
    private AboutViewModel avm;
    private InputDataViewModel idvm;

    public MainViewModel() {
        this.avm = new AboutViewModel();
        this.idvm = new InputDataViewModel(this);

        model = new Model();
        this.window = new MainWindow(this);
    }

    // Метод открытия окна "О проекте"
    public void openAboutWindow() {
        avm.openWindow();
    }

    // Метод запуска приложения
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

    // Метод, возвращающий кол-во прочитанных книг
    public int getCountCompleteBooks() {
        int res = 0;
        for(int key : model.getBooks().keySet()) {
            if(model.getBooks().get(key).getFinishDate() != null) {
                res++;
            }
        }

        return res;
    }

    // Открытие окна для добавления новой книги
    public void openInputDataWindow() {
        idvm.openWindow();
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

    public void updateBookModel(Book _newBook) {
        this.model.updateDataAddBook(_newBook);
    }
}
