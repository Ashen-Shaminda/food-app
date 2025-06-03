package com.ashen_dissanayake.foods.services;

import com.ashen_dissanayake.foods.io.FoodRequest;
import com.ashen_dissanayake.foods.io.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FoodService {
   String uploadFile(MultipartFile file);

   FoodResponse addFood(FoodRequest request, MultipartFile file);
}
