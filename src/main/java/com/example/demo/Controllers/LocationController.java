package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.Repositories.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")

public class LocationController{

    @Autowired
    MongoOperations mongoOps;
    @Autowired
    MongoTemplate mongoTemplate;

   /* AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
    MongoOperations mongoOperation =
            (MongoOperations) ctx.getBean("mongoTemplate");
*/

   Queris queris;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    VoitureRepository voitureRepository;

    @GetMapping(value="/Locations")
    public  @ResponseBody List<Location> getLocations(){
        return locationRepository.findAll();
    }


    @GetMapping(value="/NbLocations")
    public long getNbLocations(){
        return locationRepository.count();

    }


    @GetMapping(value = "/LocationID/{id}")
    public  @ResponseBody Location  getLocationById(@PathVariable("id") ObjectId _id) {
        return locationRepository.findLocationBy_id(_id);
    }

    @PostMapping(value="/Location")
    public  @ResponseBody String  AddLocation(@Valid @RequestBody Location location){

        Client c=clientRepository.findClientByNCIN(location.getClient().getNCIN());
        Voiture v=voitureRepository.findBy_id(new ObjectId(location.getVoiture().get_id()));
        if( c !=null && v!= null) {
            location.set_id(ObjectId.get());
            locationRepository.save(location);
            return "une location est ajoutée";
        }
        else {
           return "verfier les données entrées";
        }


    }

    @PutMapping(value="/LocationModify/{id}")
    public @ResponseBody Location modifyLocationtById(@PathVariable("id") ObjectId _id, @Valid @RequestBody Location location) {

        location.set_id(_id);

        locationRepository.save(location);
        return location;
    }

    @GetMapping(value = "/Location/client/{id}")
    public ArrayList<Location> getLocationByClient(@PathVariable String id){
        List<Location> locations = locationRepository.findAll();
        ArrayList<Location> locClient=new ArrayList<>();
        for (Location locationList:locations
        ) {
            if(locationList.getClient() != null){
                if(locationList.getClient().get_id().equals(id)) {
                    locClient.add(locationList);
                }
            }
        }
        return locClient;
    }

@GetMapping(value="/counttModele")
public  Voiture getLocationByModele(){
    /*Query q=new Query(Criteria.where("Modele").is(modele));
    Voiture loc=mongoOps.findOne(q,Voiture.class);
    SortOperation a= Aggregation.sort(Sort.Direction.DESC, "voiture.prix");*/

    /*TypedAggregation<Location> agg = newAggregation(Location.class,
            group("voiture").count().as("total"),
            project("voiture").and("total").previousOperation(),
            sort(Sort.Direction.DESC, "total"),
            limit(1)


    );



    /*Query qq=new Query();
    qq.with(Sort.by(Sort.Direction.DESC, "voiture.prix"));
    List <Location> looc=mongoTemplate.find(qq,Location.class);
    //List <Voiture> loc= voitureRepository.findAll(Sort.by(Sort.Direction.DESC, "prix"));*/
     /*
     AggregationResults<Location> groupResults
            = mongoTemplate.aggregate(agg,Location.class);
   Location result = groupResults.getUniqueMappedResult();*/

    AggregationOperation group = Aggregation.group("Modele").count().as("count");
    AggregationOperation project = Aggregation.project("count").andExclude("_id").andInclude(Fields.from(Fields.field("voiture.Modele", "_id")));


    Aggregation agg = Aggregation.newAggregation(group("Modele").count().as("count"),
            project("count").and("Modele").previousOperation(),
            sort(Sort.Direction.DESC, "count"),limit(1));

    Voiture result = mongoTemplate.aggregate(agg, "Voiture", Voiture.class).getUniqueMappedResult();





    return result;
}

    @GetMapping(value="/count/{mod}")
    Integer getAllc(@PathVariable String mod) {
        return locationRepository.countAll(mod);
    }

    @GetMapping(value="/VoitureNA")
    public Voiture getVoitureNonAlloué(){
        List<Voiture> listV= voitureRepository.findAll();
        List<Location> listLoc=locationRepository.findAll();
        Voiture voiture=new Voiture();
        /*for(Location l:listLoc){
        Voiture v = listV.stream().filter(voi->{if(!(l.getVoiture()).equals(voi)){ return true;}return false;}).findAny().orElse(null);
            voiture =v;
        }
        return voiture;*/

        for(Voiture voi:listV){
            for(Location l:listLoc){
                if(voi != l.getVoiture()){
                    voiture=voi;
                }
            }
        }
        return voiture;
    }
    @GetMapping(value = "/Location/voiture/{id}")
    public ArrayList<Location> getLocationByVoiture(@PathVariable String id){
        List<Location> locations = locationRepository.findAll();
        ArrayList<Location> locVoitures=new ArrayList<>();

        for (Location locationList:locations
        ) {
            if(locationList.getVoiture() != null){
                if(locationList.getVoiture().get_id().equals(id)) {
                    locVoitures.add(locationList);
                }
            }
        }
        return locVoitures;
    }


    @RequestMapping(value="/Location/{id}",method=RequestMethod.DELETE)
    public  @ResponseBody List<Location> deleteLocation(@PathVariable("id") ObjectId id) {
        locationRepository.delete(locationRepository.findLocationBy_id(id));
        return locationRepository.findAll();
    }
}
