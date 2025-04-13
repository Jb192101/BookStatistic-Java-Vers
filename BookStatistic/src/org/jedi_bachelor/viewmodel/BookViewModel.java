package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.view.InputDataWindow;

import java.io.*;
import java.util.ArrayList;

public class BookViewModel {
    private final String PATH_TO_FILE_BOOKS = "books.bin";

    public void openInputDataWindow() {
        InputDataWindow inputWindow = new InputDataWindow();
        Book newBook = inputWindow.showAndWait();
        updateBookModel(newBook);
        System.out.println(newBook);
    }

    public ArrayList<Book> readFromBookModel() {
        try {
            FileInputStream fis = new FileInputStream(PATH_TO_FILE_BOOKS);
            ObjectInputStream ois = new ObjectInputStream(fis);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return new ArrayList<>();
    }

    private void updateBookModel(Book _newBook) {
        try {
            FileOutputStream fos = new FileOutputStream(PATH_TO_FILE_BOOKS);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

}
