package com.reservationsystem.application;

import com.reservationsystem.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class RestaurantService {


    private MenuItemRepository menuItemRepository;
    private RestaurantRepository restaurantRepository;
    private ReviewRepository reviewRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository, ReviewRepository reviewRepository){
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }

    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        List<Review> reviews =reviewRepository.findAllByRestaurantId(id);
        restaurant.setReviews(reviews);
        return restaurant;
    }

    public List<Restaurant> getRestaurants(String region, long categoryId) {
        //TODO: region filtering

        List<Restaurant> restaurants = restaurantRepository.findAllByAddressContainingAndCategoryId(region, categoryId);
        return restaurants;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        Restaurant saved = restaurantRepository.save(restaurant);
        return saved;
    }

    @Transactional
    public Restaurant updateRestaurant(String name, String address, long id) {
        Restaurant restaurant = (Restaurant) restaurantRepository.findById(id).orElse(null);

        restaurant.updatedInformation(name, address);

        return restaurant;
    }

}
