package com.example.services;

import com.example.dto.FoodDTO;
import com.example.models.Food;
import com.example.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<Food> getAll() {
        return foodRepository.findAll();
    }

    public Boolean addFood(FoodDTO food) {
        Food newFood = new Food();
        setData(food, newFood);
        foodRepository.save(newFood);
        return true;
    }


    public boolean updateFood(FoodDTO food, int id) {
        Food m = foodRepository.getReferenceById(id);
        setData(food, m);
        foodRepository.save(m);
        return true;
    }

    public List<String> doAutoComplete(final String input) {
        return getStrings(input);
    }

    public Food getFoodById(int id) {
        return foodRepository.getById(id);
    }

    public Food getFoodByName(String name) {
        return foodRepository.findByNameContainsIgnoreCase(name);
    }

    public boolean deleteFood(int id) {
        foodRepository.deleteById(id);
        return true;
    }

    private void setData(FoodDTO food, Food newFood) {
        newFood.setName(food.getName());
        newFood.setPrice(food.getPrice());
    }

    private List<String> getStrings(final String input) {
        return foodRepository.getNames().stream().filter(s -> s.toLowerCase().contains(input.toLowerCase())).collect(Collectors.toList());
    }

}

