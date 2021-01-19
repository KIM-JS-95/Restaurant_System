package com.reservationsystem.domain;

import jdk.internal.vm.compiler.word.LocationIdentity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    Optional findById(Long id);

    Restaurant save(Restaurant restaurant);
}
