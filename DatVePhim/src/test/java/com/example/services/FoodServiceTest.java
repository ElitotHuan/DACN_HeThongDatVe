package com.example.services;
import com.example.models.Food;
import com.example.repositories.FoodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.dto.FoodDTO;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {

    @Mock
    private FoodRepository foodRepository;

    @InjectMocks
    private FoodService foodService;

    @Test
    void testGetAll() {
        List<Food> foods = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Food food = new Food();
            food.setId(i + 1);
            food.setName("Food" + (i + 1));
            food.setPrice((int) (10.0 * (i + 1)));
            foods.add(food);
        }

        when(foodRepository.findAll()).thenReturn(foods);

        List<Food> result = foodService.getAll();
        assertEquals(foods.size(), result.size());
    }

    @Test
    void testAddFood() {
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName("New Food");
        foodDTO.setPrice((int) 10.0);

        boolean result = foodService.addFood(foodDTO);
        assertTrue(result);

        verify(foodRepository).save(any(Food.class));
    }

    @Test
    void testUpdateFood() {
        Food food = new Food();
        food.setId(1);
        food.setName("Old Food");
        food.setPrice((int) 10.0);

        when(foodRepository.getReferenceById(1)).thenReturn(food);

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName("Updated Food");
        foodDTO.setPrice((int) 20.0);

        boolean result = foodService.updateFood(foodDTO, 1);
        assertTrue(result);

        verify(foodRepository).save(any(Food.class));
    }

    @Test
    void testDoAutoComplete() {
        List<String> names = new ArrayList<>();
        names.add("Apple");
        names.add("Banana");
        names.add("Cherry");

        when(foodRepository.getNames()).thenReturn(names);

        List<String> result = foodService.doAutoComplete("a");
        assertEquals(2, result.size());
    }

    @Test
    void testGetFoodById() {
        Food food = new Food();
        food.setId(1);
        food.setName("Test Food");
        food.setPrice((int) 10.0);

        when(foodRepository.getById(1)).thenReturn(food);

        Food result = foodService.getFoodById(1);
        assertEquals(food, result);
    }

    @Test
    void testGetFoodByName() {
        Food food = new Food();
        food.setId(1);
        food.setName("Test Food");
        food.setPrice((int) 10.0);

        when(foodRepository.findByNameContainsIgnoreCase("Test")).thenReturn(food);

        Food result = foodService.getFoodByName("Test");
        assertEquals(food, result);
    }

    @Test
    void testDeleteFood() {
        boolean result = foodService.deleteFood(1);
        assertTrue(result);

        verify(foodRepository).deleteById(1);
    }
}
