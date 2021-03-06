package com.reservationsystem.domain;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserTests {

    @Test
    public void creation() {
        User user = User.builder()
                .email("tester@example.com")
                .name("tester")
                .level(100L)
                .build();

        assertThat(user.getName()).isEqualTo("tester");
        assertThat(user.isAdmin()).isTrue();
        assertThat(user.isActive()).isTrue();

        user.deativate();

        assertThat(user.isActive()).isFalse();
    }

    @Test
    public void restaurantOwner() {
        User user = User.builder().level(1L).build();

        assertThat(user.isRestaurantOwner()).isFalse();

        user.setRestaurantId(1004L);

        assertThat(user.isRestaurantOwner()).isTrue();
        assertThat(user.getRestaurantId()).isEqualTo(1004L);
    }


}
