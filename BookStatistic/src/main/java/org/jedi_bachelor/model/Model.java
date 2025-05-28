package org.jedi_bachelor.model;

/*
В данном классе Date - это собственный класс
 */

import lombok.Setter;
import org.jedi_bachelor.utils.BinFileReader;
import org.jedi_bachelor.utils.BinFileWriter;

import java.util.HashMap;
import java.util.Map;

public class Model {
    // Потом замени подгрузкой новых файлов
    private final String PATH_TO_FILE_BOOKS = "user-data/books.bs";
    private final String PATH_TO_FILE_MONTH = "user-data/monthStatistic.bs";
    private final String PATH_TO_FILE_TEMPS = "user-data/tempsStatistic.bs";

    // Утилиты для записи
    // Для добавления/изменения книг
    private final BinFileReader<HashMap<Integer, Book>> bfrBooks;
    private final BinFileWriter<HashMap<Integer, Book>> bfwBooks;

    // Для темпов чтения
    private final BinFileReader<HashMap<Date, Integer>> bfrStat;
    private final BinFileWriter<HashMap<Date, Integer>> bfwStat;

    @Setter
    private HashMap<Integer, Book> books;
    private HashMap<Date, Integer> monthTemps;
    private HashMap<Date, Integer> monthStat;

    public Model() {
        monthTemps = new HashMap<>();
        monthStat = new HashMap<>();

        bfrBooks = new BinFileReader<>(PATH_TO_FILE_BOOKS);
        bfwBooks = new BinFileWriter<>(PATH_TO_FILE_BOOKS, books);

        bfrStat = new BinFileReader<>(PATH_TO_FILE_TEMPS);
        bfwStat = new BinFileWriter<>(PATH_TO_FILE_TEMPS, monthTemps);

        readFromFile();
    }

    public void addBook(int _id, Book _newBook) {
        books.put(_id, _newBook);
    }

    public Map<Integer, Book> getBooks() {
        return this.books;
    }

    private void readFromFile() {
        bfrBooks.read();
        books = bfrBooks.getObject();
        if(books == null)
            books = new HashMap<>();
    }

    public void updateDataAddBook(Book _newBook) {
        _newBook.setId(books.size() + 1);
        books.put(books.size() + 1, _newBook);

        updateFileBooks();
    }

    public void changeBook(Book _book) {
        int id = 0;
        int changedPages = 0;
        if(this.books.containsValue(_book))
            for(int idIterator : this.books.keySet())
                if (this.books.get(idIterator).equals(_book)) {
                    id = idIterator;
                    break;
                }
        Book medBook = searchBook(id);

        changedPages = _book.getCompletePages() - medBook.getCompletePages();
        if(changedPages < 0)
            changedPages *= -1;

        if(!monthTemps.containsKey(Date.now())) {
            monthTemps.put(Date.now(), changedPages);
        } else {
            monthTemps.put(Date.now(), monthTemps.get(Date.now()) + changedPages);
        }

        System.out.println(monthTemps);

        books.remove(id, medBook);

        medBook.setNameOfBook(_book.getNameOfBook());
        medBook.setAuthorOfBook(_book.getAuthorOfBook());
        medBook.setAllPages(_book.getAllPages());
        medBook.setCompletePages(_book.getCompletePages());
        medBook.setFinishDate(_book.getFinishDate());

        books.put(id, medBook);

        updateFileBooks();
    }

    private void updateFileBooks() {
        bfwBooks.setObject(books);
        bfwBooks.write();
    }

    public Book searchBook(int _id) {
        if(this.books.containsKey(_id))
            return this.books.get(_id);

        return new Book();
    }

}