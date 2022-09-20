package com.eze.catalogue.service;

import com.eze.catalogue.domain.Book;
import com.eze.catalogue.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        if(bookList.isEmpty()){
            throw new RuntimeException("No books found..");
        }
        return bookList;
    }

    @Override
    public Book findBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

    @Override
    public Book findBookByTitle(String title) {
        Optional<Book> optionalBook = bookRepository.findByTitle(title);
        return optionalBook.orElse(null);
    }

    @Override
    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        return optionalBook.orElse(null);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void removeBook(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        bookOptional.ifPresent(bookRepository::delete);
    }
}
