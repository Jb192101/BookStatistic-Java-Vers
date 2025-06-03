package org.jedi_bachelor.viewmodel;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.model.Model;
import org.jedi_bachelor.view.MainWindow;

import java.util.Map;

public class MainViewModel extends LocalViewModel {
    private final Model model;

    // ViewModel-ы
    private AboutViewModel avm;
    private InputDataViewModel idvm;
    private InputIndexViewModel iivm;
    private ChangeViewModel cvm;

    // Доп. окна
    Alert alertWindow;

    public MainViewModel() {
        this.avm = new AboutViewModel();
        this.idvm = new InputDataViewModel(this);
        this.iivm = new InputIndexViewModel(this);
        this.cvm = new ChangeViewModel(this);

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

    public void openChangeWindow(int _index) {
        Book changedBook = searchBookByID(_index);
        System.out.println(changedBook);
        if(changedBook != null) {
            cvm.setBookWithoutClosingWindow(changedBook);
            iivm.closeWindow();
            cvm.openWindow();
        } else {
            // Вывод сообщения об ошибке
            alertWindow  = new Alert(Alert.AlertType.CONFIRMATION, "Неправильный ввод индекса книги!", ButtonType.OK);
            alertWindow.showAndWait();
        }
    }

    public void openInputIndexWindow() {
        iivm.openWindow();
    }

    public void updateBookModel(Book _newBook) {
        this.model.updateDataAddBook(_newBook);
    }

    private Book searchBookByID(int _index) {
        return model.searchBook(_index);
    }

    public void changeBook(Book _book) {
        this.model.changeBook(_book);
    }
}
