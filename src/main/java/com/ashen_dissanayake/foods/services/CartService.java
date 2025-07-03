package com.ashen_dissanayake.foods.services;

import com.ashen_dissanayake.foods.domain.dtos.CartRequest;
import com.ashen_dissanayake.foods.domain.dtos.CartResponse;

public interface CartService {

   CartResponse addToCart(CartRequest request);

   CartResponse getCart();

   CartResponse removeFromCart(CartRequest request);

   void clearCart();
}
