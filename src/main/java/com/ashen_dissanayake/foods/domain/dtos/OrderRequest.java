package com.ashen_dissanayake.foods.domain.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequest {

   private List<OrderItem> orderItems;

   private String userAddress;

   private Long amount;

   private String email;

   private String phoneNumber;

   private String OrderStatus;
}
