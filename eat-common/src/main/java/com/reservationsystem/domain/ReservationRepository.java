package com.reservationsystem.domain;

import com.reservationsystem.domain.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Long, Reservation> {

    Reservation save(Reservation reservation);

    List<Reservation> findAllByRestaurantId(Long id);
}
