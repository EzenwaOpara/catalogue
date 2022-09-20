package com.eze.catalogue.service;

import com.eze.catalogue.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks();

    Book findBookById(Long id);

    Book findBookByTitle(String title);

    List<Book> findBookByAuthor(String author);

    Book findBookByIsbn(String isbn);

    Book saveBook(Book book);

    void removeBook(Long id);
}
