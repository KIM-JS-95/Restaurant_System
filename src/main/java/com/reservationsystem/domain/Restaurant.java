package com.reservationsystem.domain;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private final String name;
    private final String address;
    private final Long id;
    private List<MenuItem> menuItems= new ArrayList<MenuItem>();


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

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }
    public void addMenuItem(MenuItem menuItem){
     menuItems.add(menuItem);
    }

    public void setMenuItems(List<MenuItem> menuItems){
        for(MenuItem menuItem : menuItems){
            addMenuItem(menuItem);
        }
    }
}
