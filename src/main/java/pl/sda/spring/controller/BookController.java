package pl.sda.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.spring.model.Book;
import pl.sda.spring.service.OrderService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@RestController
public class BookController {

    private final OrderService orderService;

    public BookController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/books", produces = "application/json")
    public Set<Book> findAllBooks(@RequestParam(required = false) String title) {
        return orderService.findAll(title);
    }

    @GetMapping(value = "/book/order/{title}", produces = "application/json")
    public ResponseEntity<Book> borrowBook(@PathVariable String title) {
        Optional<Book> book = orderService.borrowBook(title);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/book/add", consumes = "application/json")
    public ResponseEntity<Long> addBook(@RequestBody Book book) {
        Book addedBook = orderService.add(book.getAuthor(), book.getTitle());
        return new ResponseEntity<>(addedBook.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/book/remove/{id}")
    public ResponseEntity<Void> removeBook(@PathVariable Long id) {
        orderService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/book/return/{id}")
    public Book returnBook(@PathVariable Long id) {
        return orderService.returnBook(id);
    }

    //catch every RuntimeException or throw always NoSuchElementException from repo
    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<String> handleException(NoSuchElementException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
