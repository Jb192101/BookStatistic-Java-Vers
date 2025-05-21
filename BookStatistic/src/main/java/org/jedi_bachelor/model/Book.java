package org.jedi_bachelor.model;

/*
Класс книги Book
 */

//import java.io.Serial;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
public class Book implements Serializable, Comparable<Book>{
    //@Serial
    //private static final long serialVersionUID = 823478973782L;

    @Setter
    @Getter
    private int id = -1; //<- id становится ключом в HashMap
    @Setter
    @Getter
    private String nameOfBook;
    @Setter
    @Getter
    private String authorOfBook;
    @Getter
    private int completePages;
    @Setter
    @Getter
    private int allPages;
    @Getter
    private LocalDate startDate;
    @Getter
    @Setter
    private LocalDate finishDate = null;
    @Getter
    private float procentOfReaded;

    public Book(String _nameOfBook, String _authorOfBook, int _completePages, int _allPages) {
        if(_completePages <= _allPages && _completePages >= 0 && _allPages > 0) {
            this.nameOfBook = _nameOfBook;
            this.authorOfBook = _authorOfBook;
            this.completePages = _completePages;
            this.allPages = _allPages;
            this.startDate = LocalDate.now();
            changeProcent();

            if(_completePages == _allPages)
                this.finishDate = this.startDate;
        }
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

    public float getProcentOfReaded() {
        return procentOfReaded*100.0f;
    }

    public void setCompletePages(int _completePages) {
        if(_completePages <= this.allPages && _completePages >= 0 && _completePages >= this.completePages)
            this.completePages = _completePages;
    }

    public void changeProcent() { this.procentOfReaded = (float) this.completePages / this.allPages; }

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

    @Override
    public int compareTo(Book o) {
        return (int) (this.procentOfReaded - o.getProcentOfReaded());
    }
}