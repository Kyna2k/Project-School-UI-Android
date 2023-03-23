package com.example.assignment.model;

import java.io.Serializable;

public class Transactions implements Serializable {
    private Integer id;
    private Integer type;
    private Double amount;
    private Integer category_id;
    private Integer wallets_id;
    private String notes;
    private Long date;

    public Transactions() {
    }

    public Transactions(Integer type, Double amount, Integer category_id, Integer wallets_id, String notes, Long date) {
        this.type = type;
        this.amount = amount;
        this.category_id = category_id;
        this.wallets_id = wallets_id;
        this.notes = notes;
        this.date = date;
    }

    public Transactions(Integer id, Integer type, Double amount, Integer category_id, Integer wallets_id, String notes, Long date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.category_id = category_id;
        this.wallets_id = wallets_id;
        this.notes = notes;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getWallets_id() {
        return wallets_id;
    }

    public void setWallets_id(Integer wallets_id) {
        this.wallets_id = wallets_id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
