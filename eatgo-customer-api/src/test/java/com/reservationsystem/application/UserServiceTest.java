package com.reservationsystem.application;


import com.reservationsystem.domain.User;
import com.reservationsystem.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class UserServiceTest {


    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void registerUser(){

        String email = "tester@example.com";
        String name ="Tester";
        String password ="test";

        userService.registerUser(email,name,password);

        verify(userRepository).save(any());
    }

    @Test(expected= EmailExistedException.class)
    public void registerWithExistedEmail(){

        String email = "tester@example.com";
        String name ="Tester";
        String password ="test";

        User mockUser = User.builder().build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        userService.registerUser(email,name,password);

       verify(userRepository,never()).save(any());
    }

}