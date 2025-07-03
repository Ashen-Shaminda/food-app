package com.ashen_dissanayake.foods.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class StripeConfig {

   @Value("${stripe.secret.key}")
   private String secretKey;
}
