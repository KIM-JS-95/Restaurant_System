package com.reservationsystem.application;

import com.reservationsystem.domain.User;
import com.reservationsystem.domain.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserServiceTest{

    @MockBean
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }


    @Test
    public void getUsers(){
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder()
                .email("tester@example.com")
                .name("tester")
                .level(1L)
                .build());

        given(userRepository.findAll()).willReturn(mockUsers);

        List<User> users = userService.getUsers();

        User user = users.get(0);

        assertThat(user.getName(),is("tester"));
    }

    @Test
    public void addUser(){

        String email = "admin@exmaple.com";
        String name = "Administrator";

        User mockUser = User.builder().email(email).name(name).build();

        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.addUser(email, name);

        assertThat(user.getName(),is("Administrator"));
    }

    @Test
    public void updateUser(){

        String email = "admin@exmaple.com";
        String name = "Superman";
        Long id = 1004L;
        Long level = 100L;

        User mockUser = User.builder().email(email).name(name).build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.updateUser(email, name, id, level);

        verify(userRepository).findById(eq(id));

        assertThat(user.getName(), is("Superman"));
    }

    @Test
    public void delete1(){

        Long id = 1004L;

        User mockUser = User.builder()
                .id(id)
                .email("admin@exmaple.com")
                .name("Administrator")
                .level(100L)
                .build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.deleteuser(1004L);

        verify(userRepository).findById(1004L);

      assertThat(user.isAdmin(),is(false));
        assertThat(user.isActive(),is(false));
    }

}