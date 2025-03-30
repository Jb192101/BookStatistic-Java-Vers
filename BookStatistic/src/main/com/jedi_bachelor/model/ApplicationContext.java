package main.com.jedi_bachelor.model;

/*
Класс ApplicationContext

Время работы операций:
1) Получение данных - О(1) (ArrayList)
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationContext {
    private List<Book> books;
    private final String pathFile = "C:/Users/gogat/IdeaProjects/BookStatistic/src/com/jedi_bachelor/serializable/books.bin";

    public ApplicationContext() {
        books = new ArrayList<>();
    }

    // Чтение из books в books.bin
    public void writeData() {
        try {
            FileOutputStream fos = new FileOutputStream(pathFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeInt(books.size());

            for(Book b : books) {
                oos.writeObject(b);
            }

            oos.close();
            fos.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    // Запись в books из books.bin
    public void getData() {
        try {
            FileInputStream fis = new FileInputStream(pathFile);
            ObjectInputStream ois = new ObjectInputStream(fis);

            int bookCount = ois.readInt();
            for(int i = 0; i < bookCount; i++) {
                books.add((Book) ois.readObject());
            }

            ois.close();
            fis.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> _books) {
        this.books = _books;
    }
}
