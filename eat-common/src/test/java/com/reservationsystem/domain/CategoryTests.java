package com.reservationsystem.domain;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CategoryTests {

    @Test
    public void creation() {
        Category category = Category.builder().name("Korean food").build();

        assertThat(category.getName(), is("Korean food"));
    }
}
