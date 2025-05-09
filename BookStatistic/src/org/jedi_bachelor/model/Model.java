package org.jedi_bachelor.model;

import org.jedi_bachelor.utils.BinFileReader;
import org.jedi_bachelor.utils.BinFileWriter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Model {
    private final String PATH_TO_FILE_BOOKS = "src/resources/data_files/books.bin";
    //private final String PATH_TO_FILE_MONTH = "src/resources/data_files/monthStatistic.bin";
    //private final String PATH_TO_FILE_TEMPS = "src/resources/data_files/tempsStatistic.bin";

    private BinFileReader<HashMap<Integer, Book>> bfr;
    private BinFileWriter<HashMap<Integer, Book>> bfw;
    private HashMap<Integer, Book> books = new HashMap<>();

    public Model() {
        bfr = new BinFileReader<>(PATH_TO_FILE_BOOKS);
        bfw = new BinFileWriter<>(PATH_TO_FILE_BOOKS, books);

        readFromFile();
    }

    public void addBook(int _id, Book _newBook) {
        books.put(_id, _newBook);
    }
    public Map<Integer, Book> getBooks() {
        return books;
    }
    public void setBooks(HashMap<Integer, Book> _listBooks) {
        books = _listBooks;
    }

    private void readFromFile() {
        bfr.read();
        books = bfr.getObject();
    }

    private void writeToFile(HashMap<Integer, Book> _books) {
        bfw.setObject(_books);
        bfw.write();
    }

    public void updateDataAddBook(Book _newBook) {
        _newBook.setId(this.books.size() + 1);
        this.books.put(this.books.size() + 1, _newBook);

        updateFileBooks();
    }

    public void changeBook(Book _book) {
        int id = 0;
        if(this.books.containsValue(_book))
            for(int idIterator : this.books.keySet())
                if (this.books.get(idIterator).equals(_book)) {
                    id = idIterator;
                    break;
                }

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
