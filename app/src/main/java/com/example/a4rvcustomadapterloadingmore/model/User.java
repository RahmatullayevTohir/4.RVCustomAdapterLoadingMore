package com.example.a4rvcustomadapterloadingmore.model;

public class User {
    private String firstName;
    private String lastName;
    private boolean available;

    public User(String firstName, String lastName, boolean available) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.available = available;
    }


    public boolean isAvailable() {
        return available;
    }


    public User(){}

    public void setAvailable(boolean available) {
        this.available = available;
    }
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
}
