package com.rodzyn.rodzynprojekt.model.book;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String author;
    private String binding;
    private String description;
    private int numberPages;
    private String category;
    private String ISBN;
    private String url;

    public Book() {
    }

    public Book(String title, String author, String binding, String description, int numberPages, String category, String ISBN, String url) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.binding = binding;
        this.description = description;
        this.numberPages = numberPages;
        this.category = category;
        this.ISBN = ISBN;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", binding='" + binding + '\'' +
                ", description='" + description + '\'' +
                ", numberPages=" + numberPages +
                ", category='" + category + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
