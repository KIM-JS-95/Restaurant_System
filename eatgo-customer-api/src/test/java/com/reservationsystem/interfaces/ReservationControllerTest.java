package com.reservationsystem.interfaces;

import com.reservationsystem.application.ReservationService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void create() throws Exception{
        Long restaurantId = 1004L;
        Long userId=1L;
        String name ="Tester";
        String date= "2019-12-24";
        String time = "20:00";
        Integer partySize = 20;

        mvc.perform(post("/restaurant/1004/reservations"))
                .andExpect(status().isCreated());

        verify(reservationService).addReservation(eq(restaurantId),eq(userId), eq(name), eq(date), eq(time), eq(partySize));
    }

}