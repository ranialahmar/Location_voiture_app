package com.example.demo.Repositories;

import com.example.demo.Models.Location;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoAdminOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LocationRepository extends MongoRepository<Location, String> {
    Location findLocationBy_id(ObjectId _id);

    @Query(value = "{'voiture.$id':?0}", count = true)
    Long LocationByVoiture(ObjectId Modele);

    @Query(value = "{'voiture.$id':?0}", count = true, sort = "{prix:-1}")
    List<Location> LocationByVoituree(ObjectId Modele);

    @Query(value="{Voiture.Modele: ?0}", count = true)
    Integer countAll(String Modele);
}
