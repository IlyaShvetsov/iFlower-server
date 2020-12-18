package com.iflower.repository;

import com.iflower.model.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FlowerRepository extends JpaRepository<Flower, String>{
    Flower findByName(String name);
}
