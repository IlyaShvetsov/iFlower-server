package com.iflower.controller;

import com.iflower.model.Flower;
import com.iflower.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@RestController
public class FlowerController {
    @Autowired
    public FlowerRepository flowerRepository;

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
    public ResponseEntity<Object> getAllFlowers() {
        List<Flower> list = flowerRepository.findAll();

        List<Map<String, String>> entities = new ArrayList<>();
        for (Flower flower : list) {
            if (flower != null) {
                entities.add(flower.toJson());
            }
        }

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/flowers/delete")
    public ResponseEntity<String> delete(@Valid @RequestBody String name) {
        Flower flower = flowerRepository.findByName(name);
        if (flower == null) {
            return new ResponseEntity<>("Wrong name", HttpStatus.FORBIDDEN);
        } else {
            flowerRepository.delete(flower);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
    }

    @CrossOrigin
    @PostMapping("/images/upload")
    public @ResponseBody
    ResponseEntity<String> addImage(@RequestParam("name") String name,
                                    @RequestParam("file") MultipartFile file) {

        File dir = new File("images/");
        dir.mkdir();

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("images/" + name));
                stream.write(bytes);
                stream.close();
                return new ResponseEntity<>("Вы удачно загрузили " + name, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Не удалось загрузить: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Не удалось загрузить файл, потому что он пустой", HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping("/images/download")
    public @ResponseBody
    ResponseEntity<Resource> getImage(@Valid @RequestParam String name) throws FileNotFoundException {
        File file = new File("images/" + name);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
