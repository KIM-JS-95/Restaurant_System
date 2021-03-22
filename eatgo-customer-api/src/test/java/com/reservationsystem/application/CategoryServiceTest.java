package com.reservationsystem.application;

import com.reservationsystem.domain.Category;
import com.reservationsystem.domain.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;


public class CategoryServiceTest {

    @MockBean
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getCategory(){

        List<Category> category = new ArrayList<>();
        category.add(Category.builder().name("Good").build());

        given(categoryRepository.findAll()).willReturn(category);


    }
}
