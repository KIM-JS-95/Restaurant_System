package com.reservationsystem.interfaces;


import com.reservationsystem.application.CategoryService;
import com.reservationsystem.domain.Category;
import com.reservationsystem.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public List<Category> list(){
        List<Category> regions = categoryService.getCategory();

        return regions;
    }

    @PostMapping("/category")
    public ResponseEntity<?> create(@Valid @RequestBody Category resource)throws URISyntaxException {
        String name = resource.getName();

        Category category = categoryService.addCategory(name);

        String url = "/category/" + category.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

}
