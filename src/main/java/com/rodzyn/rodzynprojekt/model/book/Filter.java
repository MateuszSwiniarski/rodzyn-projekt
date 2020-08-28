package com.rodzyn.rodzynprojekt.model.book;

public class Filter {

    private String parametr;
    private String category;

    public Filter(String parametr, String category) {
        this.parametr = parametr;
        this.category = category;
    }

    public Filter() {
    }

    public String getParametr() {
        return parametr;
    }

    public void setParametr(String parametr) {
        this.parametr = parametr;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "parametr='" + parametr + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
