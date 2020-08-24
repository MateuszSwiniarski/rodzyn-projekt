package com.rodzyn.rodzynprojekt.service;

import com.rodzyn.rodzynprojekt.model.Book;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class BookServiceImpTest {

    @MockBean
    BookService bookService;

    public List<Book> prepareMockData(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Ekonomia w jednej lekcji", "Henry Hazlitt", "handcover",
                "not read yet", 224, "economy", "978-83-63250-07-2", "http://www.mises.sklep.pl/68-large_default/ekonomia-w-jednej-lekcji-oprawa-twarda.jpg"));
       bookList.add(new Book("Kot biznesik", "Arkadiusz Błażyca", "handcover", "simple book for children how to think to be richer and what to do to achive this dream",
               84, "economy", "9788364599972", "https://kotbiznesik.pl/wp-content/uploads/2020/07/fizyk-KB.png"));
       bookList.add(new Book("Głupcy umierają", "Mario Puzo", "softcover", "Jeśli choć raz miałeś styczność z hazardem, to książką pozwoli przenieść się do świata pełmego pieniedzy i rozrywek",
               554, "crime", "978-83-7985-545-2", "https://www.wydawnictwoalbatros.com/wp-content/uploads/2018/12/978-83-7985-545-2-310x490.jpg"));
        return bookList;
    }

    @Test
    void should_get_all_books() {
        //given
        List<Book> bookList = prepareMockData();
        given(bookService.getBooks()).willReturn(bookList);
        //when
        List<Book> books = bookService.getBooks();
        //then
        assertThat(books, Matchers.hasSize(3));
    }

    @Test
    void should_get_one_book_by_id() {
        //given
        Book book = new Book("Głupcy umierają", "Mario Puzo", "softcover", "Jeśli choć raz miałeś styczność z hazardem, to książką pozwoli przenieść się do świata pełmego pieniedzy i rozrywek",
                554, "crime", "978-83-7985-545-2", "https://www.wydawnictwoalbatros.com/wp-content/uploads/2018/12/978-83-7985-545-2-310x490.jpg");
        given(bookService.getBook(1)).willReturn(java.util.Optional.of(book));
        //when
        String title = bookService.getBook(1).get().getTitle();
        //then
        assertEquals("Głupcy umierają", title);
        assertNotEquals("Kot biznesik", title);
    }

    @Test
    void should_add_book() {
        //given
        Book book = new Book("Głupcy umierają", "Mario Puzo", "softcover", "Jeśli choć raz miałeś styczność z hazardem, to książką pozwoli przenieść się do świata pełmego pieniedzy i rozrywek",
                554, "crime", "978-83-7985-545-2", "https://www.wydawnictwoalbatros.com/wp-content/uploads/2018/12/978-83-7985-545-2-310x490.jpg");
        given(bookService.addBook(any())).willReturn(equals(book));
        //when
        Book bookWhen = new Book("Głupcy umierają", "Mario Puzo", "softcover", "Jeśli choć raz miałeś styczność z hazardem, to książką pozwoli przenieść się do świata pełmego pieniedzy i rozrywek",
                554, "crime", "978-83-7985-545-2", "https://www.wydawnictwoalbatros.com/wp-content/uploads/2018/12/978-83-7985-545-2-310x490.jpg");
        //then
        assertEquals(bookWhen.getTitle(), "Głupcy umierają");
        assertEquals(bookWhen.getAuthor(), "Mario Puzo");

    }

    @Test
    void should_delete_book_by_id() {
        //given
        given(bookService.deleteBookById(anyInt())).willReturn(true);
        //when
        bookService.deleteBookById(1);
        //then
        verify(bookService, times(1)).deleteBookById(1l);
    }

    @Test
    void updateBook() {
    }

    @Test
    void should_find_all_by_category() {
        //given
        List<Book> bookListByCategory = prepareMockData().stream().filter(book -> book.getCategory().equals("economy")).collect(Collectors.toList());
        given(bookService.findAllByCategory(anyString())).willReturn(bookListByCategory);
        //when
        List<Book> booksByCategory = bookService.findAllByCategory("economy");
        //then
        assertThat(booksByCategory, Matchers.hasSize(2));
    }

    @Test
    void should_get_books_by_filter() {
        //given
        List<Book> bookListByCategory = prepareMockData().stream().filter(book -> book.getCategory().equals("crime")).collect(Collectors.toList());

        given(bookService.getBooksByFilter()).willReturn(bookListByCategory);
        //when
        int categoryCount = bookService.getBooksByFilter().size();
        //then
        assertEquals(categoryCount, 1);
    }

    @Test
    void should_get_all_categories() {
        //given
        List<String> categoriesList = new ArrayList<>();
        categoriesList.add("all");

        for (int i = 0; i < prepareMockData().size() ; i++) {
            if(!categoriesList.contains(prepareMockData().get(i).getCategory())){
                categoriesList.add(prepareMockData().get(i).getCategory());
            }
        }
        given(bookService.getAllCategories()).willReturn(categoriesList);
        //when
        int countCategories = bookService.getAllCategories().size();
        //then
        assertEquals(countCategories, 3);

    }
}