package org.jedi_bachelor.model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Model {
    private final String PATH_TO_FILE_BOOKS = "src/resources/data_files/books.bin";
    private final String PATH_TO_FILE_MONTH = "src/resources/data_files/monthStatistic.bin";
    private final String PATH_TO_FILE_TEMPS = "src/resources/data_files/tempsStatistic.bin";

    private Map<Integer, Book> books = new HashMap<>();

    public Model() {
        readFromFile();
    }

    public void addBook(int _id, Book _newBook) {
        books.put(_id, _newBook);
    }

    public Map<Integer, Book> getBooks() {
        return books;
    }

    public void setBooks(Map<Integer, Book> _listBooks) {
        books = _listBooks;
    }

    private void readFromFile() {
        try {
            FileInputStream fis = new FileInputStream(PATH_TO_FILE_BOOKS);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.books = (HashMap<Integer, Book>) ois.readObject();

            ois.close();
        } catch(IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void updateDataAddBook(Book _newBook) {
        this.books.put(this.books.size() + 1, _newBook);

        updateFileBooks();
    }

    public void changeBook(Book _book) {
        int id;

        Book medBook = searchBook(id);
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
        try {
            FileOutputStream fos = new FileOutputStream(PATH_TO_FILE_BOOKS);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this.books);

            oos.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public Book searchBook(int _id) {
        if(this.books.containsKey(_id))
            return this.books.get(_id);

        return new Book();
    }

}
