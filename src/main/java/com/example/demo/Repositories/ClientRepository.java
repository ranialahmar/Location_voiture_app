package com.example.demo.Repositories;

import com.example.demo.Models.Client;
import org.apache.catalina.LifecycleState;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ClientRepository extends MongoRepository<Client,String> {

    Client findClientByNCIN(Long NCIN);
    Client findClientBy_id(ObjectId _id);
    @Query(value="{}",fields ="{NCIN:1}")
    List<Client> findAllNCIN();

}
