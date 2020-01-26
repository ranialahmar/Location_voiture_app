package com.example.demo.Models;

import java.util.List;

public class VoitureAccident {

    private Voiture voiture;
    private List<Accident> accident;
    private Integer countAcc;


    public void setVoiture(Voiture voiture){
        this.voiture=voiture;
    }
    public Voiture getVoiture(){
        return this.voiture;
    }
    public void setAccident(List<Accident> accident){
        this.accident=accident;}


    public List<Accident> getAccident(){
        return accident;
    }

    public Integer getCountAcc() {
        return countAcc;
    }
    public void setCountAcc(Integer count) {
        this.countAcc = count;
    }
}
