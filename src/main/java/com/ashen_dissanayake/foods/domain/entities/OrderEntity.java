package com.ashen_dissanayake.foods.domain.entities;

import com.ashen_dissanayake.foods.domain.dtos.OrderItem;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
@Data
@Builder
public class OrderEntity {

   @Id
   private String id;

   private String userId;

   private String userAddress;

   private String phoneNumber;

   private String email;

   private List<OrderItem> orderedItems;

   private Long amount;

   private String stripeSessionId;

   private String stripeSessionURL;

   private String stripePaymentId;

   private String paymentStatus;

//   private String stripeSignature;

   private String orderStatus;
}
