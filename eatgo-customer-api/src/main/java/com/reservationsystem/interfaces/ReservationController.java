package com.reservationsystem.interfaces;


import com.reservationsystem.application.ReservationService;
import com.reservationsystem.domain.Reservation;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/restaurant/{restaurantId}/reservations")
    public ResponseEntity<?> create(
            Authentication authentication,
            @PathVariable Long restaurantId,
            @RequestBody Reservation resource
            ) throws URISyntaxException {
        Claims claims = (Claims) authentication.getPrincipal();

        Long userId = claims.get("userId", Long.class);
        String name = claims.get("name", String.class);

        String date= resource.getDate();
        String time = resource.getTime();
        Integer partySize = resource.getPartySize();

        reservationService.addReservation(restaurantId,userId,name,date,time,partySize);
        String url = "/restaurant/"+restaurantId+"/reservations/1";
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
