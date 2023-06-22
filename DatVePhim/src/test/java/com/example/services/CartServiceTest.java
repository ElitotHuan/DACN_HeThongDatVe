package com.example.services;

import com.example.models.Cart;
import com.example.models.CartItem;
import com.example.models.Food;
import com.example.models.User;
import com.example.repositories.CartRepository;
import com.example.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    void testAddToCart() {
        User user = new User();
        user.setUsername("testuser");

        when(userRepository.findByUsername("testuser")).thenReturn(user);

        Food food = new Food();
        food.setId(1);
        food.setName("Test Food");

        cartService.addToCart(food, "testuser");

        verify(cartRepository).save(any(Cart.class));
    }

    @Test
    void testRemoveFromCart() {
        User user = new User();
        user.setUsername("testuser");

        Cart cart = new Cart();
        cart.setUser(user);

        Food food = new Food();
        food.setId(1);
        food.setName("Test Food");

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setFood(food);
        cartItem.setCount(1);

        cart.getCartItems().add(cartItem);

        when(cartRepository.getCartByUsername("testuser")).thenReturn(cart);

        cartService.removeFromCart(food, "testuser");

        verify(cartRepository).save(any(Cart.class));
    }

    @Test
    void testCountItemsByCartId() {
        when(cartRepository.sumCountByCartId(1)).thenReturn(3);

        int result = cartService.countItemsByCartId(1);
        assertEquals(3, result);
    }

    @Test
    void testGetCartByUsername() {
        User user = new User();
        user.setUsername("testuser");

        Cart cart = new Cart();
        cart.setUser(user);

        when(cartRepository.getCartByUsername("testuser")).thenReturn(cart);

        Cart result = cartService.getCartByUsername("testuser");
        assertEquals(cart, result);
    }

    @Test
    void testGetCartById() {
        User user = new User();
        user.setUsername("testuser");

        Cart cart = new Cart();
        cart.setId(1);
        cart.setUser(user);

        when(cartRepository.findById(1)).thenReturn(Optional.of(cart));

        Cart result = cartService.getCartById(1);
        assertEquals(cart, result);
    }
}