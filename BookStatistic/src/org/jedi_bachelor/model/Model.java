package org.jedi_bachelor.model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Model {
    private final String PATH_TO_FILE_BOOKS = "books.bin";

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

    public void updateData(Book _newBook) {
        this.books.add(_newBook);
        try {
            FileOutputStream fos = new FileOutputStream(PATH_TO_FILE_BOOKS);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this.books);

            oos.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    
}
