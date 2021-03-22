package com.reservationsystem.application;

import com.reservationsystem.domain.Category;
import com.reservationsystem.domain.CategoryRepository;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;


public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void getCategory(){

        List<Category> category = new ArrayList<>();
        category.add(Category.builder().name("Good").build());

        given(categoryRepository.findAll()).willReturn(category);


    }
}
