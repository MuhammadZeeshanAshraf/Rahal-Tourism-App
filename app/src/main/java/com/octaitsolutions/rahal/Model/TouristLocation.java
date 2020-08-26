package com.octaitsolutions.rahal.Model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TouristLocation implements Serializable {

    String id ;
    String country;
    String city;
    String name ;
    String phone;
    String address;
    String timing;
    String link;
    String location;
    String description;
    String uri;
    String rating;

    public TouristLocation() {
    }

    public TouristLocation(String id, String country, String city, String name, String phone, String address, String timing, String link, String location, String description, String uri , String rating) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.timing = timing;
        this.link = link;
        this.location = location;
        this.description = description;
        this.uri = uri;
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
