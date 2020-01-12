package com.example.demo.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;


@Document(collection="Voiture")
public class Voiture {

    @Id
    private ObjectId _id;
    //@Indexed
    private String Immat;
    private String NumChassis;
    private String Modele;
    private Date DateCirculation;
    private Float PuissanceFiscale;
    private int nbCylindre;
    private Float prix;
    private Assurance assurance;

    public Voiture(){};


    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String get_id(){
        return _id.toHexString();
    }

    public void setNumChassis(String numChassis) {
        this.NumChassis = numChassis;
    }

    public String getNumChassis(){
        return NumChassis;
    }

    public void setImmat(String matricule) {
        this.Immat = matricule;
    }

    public String getImmat(){
        return Immat;
    }

    public void setModele(String modele) {
        this.Modele = modele;
    }
    public String getModele(){
        return Modele;
    }
    public void setDateCirculation(Date DateCirculation){
        this.DateCirculation=DateCirculation;
    }

    public void setPuissanceFiscale(Float PuissanceFiscale){
        this.PuissanceFiscale=PuissanceFiscale;
    }

    public void setNbCylindre(int nbCylindre) {
        this.nbCylindre = nbCylindre;
    }

    public int getNbCylindre(){
        return nbCylindre;
    }

    public Date getDateCirculation() {
        return DateCirculation;
    }

    public Float getPuissanceFiscale() {
        return PuissanceFiscale;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Assurance getAssurance() {
        return assurance;
    }

    public void setAssurance(Assurance assurance) {
        this.assurance = assurance;
    }


}
