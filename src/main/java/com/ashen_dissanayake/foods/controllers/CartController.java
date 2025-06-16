package com.ashen_dissanayake.foods.controllers;

import com.ashen_dissanayake.foods.io.CartRequest;
import com.ashen_dissanayake.foods.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/cart")
@AllArgsConstructor
public class CartController {

   private final CartService cartService;

   @PostMapping
   public ResponseEntity addToCart(@RequestBody CartRequest request) {
      String foodId = request.getFoodId();

      if (foodId == null && foodId.isEmpty()) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Food Id is not found.");
      }

      return new ResponseEntity<>(cartService.addToCart(request), HttpStatus.OK);
   }
}
