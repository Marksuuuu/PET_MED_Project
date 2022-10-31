package com.example.petmed.model;

public class User {
    private String id;
    private String Fullname;
    private String imageUrl;


    public void setFullname(String id,String imageUrl,String Fullname) {
        this.Fullname = Fullname;
        this.id = id;
        this.imageUrl = imageUrl;
    }
    public User(){

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }
}