package com.rodzyn.rodzynprojekt.service;

import com.rodzyn.rodzynprojekt.model.Book;
import com.rodzyn.rodzynprojekt.model.Filter;
import com.rodzyn.rodzynprojekt.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if(getFilter().getCategory() == null){
            return bookRepo.findAll();
        }else if(getFilter().getCategory().equals("all")){
            return bookRepo.findAll();
        } else{
            return bookRepo.findAllByCategory(getFilter().getCategory());
        }
    }

    public List<String> getAllCategories(){
        List<String> categoryList = new ArrayList<>();
        categoryList.add("all");
        List<Book> all = bookRepo.findAll();
        for (int i = 0; i < all.size() ; i++) {
            if(!categoryList.contains(all.get(i).getCategory())){
                categoryList.add(all.get(i).getCategory());
            }
        }
        categoryList.forEach(System.out::println);
        return categoryList;
    }
}
