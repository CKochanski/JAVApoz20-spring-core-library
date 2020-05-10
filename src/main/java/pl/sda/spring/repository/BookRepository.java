package pl.sda.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.spring.model.Book;

import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {

    Set<Book> findAllByTitle(String title);
}
