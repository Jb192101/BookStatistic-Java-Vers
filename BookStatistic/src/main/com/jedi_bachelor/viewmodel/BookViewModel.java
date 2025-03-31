package main.com.jedi_bachelor.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.com.jedi_bachelor.model.Book;
import main.com.jedi_bachelor.repository.BookRepository;
import main.com.jedi_bachelor.view.IndexInputWindow;

public class BookViewModel {
    private final BookRepository repository;
    private final ObservableList<Book> books = FXCollections.observableArrayList();

    public BookViewModel(BookRepository repository) {
        this.repository = repository;
        loadBooks();
    }

    private void loadBooks() {
        this.books.setAll(repository.getAllBooks());
    }

    // Тестовое содержание
    public void addBook() {
        IndexInputWindow iiWindow = new IndexInputWindow();
        iiWindow.show();
    }

    public void changeData() {

    }

    public void showStatMonth() {

    }

    public void showStatTemps() {

    }

    public ObservableList<Book> getBooks() { return books; }
}
