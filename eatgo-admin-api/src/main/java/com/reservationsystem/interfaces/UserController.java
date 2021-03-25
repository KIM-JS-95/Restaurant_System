package com.reservationsystem.interfaces;


import com.reservationsystem.application.UserService;
import com.reservationsystem.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> list(){
        List<User> users = userService.getUsers();
        return users;
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {
        String email = resource.getEmail();
        String name = resource.getName();
        User user= userService.addUser(email, name);

        String url = "/users/" + user.getId();
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }

    @PatchMapping("/users/{id}")
    public String update(@RequestBody User resource,
                         @PathVariable("id") Long id)
    {
    String email = resource.getEmail();
    String name = resource.getName();
    Long level = resource.getLevel();

    userService.updateUser(email,name,id,level);
        return "{}";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") Long id) {
userService.deleteuser(id);
        return "{}";
    }
}
