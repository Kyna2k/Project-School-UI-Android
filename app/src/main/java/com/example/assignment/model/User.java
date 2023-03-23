package com.example.assignment.model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class User implements Serializable {
    private Integer id;
    private String username;
    private String passwork;
    private String name,address,email;
    private String avatar;

    public User() {
    }
    public User(Integer id, String username, String passwork, String name, String address, String email, String avatar) {
        this.id = id;
        this.username = username;
        this.passwork = passwork;
        this.name = name;
        this.address = address;
        this.email = email;
        this.avatar = avatar;
    }

    public User(String username, String passwork, String name, String address, String email, String avatar) {
        this.username = username;
        this.passwork = passwork;
        this.name = name;
        this.address = address;
        this.email = email;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswork() {
        return passwork;
    }

    public void setPasswork(String passwork) {
        this.passwork = passwork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
