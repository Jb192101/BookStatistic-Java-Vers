package main.com.jedi_bachelor.repository;

import javafx.collections.ObservableList;

import main.com.jedi_bachelor.model.Book;

public interface BookRepository {
    void addBook(Book book);
    ObservableList<Book> getAllBooks();
    void updateReadPages(Book book, int pages);
}
