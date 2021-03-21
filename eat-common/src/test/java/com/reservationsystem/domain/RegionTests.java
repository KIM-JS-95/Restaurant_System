package com.reservationsystem.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RegionTests {

    @Test
    public void creation(){
        Region region = Region.builder().name("Seoul").build();

        assertThat(region.getName(), is("Seoul"));
    }


}