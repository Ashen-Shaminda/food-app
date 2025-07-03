package com.ashen_dissanayake.foods.services.impl;

import com.ashen_dissanayake.foods.domain.entities.CartEntity;
import com.ashen_dissanayake.foods.domain.dtos.CartRequest;
import com.ashen_dissanayake.foods.domain.dtos.CartResponse;
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
      Optional<CartEntity> cartItem = cartRepository.findByUserId(loggedInUserId);
      CartEntity cartEntity = cartItem.orElseGet(() -> new CartEntity(loggedInUserId, new HashMap<>()));
      Map<String, Integer> cartItems = cartEntity.getItems();

      cartItems.put(request.getFoodId(), cartItems.getOrDefault(request.getFoodId(), 0) + 1);
      cartEntity.setItems(cartItems);
      cartEntity = cartRepository.save(cartEntity);

      return convertToResponse(cartEntity);
   }

   @Override
   public CartResponse getCart() {
      String loggedInUserId = userService.findByUserId();
      CartEntity cartEntity = cartRepository.findByUserId(loggedInUserId).orElse(new CartEntity(null, loggedInUserId, new HashMap<>()));

      return convertToResponse(cartEntity);
   }

   @Override
   public CartResponse removeFromCart(CartRequest request) {
      String loggedInUserId = userService.findByUserId();
      CartEntity cartEntity = cartRepository.findByUserId(loggedInUserId).orElseThrow(() -> new RuntimeException("Cart is not found."));

      Map<String, Integer> cartItems = cartEntity.getItems();

      if (cartItems.containsKey(request.getFoodId())) {
         int currentQuantity = cartItems.get(request.getFoodId());

         if (currentQuantity > 0)
            cartItems.put(request.getFoodId(), currentQuantity - 1);
         else
            cartItems.remove(request.getFoodId());

         cartEntity = cartRepository.save(cartEntity);
      }

      return convertToResponse(cartEntity);
   }

   @Override
   public void clearCart() {
      String loggedInUserId = userService.findByUserId();
      cartRepository.deleteByUserId(loggedInUserId);
   }

   private CartResponse convertToResponse(CartEntity cartEntity) {
      return CartResponse.builder()
              .id(cartEntity.getId())
              .userId(cartEntity.getUserId())
              .items(cartEntity.getItems())
              .build();
   }
}
