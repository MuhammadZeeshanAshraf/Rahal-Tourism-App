package com.octaitsolutions.rahal.Model;

import java.io.Serializable;

public class CityFavourite implements Serializable {

    String id;
    String country;
    int image;
    int name;

    public CityFavourite(String id, String country, int image, int name) {
        this.id = id;
        this.country = country;
        this.image = image;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
