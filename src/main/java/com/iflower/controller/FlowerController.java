package com.iflower.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iflower.model.Flower;
import com.iflower.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
public class FlowerController {
    @Autowired
    public FlowerRepository flowerRepository;

    @Autowired
    GsonBuilder gsonBuilder = new GsonBuilder();

    @CrossOrigin
    @PostMapping("/flowers/add")
    public ResponseEntity<String> addFlower(@Valid @RequestBody Flower flower) {
        String name = flower.getName();
        if (flowerRepository.findByName(name) == null) {
            flowerRepository.save(flower);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Flower already exists!", HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @GetMapping("/flowers/get_all")
    public String getAllFlowers() {
        List<Flower> list = flowerRepository.findAll();
        List<String> jsonList = new ArrayList<>();

        Gson json = gsonBuilder.create();
        for (Flower flower : list) {
            jsonList.add(json.toJson(flower));
        }

        return json.toJson(jsonList);
    }

}
