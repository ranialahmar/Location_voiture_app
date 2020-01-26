package com.example.demo.Models;


import com.sun.javafx.beans.IDProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


public class Assurance  {


    private String assureur;
    private String typeAssurance;
    private Float cotisation;

    public Assurance(){}


    public String getAssureur() {
        return assureur;
    }

    public void setAssureur(String assureur) {
        this.assureur = assureur;
    }

    public String getTypeAssurance() {
        return typeAssurance;
    }

    public void setTypeAssurance(String typeAssurance) {
        this.typeAssurance = typeAssurance;
    }

    public Float getCotisation() {
        return cotisation;
    }

    public void setCotisation(Float cotisation) {
        this.cotisation = cotisation;
    }
}
