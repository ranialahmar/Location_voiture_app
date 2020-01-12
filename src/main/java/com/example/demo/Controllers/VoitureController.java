package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.Repositories.VoitureRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/")
@CrossOrigin(value = {"*"},allowCredentials="true",allowedHeaders = {"*"},exposedHeaders = {"Content-Disposition"})
public class VoitureController{


    @Autowired
    VoitureRepository voitureRepository;

    @GetMapping(value="/Voitures")
    public  @ResponseBody List<Voiture> getVoitures(){
        return voitureRepository.findAll();
    }


    @GetMapping(value="/NbVoitures")
    public long getNbVoitures(){
        return voitureRepository.count();

    }

    @GetMapping(value="/mod/{mod}")
    public Integer getAllmod(@PathVariable String mod) {
        return voitureRepository.countAllMod(mod);
    }

    @GetMapping(value="/sort")
    public List<Voiture> sort() {
        return voitureRepository.sortby();
    }

    @GetMapping(value="/Matricules")
    public ArrayList<String> getMatricules(){
        ArrayList<String> matricules= new ArrayList<String>();
        List<Voiture> listVoitures= voitureRepository.findAll();

        for (Voiture voiture:listVoitures
        ) {
                    matricules.add(voiture.getImmat());


        }

        return matricules;
    }

    @GetMapping(value="/Prix/{mat}")
    public Float getPrixByMat(@PathVariable String mat){
        List<Voiture> listVoitures= voitureRepository.findAll();
        Float prix=null;
        for (Voiture voiture:listVoitures
        ) {
                if(voiture.getImmat().equals(mat)) {
                    prix=voiture.getPrix();

                }

        }
        return prix;


    }


    @GetMapping(value="/Voiture/{Immat}")
    public   Voiture getVoiture(@PathVariable String Immat){

        List<Voiture> listVoitures= voitureRepository.findAll();
        Voiture voiture=new Voiture();
        for (Voiture v:listVoitures
        ) {
            if(v.getImmat().equals(Immat)) {
                voiture=v;

            }

        }
        return voiture;
    }
    @GetMapping(value = "/VoitureID/{id}")
    public  @ResponseBody Voiture  getVoitureById(@PathVariable("id") ObjectId _id) {
        return voitureRepository.findBy_id(_id);
    }

    @PostMapping(value="/Voiture")
    public  @ResponseBody List<Voiture> AddVoiture(@Valid @RequestBody Voiture voiture){
        voiture.set_id(ObjectId.get());
        voitureRepository.save(voiture);

        return voitureRepository.findAll();

    }

    @PutMapping(value="/VoitureModify/{id}")
    public @ResponseBody Voiture modifyVoituretById(@PathVariable("id") ObjectId _id, @Valid @RequestBody Voiture voiture) {

        voiture.set_id(_id);
        voitureRepository.save(voiture);
        return voiture ;
    }


    @PutMapping(value="/ModifyVoiture/{Immat}")
    public @ResponseBody Voiture modifyVoitureByImmat(@PathVariable String Immat, @Valid @RequestBody Voiture voiture) {
        List<Voiture> listVoitures= voitureRepository.findAll();
        ObjectId _id=null;
        for (Voiture v:listVoitures
        ) {
            if(v.getImmat().equals(Immat)) {
                _id=new ObjectId(v.get_id());
                voiture.set_id(_id);
                voiture.setImmat(Immat);
                voitureRepository.save(voiture);

            }

        }


        return voiture;
    }

    @RequestMapping(value = "/Voiture/{Immat}",method=RequestMethod.DELETE)
    public  @ResponseBody List<Voiture> deleteVoiture(@PathVariable String Immat) {

        List<Voiture> listVoitures= voitureRepository.findAll();
        for (Voiture v:listVoitures
        ) {
            if(v.getImmat().equals(Immat)) {
                voitureRepository.delete(v);

            }

        }

        return voitureRepository.findAll();
    }

    @RequestMapping(value = "/Voiture/{id}",method=RequestMethod.DELETE)
    public  @ResponseBody List<Voiture> deleteVoiture(@PathVariable ObjectId id) {
        voitureRepository.delete(voitureRepository.findBy_id(id));
        return voitureRepository.findAll();
    }
}
