package com.reservationsystem.domain;

public class Restaurant {

    private final String name;
    private final String address;
    private final Long id;


    public Restaurant(String name, String address, Long id){
        this.address =address;
        this.name=name;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getInformation(){
        return name + "in" + address;
    }
}
