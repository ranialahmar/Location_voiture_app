package com.example.demo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


public class voitureModele {


    private Voiture voiture;
    private Integer count;


    public void setVoiture(Voiture voiture){
        this.voiture=voiture;}


    public Voiture getVoiture(){
        return voiture;
    }

    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
}
