package com.rodzyn.rodzynprojekt.service;

import com.rodzyn.rodzynprojekt.model.Book;
import com.rodzyn.rodzynprojekt.model.Filter;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getBooks();

    Optional<Book> getBook(long id);

    boolean addBook(Book book);

    boolean deleteBookById(long id);

    void updateBook(String title, String author, String binding,int numberPages, String category,
                    String ISBN, String url, String description, long id);

    List<Book> findAllByCategory(String category);

    List<Book> findAllByAuthor(String author);

    List<Book> findAllByBinding(String binding);

    Filter getFilter();

    void setFilter(Filter filter);

    List<Book> getBooksByFilter();
}
