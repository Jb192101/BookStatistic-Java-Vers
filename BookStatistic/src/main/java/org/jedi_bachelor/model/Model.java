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
    private final BinFileReader<HashMap<Date, Integer>> bfrStatTemps;
    private final BinFileWriter<HashMap<Date, Integer>> bfwStatTemps;

    // Для статистики чтения
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

        bfrStatTemps = new BinFileReader<>(PATH_TO_FILE_TEMPS);
        bfwStatTemps = new BinFileWriter<>(PATH_TO_FILE_TEMPS, monthTemps);

        bfrStat = new BinFileReader<>(PATH_TO_FILE_MONTH);
        bfwStat = new BinFileWriter<>(PATH_TO_FILE_MONTH, monthStat);

        readFromFile();
    }

    public Map<Integer, Book> getBooks() {
        return this.books;
    }

    public Map<Date, Integer> getStat() {
        return this.monthStat;
    }

    public Map<Date, Integer> getTemps() {
        return this.monthTemps;
    }

    private void readFromFile() {
        bfrBooks.read();
        books = bfrBooks.getObject();
        if(books == null)
            books = new HashMap<>();

        bfrStat.read();
        monthStat = bfwStat.getObject();
        if(monthStat == null)
            monthStat = new HashMap<>();

        bfrStatTemps.read();
        monthTemps = bfwStatTemps.getObject();
        if(monthTemps == null)
            monthTemps = new HashMap<>();
    }

    public void updateDataAddBook(Book _newBook) {
        _newBook.setId(books.size() + 1);
        books.put(books.size() + 1, _newBook);

        // Добавление прочитанных страниц в статистику
        addPagesAtMonthSpeed(_newBook.getCompletePages());
        addPagesAtMonthStat(_newBook.getCompletePages());

        updateFileBooks();
    }

    public void changeBook(Book _book) {
        if(_book == null) {
            return;
        }

        int id = 0;
        int changedPages = 0;
        if(this.books.containsValue(_book))
            for(int idIterator : this.books.keySet()) {
                System.out.println(this.books.get(idIterator).equals(_book));
                if (this.books.get(idIterator).equals(_book)) {
                    id = idIterator;
                    break;
                }
            }
        Book medBook = searchBook(id);

        changedPages = _book.getCompletePages() - medBook.getCompletePages();
        if(changedPages < 0)
            changedPages *= -1;

        addPagesAtMonthStat(changedPages);
        addPagesAtMonthSpeed(changedPages);

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

        System.out.println(monthTemps);
        bfwStatTemps.setObject(monthTemps);
        bfwStatTemps.write();

        bfwStat.setObject(monthStat);
        bfwStat.write();
    }

    public Book searchBook(int _id) {
        if(this.books.containsKey(_id))
            return this.books.get(_id);

        // Если книга не найдена, возвращай null
        return null;
    }

    private void addPagesAtMonthStat(int _changed) {
        if(!monthStat.containsKey(Date.now())) {
            monthStat.put(Date.now(), _changed);
        } else {
            monthStat.put(Date.now(), monthStat.get(Date.now()) + _changed);
        }
    }

    private void addPagesAtMonthSpeed(int _changed) {
        if(!monthTemps.containsKey(Date.now())) {
            monthTemps.put(Date.now(), _changed);
        } else {
            monthTemps.put(Date.now(), monthTemps.get(Date.now()) + _changed);
        }
    }
}