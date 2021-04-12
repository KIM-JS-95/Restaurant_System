package com.reservationsystem.interfaces;


import com.reservationsystem.application.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/restaurant/{restaurantId}/reservations")
    public ResponseEntity<?> create(
            @PathVariable Long restaurantId
    ) throws URISyntaxException {

        Long userId=1L;
        String name ="Tester";
        String date= "2019-12-24";
        String time = "20:00";
        Integer partySize = 20;

        reservationService.addReservation(restaurantId,userId,name,date,time,partySize);
        String url = "/restaurant/"+restaurantId+"/reservations/1";
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
