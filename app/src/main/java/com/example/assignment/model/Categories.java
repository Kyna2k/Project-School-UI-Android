package com.example.assignment.model;

import java.io.Serializable;

public class Categories implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private String images;

    public Categories() {
    }

    public Categories(String name, String description, String images) {
        this.name = name;
        this.description = description;
        this.images = images;
    }

    public Categories(Integer id, String name, String description, String images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.images = images;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
