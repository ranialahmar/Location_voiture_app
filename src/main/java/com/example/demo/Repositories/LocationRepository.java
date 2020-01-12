package com.example.demo.Repositories;

import com.example.demo.Models.Location;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoAdminOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LocationRepository extends MongoRepository<Location, String> {
    Location findLocationBy_id(ObjectId _id);

    @Query(value = "{'Location.voiture.Modele':?0}", count = true)
    Long LocationByVoiture(String Modele);

    @Query(value="{voiture.Modele: ?0}", count = true)
    Integer countAll(String Modele);
}
