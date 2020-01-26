package com.example.demo.Repositories;

import com.example.demo.Models.Voiture;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface VoitureRepository extends MongoRepository<Voiture,ObjectId> {



    //Voiture findByImmat(String Immat);
    //Voiture findVoitureByNumChassis(String NumChassis);
    Voiture findBy_id(ObjectId _id);

    @Query(value="{Modele: ?0}", count = true)
    Integer countAllMod(String Modele);

    @Query(value="{}", sort = "{prix:1}")
    List<Voiture> sortby();
    @Query(value="{Modele: ?0}")
    Voiture findByModele(String Modele);
}
