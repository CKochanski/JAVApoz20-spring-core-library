package pl.sda.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue(generator = "bookSeq")
    @SequenceGenerator(name = "bookSeq", sequenceName = "book_seq", allocationSize = 1)
    private Long id;
    private String author;
    private String title;
    private LocalDate borrowedTill;
//    private String reader; null when not borrowed

    public Book() {}

    public Book(Long id, String author, String title, LocalDate borrowedTill) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.borrowedTill = borrowedTill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getBorrowedTill() {
        return borrowedTill;
    }

    public void setBorrowedTill(LocalDate borrowedTill) {
        this.borrowedTill = borrowedTill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(author, book.author) && Objects
            .equals(title, book.title) && Objects.equals(borrowedTill, book.borrowedTill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, borrowedTill);
    }
}
