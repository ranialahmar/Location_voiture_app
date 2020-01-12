package com.example.demo.Models;

import org.springframework.data.annotation.Id;

import java.util.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Accident {

   /* @Id
    private  Long Accident_id;*/
    private Date dateAccident;
    private String lieu;
    private String description;

    public Accident(){}
/*
    public Long getId() {
        return Accident_id;
    }

    public void setId(Long id) {
        this.Accident_id = id;
    }*/

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
