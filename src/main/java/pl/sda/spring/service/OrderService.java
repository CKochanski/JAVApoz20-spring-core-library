package pl.sda.spring.service;

import org.springframework.stereotype.Service;
import pl.sda.spring.model.Book;
import pl.sda.spring.repository.BookRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {

    private final BookRepository bookRepository;

    public OrderService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> borrowBook(String title) {
        Optional<Book> notBorrowedBook = bookRepository.findAllByTitle(title).stream()
            .filter(book -> book.getBorrowedTill() == null).findAny();
        if (notBorrowedBook.isPresent()) {
            Book bookToBorrow = notBorrowedBook.get();
            bookToBorrow.setBorrowedTill(LocalDate.now().plusDays(30));
            return Optional.of(bookRepository.save(bookToBorrow));
        }
        return notBorrowedBook;
    }

    public Book add(String author, String title) {
        return bookRepository.save(new Book(null, author, title, null));
    }

    public void remove(Long id) {
        bookRepository.deleteById(id);
    }

    public Book returnBook(Long id) {
        Book bookToReturn = bookRepository.findById(id).get();
        bookToReturn.setBorrowedTill(null);
        return bookRepository.save(bookToReturn);
    }

    public Set<Book> findAll(String title) {
        if (title == null) {
            return new HashSet<>(bookRepository.findAll());
        }
        return bookRepository.findAllByTitle(title);
    }
}
