package com.rodzyn.rodzynprojekt.service;

import com.rodzyn.rodzynprojekt.model.Book;
import com.rodzyn.rodzynprojekt.model.Filter;
import com.rodzyn.rodzynprojekt.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {

    private BookRepository bookRepo;

    @Autowired
    public BookServiceImp(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> getBooks(){
        return bookRepo.findAll();
    }

    @Override
    public Optional<Book> getBook(long id) {
        return bookRepo.findById(id);
    }

    public boolean addBook(Book book){
        bookRepo.save(book);
        return true;
    }

    @Override
    public boolean deleteBookById(long id) {
        bookRepo.deleteById(id);
        return true;
    }

    @Override
    public void updateBook(String title, String author, String binding, int numberPages, String category,
            String ISBN, String url, String description, long id){
        bookRepo.updateBook(title, author, binding, numberPages, category, ISBN, url, description, id);
    }

    @Override
    public List<Book> findAllByCategory(String category) {
        return bookRepo.findAllByCategory(category);
    }

    @Override
    public List<Book> findAllByAuthor(String author) {
        return bookRepo.findAllByAuthor(author);
    }

    @Override
    public List<Book> findAllByBinding(String binding) {
        return bookRepo.findAllByBinding(binding);
    }

    public void addBooks(){
        bookRepo.deleteAll();
        bookRepo.save(new Book("Ekonomia w jednej lekcji", "Henry Hazlitt,",
                "hardcover", "not read yet",
                224, "economy", "978-83-63250-07-2",
                "http://www.mises.sklep.pl/68-large_default/ekonomia-w-jednej-lekcji-oprawa-twarda.jpg"));
        bookRepo.save(new Book("Kot biznesik", "Arkadiusz Błażyca", "hardcover",
                "simple book for children how to think to be richer and what to do to achive this dream",
                84, "economy", "9788364599972", "https://kotbiznesik.pl/wp-content/uploads/2020/07/fizyk-KB.png"));
    }

    private Filter filter = new Filter();

    @Override
    public Filter getFilter() {
        return filter;
    }

    @Override
    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public List<Book> getBooksByFilter(){
        if(getFilter().getCategory()== null){
            System.out.println("null");
            return bookRepo.findAll();
        }else if(getFilter().getCategory().equals("category")){
            System.out.println("drama");
            return bookRepo.findAllByCategory("drama");
        }else if(getFilter().getCategory().equals("author")){
            System.out.println("author");
            return bookRepo.findAllByAuthor(getFilter().toString());
        }else if(getFilter().getCategory().equals("binding")){
            System.out.println("binding");
            return bookRepo.findAllByBinding(getFilter().toString());
        }else {
            System.out.println("all");
            return bookRepo.findAll();
        }
    }

    //    @EventListener(ApplicationReadyEvent.class)
//    public void updateBook(){
//        updateBook("Kot biznesik", "Arkadiusz Błażyca", "hardcover",
//                84,
//                "economy", "9788364599972",
//                "https://kotbiznesik.pl/wp-content/uploads/2020/07/fizyk-KB.png",
//                "simple book for children how to think to be richer and what to do to achive this dream",13);
//    }
}
