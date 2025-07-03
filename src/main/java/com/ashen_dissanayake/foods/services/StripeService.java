package com.ashen_dissanayake.foods.services;

import com.ashen_dissanayake.foods.domain.dtos.OrderRequest;
import com.ashen_dissanayake.foods.domain.dtos.OrderResponse;

public interface StripeService {

   OrderResponse createOrderWithPayment(OrderRequest request);
}
