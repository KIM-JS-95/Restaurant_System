package com.reservationsystem.application;


import com.reservationsystem.domain.Category;
import com.reservationsystem.domain.CategoryRepository;
import com.reservationsystem.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {


    //  @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    public List<Category> getCategory() {

        List<Category> regions = categoryRepository.findAll();

        //regions.add(Region.builder().name("Seoul").build());
        return regions;
    }

    public Category addCategory(String name) {

        Category category = Category.builder().name(name).build();

        categoryRepository.save(category);
        return category;
    }
}
