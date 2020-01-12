package com.example.demo.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Models.Voiture;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoOperations;

public class Queris {

    @Autowired
    MongoOperations mongoOps;

    public Voiture getLocationByModele(String modele){
    Query q=new Query(Criteria.where("Modele").is(modele));
    Voiture loc=mongoOps.findOne(q,Voiture.class);

    return loc;
    }
}
