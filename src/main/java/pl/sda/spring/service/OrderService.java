package pl.sda.spring.service;

import org.springframework.stereotype.Service;
import pl.sda.spring.model.Book;
import pl.sda.spring.repository.BookRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrderService {

    private final BookRepository bookRepository;

    public OrderService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> borrowBook(String title) {
        return bookRepository.borrowBook(title, LocalDate.now().plusDays(30));
    }

    public Book add(String author, String title) {
        return bookRepository.add(author, title);
    }

    public void remove(Long id) {
        bookRepository.remove(id);
    }

    public Book returnBook(Long id) {
        return bookRepository.returnBook(id);
    }
}
