/*
Класс книги Book
 */

package main.com.jedi_bachelor.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 823478973783L;

    private int id;
    private String nameOfBook;
    private String authorOfBook;
    private int completePages;
    private int allPages;

    public Book(String _nameOfBook, String _authorOfBook, int _completePages, int _allPages) {
        if(_completePages < _allPages && _completePages >= 0 && _allPages > 0) {
            this.nameOfBook = _nameOfBook;
            this.authorOfBook = _authorOfBook;
            this.completePages = _completePages;
            this.allPages = _allPages;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && completePages == book.completePages && allPages == book.allPages && Objects.equals(nameOfBook, book.nameOfBook) && Objects.equals(authorOfBook, book.authorOfBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialVersionUID, id, nameOfBook, authorOfBook, completePages, allPages);
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public String getNameOfBook() {
        return nameOfBook;
    }

    public void setNameOfBook(String _nameOfBook) {
        this.nameOfBook = _nameOfBook;
    }

    public String getAuthorOfBook() {
        return authorOfBook;
    }

    public void setAuthorOfBook(String _authorOfBook) {
        this.authorOfBook = _authorOfBook;
    }

    public int getCompletePages() {
        return completePages;
    }

    public void setCompletePages(int _completePages) {
        if(_completePages < this.allPages && _completePages >= 0 && _completePages >= this.completePages)
            this.completePages = _completePages;
    }

    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int _allPages) {
        this.allPages = _allPages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "nameOfBook='" + nameOfBook + '\'' +
                ", authorOfBook='" + authorOfBook + '\'' +
                ", completePages=" + completePages +
                ", allPages=" + allPages +
                '}';
    }
}
