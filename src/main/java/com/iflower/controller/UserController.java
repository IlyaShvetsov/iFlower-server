package com.iflower.controller;

import javax.validation.Valid;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iflower.repository.UserRepository;
import com.iflower.model.User;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    GsonBuilder gsonBuilder = new GsonBuilder();

    @CrossOrigin
    @PostMapping("/users/add_user")
    public String addUser(@Valid @RequestBody User user) {
        String username = user.getUsername();
        if (userRepository.findByUsername(username) == null) {
            userRepository.save(user);
            return "";
        } else {
            String message = "User already exists!";
            System.out.println(message);
            return message;
        }
    }

    @CrossOrigin
    @GetMapping("/users/get_all")
    public String getAllUsers() {
        List<User> list = userRepository.findAll();
        List<String> jsonList = new ArrayList<>();

        Gson json = gsonBuilder.create();
        for (User user : list) {
            jsonList.add(json.toJson(user));
        }

        return json.toJson(jsonList);
    }

}
