package com.example.demo.Models;


import java.util.Date;
import org.springframework.data.mongodb.core.mapping.Document;


public class Accident {


    private Date dateAccident;
    private String lieu;
    private String description;

    public Accident(){}


    public Date getDateAccident() {
        return dateAccident;
    }

    public void setDateAccident(Date dateAccident) {
        this.dateAccident = dateAccident;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
