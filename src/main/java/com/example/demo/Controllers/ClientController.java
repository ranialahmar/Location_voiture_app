package com.example.demo.Controllers;


import com.example.demo.Models.Client;
import com.example.demo.Repositories.ClientRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;


    @GetMapping(value="/Clients")
    public  @ResponseBody List<Client> getClients(){
        return clientRepository.findAll();
    }


    @GetMapping(value="/NbClients")
    public long getNbClient(){
        return clientRepository.count();

    }

    @GetMapping(value="/ncn")
    public  @ResponseBody List<Client> getCIN(){
        return clientRepository.findAllNCIN();
    }
    @GetMapping(value="/Client/{cin}")
    public  @ResponseBody Client getClientByCIN(@PathVariable Long cin){
        return clientRepository.findClientByNCIN(cin);
    }
    @GetMapping(value = "/ClientID/{id}")
    public  @ResponseBody Client getClientById(@PathVariable("id") ObjectId _id) {
        return clientRepository.findClientBy_id(_id);
    }

    @PostMapping(value="/Client")
    public  @ResponseBody List<Client> AddClient(@Valid @RequestBody Client client){
        client.set_id(ObjectId.get());
        clientRepository.save(client);

        return clientRepository.findAll();

    }

    @PutMapping(value="/ClientModify/{id}")
    public @ResponseBody Client modifyClientById(@PathVariable("id") ObjectId _id, @Valid @RequestBody Client client) {

        client.set_id(_id);
        clientRepository.save(client);
        return client ;
    }

    @PutMapping(value="/ModifyClient/{cin}")
    public @ResponseBody Client modifyClientByCIN(@PathVariable("cin") Long NCIN, @Valid @RequestBody Client client) {
        Client newclient=clientRepository.findClientByNCIN(NCIN);
       client.set_id(new ObjectId(newclient.get_id()));
        clientRepository.save(client);
        return client;
    }

    @RequestMapping(value = "/Client/{cin}",method=RequestMethod.DELETE)
    public  @ResponseBody List<Client> deleteClient(@PathVariable Long cin) {
        clientRepository.delete(clientRepository.findClientByNCIN(cin));
        return clientRepository.findAll();
    }
}
