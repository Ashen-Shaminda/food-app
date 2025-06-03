package com.ashen_dissanayake.foods.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//TODO : Dtos
public class FoodResponse {
   private String id;

   private String name;

   private String description;

   private String imageUrl;

   private double price;

   private String category;
}
