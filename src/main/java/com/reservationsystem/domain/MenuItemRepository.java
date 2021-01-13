package com.reservationsystem.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository {

    List<MenuItem> findAllByRestaurantId(Long id);

}
