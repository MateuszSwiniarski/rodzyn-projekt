package com.rodzyn.rodzynprojekt.controller;

import com.rodzyn.rodzynprojekt.model.book.Book;
import com.rodzyn.rodzynprojekt.model.book.Filter;
import com.rodzyn.rodzynprojekt.model.nameday.Country;
import com.rodzyn.rodzynprojekt.service.BookService;
import com.rodzyn.rodzynprojekt.service.namedayService.NameDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private NameDayService nameDayService;

    @Autowired
    public BookController(BookService bookService, NameDayService nameDayService) {
        this.bookService = bookService;
        this.nameDayService = nameDayService;
    }

    @GetMapping
    public String getBooks(Model model){
        model.addAttribute("books", bookService.getBooksByFilter());
        model.addAttribute("filter", new Filter());
        model.addAttribute("categories", bookService.getAllCategories());
        model.addAttribute("countries", nameDayService.nameDayList().keySet());
        model.addAttribute("country", new Country());
        model.addAttribute("namesDay", nameDayService.getNameDays());
        model.addAttribute("day", nameDayService.getData());
        return "books";
    }

    @PostMapping
    public String getFilter(@ModelAttribute Filter filter, Country country){
        bookService.getFilter().setCategory(filter.getCategory());
        nameDayService.getCountry().setCountry(country.getCountry());
        System.out.println("country z controllera: " + country.getCountry());
//        if(filter==null){
//            bookService.getFilter().setCategory("all");
//        }else{
//            bookService.getFilter().setCategory(filter.getCategory());
//        }
//
//        System.out.println("country z controllera: " + country.getCountry());
//        if(country == null){
//            nameDayService.getCountry().setCountry("pl");
//        }else{
//            System.out.println(country.getCountry());
//            nameDayService.getCountry().setCountry(country.getCountry());
//        }
        return "redirect:/books";
    }

    @GetMapping("/book/{id}")
    public String getBook(Model model, @PathVariable("id") long id){
        model.addAttribute("book", bookService.getBook(id).get());
        return "oneBook";
    }

    @GetMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") long id, Model model){
        model.addAttribute("book", bookService.getBook(id));
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book){
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("update/{id}")
    public String updateBook(Model model, @PathVariable("id") long id){
        Optional<Book> book = bookService.getBook(id);
        if(book.isPresent()){
            model.addAttribute("book", book.get());
            return "updateBook";
        }
        return "redirect:/books";
    }

    @PostMapping("update")
    public String updateBook(@ModelAttribute Book book){
        bookService.updateBook(book.getTitle(), book.getAuthor(), book.getBinding(),
                book.getNumberPages(), book.getCategory(), book.getISBN(),
                book.getUrl(), book.getDescription(), book.getId());
        return "redirect:/books";
    }
}
