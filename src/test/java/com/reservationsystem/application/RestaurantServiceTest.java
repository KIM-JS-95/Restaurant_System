package com.reservationsystem.application;

import com.reservationsystem.domain.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


public class RestaurantServiceTest{

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private MenuItemRepository menuItemRepository;

    @Before
    public void setUp() {
    MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();
        mockMenuItemRepository();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);

    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Kimchi"));
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);

    }

    private void mockRestaurantRepository() {
        Restaurant restaurant = new Restaurant("bob","Seoul", 1004L);
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(restaurant);
        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(restaurant);

    }


    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        assertThat(restaurant.getId(), is(1004L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertThat(menuItem.getName(), is("Kimchi"));
    }

    @Test
    public void Restaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void addRestaurant(){
        Restaurant restaurant = new Restaurant("BeRyong", "Busan");
        Restaurant saved = new Restaurant("BeRyong", "Busan", 1234L);
        Restaurant created =restaurantService.addRestaurant(restaurant);

        given(restaurantRepository.save(any())).willReturn(saved);
        assertThat(created.getId(), is(1234L));
    }
}