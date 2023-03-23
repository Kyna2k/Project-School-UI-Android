package com.example.assignment.model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Wallets implements Serializable {
    private Integer id;
    private String type;
    private String image;
    private Double money;
    private String description;
    private Integer id_user;

    public Wallets() {
    }
    public Wallets(Integer id, String type, String image, Double money, String description, Integer id_user) {
        this.id = id;
        this.type = type;
        this.image = image;
        this.money = money;
        this.description = description;
        this.id_user = id_user;
    }
    public Wallets(String type, String image, Double money, String description, Integer id_user) {
        this.type = type;
        this.image = image;
        this.money = money;
        this.description = description;
        this.id_user = id_user;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String text) {
        this.type = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }
}
