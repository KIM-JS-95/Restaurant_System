package com.reservationsystem.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private List<Restaurant> restaurants = new ArrayList<>();

     public RestaurantRepositoryImpl() {
         restaurants.add(new Restaurant("bob", "Seoul", 1004L));
         restaurants.add(new Restaurant("Cyber food", "Seoul", 2020L));
     }

     @Override
     public List<Restaurant> findAll(){

        return restaurants;
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurants.stream()
        .filter(r ->r.getId().equals(id))
        .findFirst()
        .orElse(null);
    }
}
