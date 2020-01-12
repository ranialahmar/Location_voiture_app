package com.example.demo.Models;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;



@Document(collection="Client")
public class Client  {

    @Id
    private ObjectId _id;
    @Indexed
    private Long NCIN;
    private Long NPermis;
    private String nom;
    private String prenom;
    private Date DateNaiss;
    //@DBRef
    private Adresse adresse;

    public Client(){}

    public  String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setNPermis(Long NPermis){this.NPermis=NPermis;}

    public Long getNPermis(){ return NPermis;}

    public Long getNCIN() {
        return NCIN;
    }

    public void setNCIN(Long NCIN) {
        this.NCIN = NCIN;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaiss() {
        return DateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        DateNaiss = dateNaiss;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
