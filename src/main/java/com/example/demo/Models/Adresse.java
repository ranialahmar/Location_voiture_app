package com.example.demo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Adresse {



    private String rue;

    private String CodePostal;

    private String ville;

    public Adresse(){}


   /* public Long getId() {
        return Adresse_id;
    }

    public void setId(Long id) {
        this.Adresse_id = id;
    }*/

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(String codePostal) {
        CodePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
