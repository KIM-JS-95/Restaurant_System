package com.reservationsystem.application;


import com.reservationsystem.domain.User;
import com.reservationsystem.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<User> getUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User addUser(String email, String name){

        User user = User.builder().email(email).name(name).build();

       return userRepository.save(user);
    }

    public User updateUser(String email, String name, Long id, Long level) {

        User user = userRepository.findById(id).orElse(null);

        user.setEmail(email);
        user.setName(name);
        user.setLevel(level);

        return user;
    }

    public User deleteuser(Long id) {

       User user= userRepository.findById(id).orElse(null);

       user.deativate();
        return user;
    }
}
