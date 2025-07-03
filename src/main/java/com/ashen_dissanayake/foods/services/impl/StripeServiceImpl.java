package com.ashen_dissanayake.foods.services.impl;

import com.ashen_dissanayake.foods.config.StripeConfig;
import com.ashen_dissanayake.foods.domain.dtos.OrderRequest;
import com.ashen_dissanayake.foods.domain.dtos.OrderResponse;
import com.ashen_dissanayake.foods.domain.entities.OrderEntity;
import com.ashen_dissanayake.foods.repository.OrderRepository;
import com.ashen_dissanayake.foods.services.StripeService;
import com.ashen_dissanayake.foods.services.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StripeServiceImpl implements StripeService {

   private final OrderRepository orderRepository;
   private final UserService userService;
   private final StripeConfig stripeConfig;


   public OrderResponse createOrderWithPayment(OrderRequest request) {
      OrderEntity newOrder = convertToEntity(request);
      newOrder = orderRepository.save(newOrder);

      Stripe.apiKey = stripeConfig.getSecretKey();

      // Create a PaymentIntent with the order amount and currency
      SessionCreateParams.LineItem.PriceData.ProductData productData =
              SessionCreateParams.LineItem.PriceData.ProductData.builder()
                      .setName(newOrder.getEmail())
                      .build();

      // Create new line item with the above product data and associated price
      SessionCreateParams.LineItem.PriceData priceData =
              SessionCreateParams.LineItem.PriceData.builder()
                      .setCurrency("USD")
                      .setUnitAmount(newOrder.getAmount() * 100)
                      .setProductData(productData)
                      .build();

      // Create new line item with the above price data
      SessionCreateParams.LineItem lineItem =
              SessionCreateParams
                      .LineItem.builder()
                      .setPriceData(priceData)
                      .setQuantity(1L)
                      .build();

      // Create new session with the line items
      SessionCreateParams params =
              SessionCreateParams.builder()
                      .setMode(SessionCreateParams.Mode.PAYMENT)
                      .setSuccessUrl("http://localhost:8080/success")
                      .setCancelUrl("http://localhost:8080/cancel")
                      .addLineItem(lineItem)
                      .build();

      // Create new session
      Session session = null;
      try {
         session = Session.create(params);
         newOrder.setSessionId(session.getId());
         newOrder.setSessionURL(session.getUrl());
         String loggedInUserId = userService.findByUserId();
         newOrder.setUserId(loggedInUserId);
         newOrder = orderRepository.save(newOrder);

      } catch (StripeException e) {
         e.printStackTrace(); // Log the error to debug
         throw new RuntimeException("Stripe session creation failed: " + e.getMessage());
      }

      return convertToResponse(newOrder);
   }

   private OrderResponse convertToResponse(OrderEntity orderEntity) {
      return OrderResponse.builder()
              .id(orderEntity.getId())
              .amount(orderEntity.getAmount())
              .userId(orderEntity.getUserId())
              .userAddress(orderEntity.getUserAddress())
              .sessionId(orderEntity.getSessionId())
              .sessionURL(orderEntity.getSessionURL())
              .paymentStatus(orderEntity.getPaymentStatus())
              .orderStatus(orderEntity.getOrderStatus())
              .email(orderEntity.getEmail())
              .phoneNumber(orderEntity.getPhoneNumber())
              .build();
   }

   private OrderEntity convertToEntity(OrderRequest request) {
      return OrderEntity.builder()
              .userAddress(request.getUserAddress())
              .amount(request.getAmount())
              .orderedItems(request.getOrderItems())
              .email(request.getEmail())
              .phoneNumber(request.getPhoneNumber())
              .orderStatus(request.getOrderStatus())
              .build();
   }
}
