package com.reservationsystem.interfaces;

import com.reservationsystem.application.RestaurantService;
import com.reservationsystem.domain.MenuItem;
import com.reservationsystem.domain.MenuItemRepository;
import com.reservationsystem.domain.Restaurant;
import com.reservationsystem.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants=restaurantService.getRestaurants();
    return restaurants;
}

@GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {

        //기본정보 + 메뉴정보 제공
   Restaurant restaurant= restaurantService.getRestaurant(id);


    List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
    restaurant.setMenuItems(menuItems);
        return restaurant;
}

@PostMapping("/restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant resource) throws URISyntaxException {
    String name = resource.getName();
    String address = resource.getAddress();

        Restaurant restaurant = new Restaurant(name, address,1234L);
        restaurantService.addRestaurant(restaurant);

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
}

}
