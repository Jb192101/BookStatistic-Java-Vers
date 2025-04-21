package org.jedi_bachelor.model;

import java.io.*;
import java.util.ArrayList;

public class Model {
    private final String PATH_TO_FILE_BOOKS = "src/resources/data_files/books.bin";
    private final String PATH_TO_FILE_MONTH = "src/resources/data_files/monthStatistic.bin";
    private final String PATH_TO_FILE_TEMPS = "src/resources/data_files/tempsStatistic.bin";

    private ArrayList<Book> books = new ArrayList<>();

    public Model() {
        readFromFile();
    }

    public void addBook(Book _newBook) {
        books.add(_newBook);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> _listBooks) {
        books = _listBooks;
    }

    private void readFromFile() {
        try {
            FileInputStream fis = new FileInputStream(PATH_TO_FILE_BOOKS);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.books = (ArrayList<Book>) ois.readObject();

            ois.close();
        } catch(IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void updateDataAddBook(Book _newBook) {
        _newBook.setId(this.books.size()+1);
        this.books.addFirst(_newBook);

        updateFileBooks();
    }

    public void changeBook(Book _book) {
        int id = _book.getId();

        Book medBook = searchBook(id);
        books.remove(medBook);

        medBook.setNameOfBook(_book.getNameOfBook());
        medBook.setAuthorOfBook(_book.getAuthorOfBook());
        medBook.setAllPages(_book.getAllPages());
        medBook.setCompletePages(_book.getCompletePages());
        medBook.setFinishDate(_book.getFinishDate());

        books.add(medBook);

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
        for(Book book : this.books) {
            if(book.getId() == _id) {
                return book;
            }
        }

        return new Book();
    }

}
