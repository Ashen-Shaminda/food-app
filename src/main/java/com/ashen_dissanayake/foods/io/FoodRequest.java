package com.ashen_dissanayake.foods.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

//TODO : rename this dtos and put inside dtos
public class FoodRequest {
   private String name;

   private String description;

   private double price;

   private String Category;
}
