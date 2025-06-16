package com.ashen_dissanayake.foods.services.impl;

import com.ashen_dissanayake.foods.entity.CartEntity;
import com.ashen_dissanayake.foods.io.CartRequest;
import com.ashen_dissanayake.foods.io.CartResponse;
import com.ashen_dissanayake.foods.repository.CartRepository;
import com.ashen_dissanayake.foods.services.CartService;
import com.ashen_dissanayake.foods.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
   private final UserService userService;
   private final CartRepository cartRepository;

   @Override
   public CartResponse addToCart(CartRequest request) {
      String loggedInUserId = userService.findByUserId();
      Optional<CartEntity> cart = cartRepository.findByUserId(loggedInUserId);
      CartEntity cartEntity = cart.orElseGet(() -> new CartEntity(loggedInUserId, new HashMap<>()));
      Map<String, Integer> cartItems = cartEntity.getItems();

      cartItems.put(request.getFoodId(), cartItems.getOrDefault(request.getFoodId(), 0) + 1);
      cartEntity.setItems(cartItems);
      cartEntity = cartRepository.save(cartEntity);

      return convertToResponse(cartEntity);
   }

   private CartResponse convertToResponse(CartEntity cartEntity) {
      return CartResponse.builder()
              .id(cartEntity.getId())
              .userId(cartEntity.getUserId())
              .items(cartEntity.getItems())
              .build();
   }
}
