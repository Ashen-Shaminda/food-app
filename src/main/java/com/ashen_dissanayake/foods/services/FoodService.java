package com.ashen_dissanayake.foods.services;

import com.ashen_dissanayake.foods.domain.dtos.FoodRequest;
import com.ashen_dissanayake.foods.domain.dtos.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodService {
   String uploadFoodImage(MultipartFile file);

   List<FoodResponse> getAllFoods();

   FoodResponse addFood(FoodRequest request, MultipartFile file);

   FoodResponse getFoodById(String id);

   boolean deleteFoodImage(String fileName);

   void deleteFoodById(String id);
}
