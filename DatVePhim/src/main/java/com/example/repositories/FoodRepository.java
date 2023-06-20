package com.example.repositories;

import com.example.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    Food findByNameContainsIgnoreCase(String name);

    @Query(value = "SELECT name from Food", nativeQuery = true)
    List<String> getNames();


}

