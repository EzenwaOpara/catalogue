package com.eze.catalogue.service;

import com.eze.catalogue.domain.Book;
import com.eze.catalogue.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    private Book findBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

    private Book findBookByTitle(String title) {
        Optional<Book> optionalBook = bookRepository.findByTitle(title);
        return optionalBook.orElse(null);
    }

    private List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    private Book findBookByIsbn(String isbn) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        return optionalBook.orElse(null);
    }

    private Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    private void removeBook(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        bookOptional.ifPresent(bookRepository::delete);
    }
}
