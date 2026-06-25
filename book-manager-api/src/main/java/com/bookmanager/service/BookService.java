package com.bookmanager.service;

import com.bookmanager.model.Book;
import com.bookmanager.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public Book update(Long id, Book updated) {
        Book book = findById(id);
        book.setTitle(updated.getTitle());
        book.setAuthor(updated.getAuthor());
        book.setYear(updated.getYear());
        book.setGenre(updated.getGenre());
        book.setRead(updated.isRead());
        return repository.save(book);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public List<Book> findByAuthor(String author) {
        return repository.findByAuthor(author);
    }

    public List<Book> findByGenre(String genre) {
        return repository.findByGenre(genre);
    }

    public List<Book> findByRead(boolean read) {
        return repository.findByRead(read);
    }
}
