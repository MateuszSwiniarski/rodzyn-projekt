package com.rodzyn.rodzynprojekt.model.nameday;

public class Country {

    public String country;

    public Country(String country) {
        this.country = country;
    }

    public Country() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Country{" +
                "country='" + country + '\'' +
                '}';
    }
}
