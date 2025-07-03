package com.ashen_dissanayake.foods.controllers;

import com.ashen_dissanayake.foods.domain.dtos.OrderRequest;
import com.ashen_dissanayake.foods.domain.dtos.OrderResponse;
import com.ashen_dissanayake.foods.services.StripeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/orders")
@AllArgsConstructor
public class OrderController {

   private final StripeService stripeService;

   @PostMapping(path = "/create")
   public ResponseEntity<OrderResponse> createOrderWithPayment(@RequestBody OrderRequest request) {
      OrderResponse response = stripeService.createOrderWithPayment(request);

      return new ResponseEntity<>(response, HttpStatus.OK);
   }
}
