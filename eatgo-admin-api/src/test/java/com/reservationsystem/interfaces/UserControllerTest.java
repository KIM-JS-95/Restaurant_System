package com.reservationsystem.interfaces;

import com.reservationsystem.application.UserService;
import com.reservationsystem.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest{

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void list() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .email("tester@example.com")
                .name("Tester")
                .level(1L)
                .build());

        given(userService.getUsers()).willReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Tester")));
    }

    @Test
    public void create() throws Exception{
        String email = "admin@exmaple.com";
        String name = "Administrator";

        User user = User.builder().email(email).name(name).build();

        given(userService.addUser(email, name)).willReturn(user);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"admin@exmaple.com\",\"name\":\"Administrator\"}"))
                .andExpect(status().isCreated());


        verify(userService).addUser(email,name);
    }

    @Test
    public void update() throws Exception {

        mvc.perform(patch("/users/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"admin@exmaple.com\"," +
                        "\"name\":\"Administrator\",\"level\":100}"))
                .andExpect(status().isOk());

        Long id = 1004L;
        String email = "admin@exmaple.com";
        String name = "Administrator";
        Long level = 100L;

        verify(userService).updateUser(email,name, id,level);

    }

    @Test
    public void delete1() throws Exception{

        Long id = 1004L;

        mvc.perform(delete("/users/1004"))
                .andExpect(status().isOk());


        verify(userService).deleteuser(id);
    }

}