package com.reservationsystem.interfaces;


import com.reservationsystem.application.RestaurantService;
import com.reservationsystem.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private MenuItemRepository menuItemRepository;


    @Test
    public void list() throws Exception{
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .categoryId(1L)
                .name("JOKER HOUSE")
                .address("Seoul")
                .build());

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(containsString("\"name\":\"JOKER HOUSE\"")
                ));
    }

    @Test
    public void detailWithExisted() throws Exception{
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .categoryId(1L)
                .name("JOKER HOUSE")
                .address("Seoul")
                .build();



    given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);


        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER HOUSE\"")
                ));

    }

    @Test
    public void detailWithNotExisted() throws Exception{
                given(restaurantService.getRestaurant(404L))
                        .willThrow(new RestaurantNotFoundException(404L));

        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

    @Test
    public void createWithValidData() throws Exception{
        given(restaurantService.addRestaurant(any())).will(invocation -> {
                    Restaurant restaurant = invocation.getArgument(0);
                    return Restaurant.builder()
                            .name(restaurant.getName())
                            .address(restaurant.getAddress())
                            .id(1234L)
                            .build();
                });


        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryId\":1,\"name\":\"BeRyong\",\"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location",  "/restaurants/1234"))
        .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());
    }

    @Test
    public void createWithInValidData() throws Exception{
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryId\":1,\"name\":\"\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest());

    }


    @Test
    public void updateWithValidData() throws Exception {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryId\":1,\"name\":\"JOKER bar\", \"address\":\"Busan\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant("JOKER bar" , "Busan", 1004L);
    }

    @Test
    public void updateWithInValidData() throws Exception {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryId\":1,\"name\":\"\", \"address\":\"\"}"))
                .andExpect(status().isBadRequest());

    }

 }


