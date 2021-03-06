package com.reservationsystem.application;


import com.reservationsystem.domain.MenuItem;
import com.reservationsystem.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;


    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;

    }
    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {

    for(MenuItem menuItem : menuItems) {
        update(restaurantId, menuItem);
    }
    }

    private void update(Long restaurantId, MenuItem menuItem) {
        if(menuItem.isDestroy()){
            menuItemRepository.deleteById(menuItem.getId());
            return;
        }
        menuItem.setRestaurantId(restaurantId);
        menuItemRepository.save(menuItem);
    }

    public List<MenuItem> getMenuItems(long restaurantId) {

        List<MenuItem> menuItems =menuItemRepository.findAllByRestaurantId(restaurantId);

        return menuItems;
    }
}
