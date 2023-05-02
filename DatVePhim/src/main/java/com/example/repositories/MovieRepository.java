package com.example.repositories;

import com.example.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Movie findByNameContainsIgnoreCase(String name);

    @Query(value = "SELECT name from Movie", nativeQuery = true)
    List<String> getNames();


}

