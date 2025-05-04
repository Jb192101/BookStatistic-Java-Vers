/*
Класс книги Book
 */

package org.jedi_bachelor.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Book implements Serializable, Comparable<Book>{
    @Serial
    private static final long serialVersionUID = 823478973782L;

    //private int id; <- id становится ключом в HashMap
    private String nameOfBook;
    private String authorOfBook;
    private int completePages;
    private int allPages;
    private LocalDate startDate;
    private LocalDate finishDate = null;
    private float procentOfReaded;

    public Book(String _nameOfBook, String _authorOfBook, int _completePages, int _allPages, LocalDate _ld) {
        if(_completePages <= _allPages && _completePages >= 0 && _allPages > 0) {
            this.nameOfBook = _nameOfBook;
            this.authorOfBook = _authorOfBook;
            this.completePages = _completePages;
            this.allPages = _allPages;
            this.startDate = _ld;
            changeProcent();
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
        return completePages == book.completePages && allPages == book.allPages && Objects.equals(nameOfBook, book.nameOfBook) && Objects.equals(authorOfBook, book.authorOfBook) && Objects.equals(startDate, book.startDate) && Objects.equals(finishDate, book.finishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfBook, authorOfBook, completePages, allPages, startDate, finishDate);
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

    public float getProcentOfReaded() {
        return procentOfReaded*100.0f;
    }

    public void setCompletePages(int _completePages) {
        if(_completePages <= this.allPages && _completePages >= 0 && _completePages >= this.completePages)
            this.completePages = _completePages;
    }

    public void changeProcent() { this.procentOfReaded = (float) this.completePages / this.allPages; }

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
                ", nameOfBook='" + nameOfBook + '\'' +
                ", authorOfBook='" + authorOfBook + '\'' +
                ", completePages=" + completePages +
                ", allPages=" + allPages +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }

    @Override
    public int compareTo(Book o) {
        return (int) (this.procentOfReaded - o.getProcentOfReaded());
    }
}
