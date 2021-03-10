package com.reservationsystem.interfaces;

import com.reservationsystem.application.RestaurantService;
import com.reservationsystem.domain.MenuItem;
import com.reservationsystem.domain.MenuItemRepository;
import com.reservationsystem.domain.Restaurant;
import com.reservationsystem.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
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

   Restaurant restaurant= restaurantService.getRestaurant(id);

        return restaurant;
}

@PostMapping("/restaurants")
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource)
        throws URISyntaxException {
    String name = resource.getName();
    String address = resource.getAddress();
    List<MenuItem> menuItems = resource.getMenuItems();

    Restaurant restaurant = restaurantService.addRestaurant(
            Restaurant.builder()
                    .name(name)
                    .address(address)
                    .menuItems(menuItems)
                    .build());

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
}

@PatchMapping("/restaurants/{id}")
    public String update( @PathVariable("id") Long id, @Valid @RequestBody Restaurant resource){
        String name = resource.getName();
        String address = resource.getAddress();
        restaurantService.updateRestaurant(name, address, id);
        return "{}";
}

}
