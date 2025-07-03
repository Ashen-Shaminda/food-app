package com.ashen_dissanayake.foods.controllers;

import com.ashen_dissanayake.foods.domain.dtos.CartRequest;
import com.ashen_dissanayake.foods.domain.dtos.CartResponse;
import com.ashen_dissanayake.foods.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/cart")
@AllArgsConstructor
public class CartController {

   private final CartService cartService;

   @PostMapping
   public ResponseEntity<CartResponse> addToCart(@RequestBody CartRequest request) {
      String foodId = request.getFoodId();

      if (foodId == null && foodId.isEmpty()) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Food Id is not found.");
      }

      return new ResponseEntity<>(cartService.addToCart(request), HttpStatus.OK);
   }

   @GetMapping
   public ResponseEntity<CartResponse> getCart() {

      return new ResponseEntity<>(cartService.getCart(), HttpStatus.OK);
   }

   @PostMapping(path = "/remove")
   public ResponseEntity<CartResponse> removeFromCart(@RequestBody CartRequest request) {
      String foodId = request.getFoodId();
      if (foodId == null && foodId.isEmpty()) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Food Id is not found.");
      }

      return new ResponseEntity<>(cartService.removeFromCart(request), HttpStatus.OK);
   }


   @DeleteMapping
   public ResponseEntity<Void> clearCart() {
      cartService.clearCart();

      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}
