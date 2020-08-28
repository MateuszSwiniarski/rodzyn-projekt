package com.rodzyn.rodzynprojekt.repository;

import com.rodzyn.rodzynprojekt.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE books SET " +
            "books.title = :title, books.author = :author, books.binding = :binding , books.number_pages = :numberPages, " +
            "books.category = :category, books.ISBN = :ISBN, books.url = :url, books.description = :description "+
            "WHERE books.id = :id", nativeQuery = true)
    void updateBook(@Param("title") String title, @Param("author") String author, @Param("binding") String binding,
                    @Param("numberPages") int numberPages, @Param("category") String category,
                    @Param("ISBN") String ISBN, @Param("url") String url, @Param("description") String description, @Param("id") long id);

    List<Book> findAllByCategory(String category);
}
