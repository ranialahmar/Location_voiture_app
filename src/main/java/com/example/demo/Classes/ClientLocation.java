package com.example.demo.Classes;

import com.example.demo.Models.*;
public class ClientLocation {
    private Client client;
    private Integer countCli;


    public void setClient(Client client){
        this.client=client;}

    public Client getClient(){
        return client;
    }

    public Integer getCountCli() {
        return countCli;
    }
    public void setCountCli(Integer count) {
        this.countCli = count;
    }
}
