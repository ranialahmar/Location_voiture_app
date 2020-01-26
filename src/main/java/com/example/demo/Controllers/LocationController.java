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



    @GetMapping(value="/find/{mod}")
        List<Location> getlistLocByMod( @PathVariable String mod){
        ObjectId ob= new ObjectId(voitureRepository.findByModele(mod).get_id());
        List<Location> res=locationRepository.LocationByVoituree(ob);

        return res;
    }



    @GetMapping(value="/count/{mod}")
    Integer getCountLocByModele(@PathVariable String mod) {
        return locationRepository.countAll(mod);
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


    //le modele de voiture le plus populaire
    @GetMapping(value="/ModelePop")
    public  String getModelePop(){

        Aggregation agg = Aggregation.newAggregation(group("voiture").count().as("count"),
                project("count").and("voiture").previousOperation(),
                sort(Sort.Direction.DESC, "count"),limit(1));

        voitureModele result = mongoTemplate.aggregate(agg, "Location", voitureModele.class).getUniqueMappedResult();
        String modele= result.getVoiture().getModele();


        return modele;
    }

    //la plus longue durée de location
    @GetMapping(value="/LongLoc")
    public int getLongLocation(){

        Aggregation agg = Aggregation.newAggregation(
                project("date_deb","date_fin","voiture").andExpression("(date_fin-date_deb)/86400000").as("duration"),
                sort(Sort.Direction.DESC, "duration"),limit(1));

        VoitureDuree result = mongoTemplate.aggregate(agg, "Location", VoitureDuree.class).getUniqueMappedResult();
        int dureeLocation=result.getDuration();

        return dureeLocation;
    }

    //voiture la plus louée en terme de nombre de jours de location
    @GetMapping(value="/VoiturePlusLoueJrs")
    public  Voiture getVoiturePLusLoueeJrs(){

        Aggregation agg = Aggregation.newAggregation(
                project("voiture","date_deb","date_fin").andExpression("(date_fin-date_deb)/86400000").as("dur"),
                group("voiture").sum("dur").as("duration"),
                project("duration").and("voiture").previousOperation(),
                sort(Sort.Direction.DESC, "duration"),limit(1));

        VoitureDuree result = mongoTemplate.aggregate(agg, "Location", VoitureDuree.class).getUniqueMappedResult();
        Voiture voiture= result.getVoiture();


        return voiture;
    }


    //Voiture la plus louée en terme de nombre de location
    @GetMapping(value="/VoiturePop")
    public  Voiture getVoitureePlusLoc(){

        Aggregation agg = Aggregation.newAggregation(group("voiture").count().as("count"),
                project("count").and("voiture").previousOperation(),
                sort(Sort.Direction.DESC, "count"),limit(1));

        voitureModele result = mongoTemplate.aggregate(agg, "Location", voitureModele.class).getUniqueMappedResult();
        Voiture v=result.getVoiture();


        return v;
    }

    //la voiture la plus accidentée
    @GetMapping(value="/PlusAcci")
    public Voiture getVoiturePlusAccident(){
        ProjectionOperation project = Aggregation.
                project("accident","voiture").
                and(ArrayOperators.arrayOf("accident").length()).as("countAcc");
        Aggregation agg = Aggregation.newAggregation(project,
                sort(Sort.Direction.DESC, "countAcc"), limit(1));

        VoitureAccident  result = mongoTemplate.aggregate(agg, "Location", VoitureAccident.class).getUniqueMappedResult();
        Voiture voiture=result.getVoiture();

        return voiture;

    }


    //voiture n'a jamais été allouée
    @GetMapping(value="/VoitureNA")
    public Voiture getVoitureNonAlloué(){
        List<Voiture> listV= voitureRepository.findAll();
        List<Location> listLoc=locationRepository.findAll();
        Voiture voiture=new Voiture();

        for(Voiture voi:listV){
            for(Location l:listLoc){
                if(voi != l.getVoiture()){
                    voiture=voi;
                }
            }
        }
        return voiture;
    }

    //le client qui a effectuée plus de location
    @GetMapping(value="/LocationClient")
    public Client getClientPlusLoc(){
        Aggregation agg = Aggregation.newAggregation(group("client").count().as("countCli"),
                project("countCli").and("client").previousOperation(),
                sort(Sort.Direction.DESC, "countCli"),limit(1));

        clientLoc result = mongoTemplate.aggregate(agg, "Location", clientLoc.class).getUniqueMappedResult();
        Client client=result.getClient();
        return client;

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


    @RequestMapping(value="/Location/{id}",method=RequestMethod.DELETE)
    public  @ResponseBody List<Location> deleteLocation(@PathVariable("id") ObjectId id) {
        locationRepository.delete(locationRepository.findLocationBy_id(id));
        return locationRepository.findAll();
    }
}
