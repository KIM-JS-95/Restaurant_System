package com.reservationsystem.interfaces;

import com.reservationsystem.application.RestaurantService;
import com.reservationsystem.domain.MenuItemRepository;
import com.reservationsystem.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> list(@RequestParam("region") String region,
                                 @RequestParam("categoryId") Long categoryId) {
        List<Restaurant> restaurants = restaurantService.getRestaurants(region, categoryId);
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {

        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }


}
