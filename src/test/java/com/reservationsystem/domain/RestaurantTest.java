package com.reservationsystem.domain;

import com.reservationsystem.interfaces.RestaurantController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantTest {
    @Test
    public void create() {
        Restaurant restaurant = new Restaurant("bob zip", "Seoul", 1004L);

        assertThat(restaurant.getName(),is("bob zip"));
        assertThat(restaurant.getAddress(),is("Seoul"));
        assertThat(restaurant.getId(),is(1004L));
    }
    @Test
    public void information(){
        Restaurant restaurant = new Restaurant("bob zip", "Seoul", 1004L);
        assertThat(restaurant.getInformation(),is("bob zipinSeoul"));
    }
}
