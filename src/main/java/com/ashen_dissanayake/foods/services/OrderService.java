package com.ashen_dissanayake.foods.services;

import com.ashen_dissanayake.foods.domain.dtos.OrderRequest;
import com.ashen_dissanayake.foods.domain.dtos.OrderResponse;

import java.util.Map;

public interface OrderService {

   OrderResponse createOrderWithPayment(OrderRequest request);

   void verifyPayment(Map<String, String> paymentData, String status);
}
