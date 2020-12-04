package com.iflower.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iflower.model.User;
import com.iflower.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    GsonBuilder gsonBuilder = new GsonBuilder();

    @CrossOrigin
    @PostMapping("/users/add")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user) {
        String username = user.getUsername();
        if (userRepository.findByUsername(username) == null) {
            userRepository.save(user);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User already exists!", HttpStatus.CONFLICT);
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