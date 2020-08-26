package com.octaitsolutions.rahal.Model;

public class Review {
    String id;
    String userId;
    String name;
    String comment;
    String rating;
    String uri;

    public Review(String id, String userId, String name, String comment, String rating, String uri) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.comment = comment;
        this.rating = rating;
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
