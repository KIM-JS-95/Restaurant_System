package com.reservationsystem.application;

import com.reservationsystem.domain.MenuItem;
import com.reservationsystem.domain.MenuItemRepository;
import com.reservationsystem.domain.Restaurant;
import com.reservationsystem.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RestaurantService {

    @Autowired
    MenuItemRepository menuItemRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository){
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant = restaurantRepository.findById(id);

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        Restaurant saved = restaurantRepository.save(restaurant);
        return saved;
    }
}
