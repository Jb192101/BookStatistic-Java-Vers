/*
Класс книги Book
 */

package main.com.jedi_bachelor.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 823478973783L;

    private int id;
    private String nameOfBook;
    private String authorOfBook;
    private int completePages;
    private int allPages;
    private LocalDate startDate;
    private LocalDate finishDate;

    public Book(String _nameOfBook, String _authorOfBook, int _completePages, int _allPages, LocalDate _ld) {
        if(_completePages < _allPages && _completePages >= 0 && _allPages > 0) {
            this.nameOfBook = _nameOfBook;
            this.authorOfBook = _authorOfBook;
            this.completePages = _completePages;
            this.allPages = _allPages;
            this.startDate = _ld;
        }
    }

    // Пустой конструктор
    public Book() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && completePages == book.completePages && allPages == book.allPages && Objects.equals(nameOfBook, book.nameOfBook) && Objects.equals(authorOfBook, book.authorOfBook) && Objects.equals(startDate, book.startDate) && Objects.equals(finishDate, book.finishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfBook, authorOfBook, completePages, allPages, startDate, finishDate);
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

    public LocalDate getStartDate() { return startDate; }

    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getFinishDate() { return finishDate; }

    public void setFinishDate(LocalDate finishDate) { this.finishDate = finishDate; }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", nameOfBook='" + nameOfBook + '\'' +
                ", authorOfBook='" + authorOfBook + '\'' +
                ", completePages=" + completePages +
                ", allPages=" + allPages +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
