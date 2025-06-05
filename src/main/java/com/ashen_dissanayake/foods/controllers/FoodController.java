package com.ashen_dissanayake.foods.controllers;

import com.ashen_dissanayake.foods.io.FoodRequest;
import com.ashen_dissanayake.foods.io.FoodResponse;
import com.ashen_dissanayake.foods.services.FoodService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@AllArgsConstructor
public class FoodController {
   private final FoodService foodService;

   @GetMapping
   public ResponseEntity<List<FoodResponse>> getAllFoods() {

      return new ResponseEntity<>(foodService.getAllFoods(), HttpStatus.OK);
   }

   @PostMapping
   public FoodResponse addFood(@RequestPart("food") String foodString, @RequestPart("file") MultipartFile file) {
      ObjectMapper objectMapper = new ObjectMapper();
      FoodRequest request;

      try {
         request = objectMapper.readValue(foodString, FoodRequest.class);
      } catch (JsonProcessingException ex) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid json format");
      }

      return foodService.addFood(request, file);
   }

   @GetMapping(path = "/{id}")
   public ResponseEntity<FoodResponse> getFoodById(@PathVariable String id) {

      return new ResponseEntity<>(foodService.getFoodById(id), HttpStatus.OK);
   }

   @DeleteMapping(path = "/{id}")
   public ResponseEntity<FoodResponse> deleteFoodById(@PathVariable String id) {
      foodService.deleteFoodById(id);

      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}
