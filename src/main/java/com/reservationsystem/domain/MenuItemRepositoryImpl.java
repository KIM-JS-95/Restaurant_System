package com.reservationsystem.domain;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class MenuItemRepositoryImpl implements MenuItemRepository {
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public MenuItemRepositoryImpl(){
        menuItems.add(new MenuItem("Kimchi"));
    }

    @Override
    public List<MenuItem> findAllByRestaurantId(Long id) {
        return menuItems;
    }
}
