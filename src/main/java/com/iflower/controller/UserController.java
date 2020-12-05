package com.iflower.controller;

import com.iflower.model.User;
import com.iflower.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@RestController
public class UserController {
    @Autowired
    public UserRepository userRepository;

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
    public ResponseEntity<Object> getAllUsers() {
        List<User> list = userRepository.findAll();

        List<Map<String, String>> entities = new ArrayList<>();
        for (User user : list) {
            entities.add(user.toJson());
        }

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

}
