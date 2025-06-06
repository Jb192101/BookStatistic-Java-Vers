package org.jedi_bachelor.model;

/*
Класс книги Book

Содержит:

Автор: @Jb192101
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
public class Book implements Serializable, Comparable<Book>{
    @Setter
    @Getter
    private int id; //<- id становится ключом в HashMap
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
    private Set<Genre> genres;
    private Rating rating;
    private String description;

    public Book(String _nameOfBook, String _authorOfBook, int _completePages, int _allPages) {
        if(_completePages <= _allPages && _completePages >= 0 && _allPages > 0) {
            this.nameOfBook = _nameOfBook;
            this.authorOfBook = _authorOfBook;
            this.completePages = _completePages;
            this.allPages = _allPages;
            this.startDate = LocalDate.now();
            this.genres = null; // в будущем добавь возможность формировать список жанров
            this.rating = null; // в будущем добавь возможность формировать оценку книги
            this.description = null; // в будущем добавь возможность у пользователя описывать книгу / добавлять к ней комменты
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
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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