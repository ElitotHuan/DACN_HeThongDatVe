package com.example.controllers;

import com.example.models.Food;
import com.example.services.CartService;
import com.example.services.FoodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private FoodService foodService;
    @PostMapping("/addToCart")
    @ResponseBody
    public void addToCart(@RequestParam("foodId") int foodId, @RequestParam("username") String username, HttpSession session) {
        // Lấy thông tin food từ foodId
        Food food = foodService.getFoodById(foodId);
        if (cartService.getCartByUsername(username) != null) {
            int cartItemCount = cartService.countItemsByCartId(cartService.getCartByUsername(username).getId())+1;
            session.setAttribute("cartItemCount", cartItemCount);
        }
        cartService.addToCart(food, username);
    }
}
