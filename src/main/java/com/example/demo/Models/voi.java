package com.example.demo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="voi")
public class voi {

    @Field(value="Modele")

    private String Modele;
    private Integer count;
    @Id
    @Indexed
    private String _id;

    public void set_id(String _id){
        this._id=_id;}


    public String get_id(){
        return _id;
    }
    public String getModele() {
        return Modele;
    }
    public void setModele(String Modele) {
        this.Modele = Modele;
    }
    public Integer getCount() {
        return count;
    }
    public void setNoOfBusinesses(Integer count) {
        this.count = count;
    }
}
