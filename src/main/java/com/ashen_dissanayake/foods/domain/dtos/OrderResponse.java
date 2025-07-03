package com.ashen_dissanayake.foods.domain.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {

   private String id;

   private String userId;

   private String userAddress;

   private String phoneNumber;

   private String email;

   private double amount;

   private String sessionId;

   private String sessionURL;

   private String paymentStatus;

   private String orderStatus;

}
