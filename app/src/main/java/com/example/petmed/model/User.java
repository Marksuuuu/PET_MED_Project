package com.example.petmed.model;

public class User {
    private String id;
    private String username;
    private String imageUrl;


    public void setUsername(String id,String imageUrl,String username) {
        this.username = username;
        this.id = id;
        this.imageUrl = imageUrl;
    }
    public User(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}