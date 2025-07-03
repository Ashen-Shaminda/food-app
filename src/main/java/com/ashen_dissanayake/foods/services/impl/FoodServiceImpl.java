package com.ashen_dissanayake.foods.services.impl;

import com.ashen_dissanayake.foods.domain.entities.FoodEntity;
import com.ashen_dissanayake.foods.domain.dtos.FoodRequest;
import com.ashen_dissanayake.foods.domain.dtos.FoodResponse;
import com.ashen_dissanayake.foods.repository.FoodRepository;
import com.ashen_dissanayake.foods.services.FoodService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {
   private final FoodRepository foodRepository;
   private final S3Client s3Client;

   @Value("${aws.s3.bucketname}")
   private String bucketName;

   public FoodServiceImpl(S3Client s3Client, FoodRepository foodRepository) {
      this.s3Client = s3Client;
      this.foodRepository = foodRepository;
   }

   @Override
   public String uploadFoodImage(MultipartFile file) {
      String fileNameExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
      String key = UUID.randomUUID() + "." + fileNameExtension;

      try {
         PutObjectRequest putObjectRequest = PutObjectRequest
                 .builder()
                 .bucket(bucketName)
                 .key(key)
                 .contentType(file.getContentType())
                 .build();

         PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

         if (response.sdkHttpResponse().isSuccessful()) {
            return "https://" + bucketName + ".s3.amazonaws.com/" + key;
         } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed.");
         }

      } catch (S3Exception e) {
         throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "S3 error occurred: " + e.getMessage());

      } catch (IOException e) {
         throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while uploading the file.");
      }
   }

   @Override
   public FoodResponse addFood(FoodRequest request, MultipartFile file) {
      FoodEntity newFoodEntity = convertToEntity(request);
      String imageUrl = uploadFoodImage(file);

      newFoodEntity.setImageUrl(imageUrl);
      newFoodEntity = foodRepository.save(newFoodEntity);

      return convertToResponse(newFoodEntity);
   }

   @Override
   public List<FoodResponse> getAllFoods() {
      List<FoodEntity> foodEntities = foodRepository.findAll();

      return foodEntities.stream().map(this::convertToResponse).collect(Collectors.toList());
   }

   @Override
   public FoodResponse getFoodById(String id) {
      FoodEntity foodEntity = foodRepository.findById(id).orElseThrow(() -> new RuntimeException("Food not found for id: " + id));

      return convertToResponse(foodEntity);
   }

   @Override
   public boolean deleteFoodImage(String fileName) {
      DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
              .bucket(bucketName)
              .key(fileName)
              .build();

      s3Client.deleteObject(deleteObjectRequest);

      return true;
   }

   @Override
   public void deleteFoodById(String id) {
      FoodResponse foodResponse = getFoodById(id);
      String imageUrl = foodResponse.getImageUrl();
      String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);

      boolean isFileDeleted = deleteFoodImage(fileName);

      if (isFileDeleted) foodRepository.deleteById(foodResponse.getId());
   }

   //   TODO : file needs to be in a mappers directory (toEntity)
   private FoodEntity convertToEntity(FoodRequest request) {
      return FoodEntity.builder()
              .name(request.getName())
              .description(request.getDescription())
              .category(request.getCategory())
              .price(request.getPrice())
              .build();
   }

   //TODO : (toDto)
   private FoodResponse convertToResponse(FoodEntity entity) {
      return FoodResponse.builder()
              .id(entity.getId())
              .name(entity.getName())
              .description(entity.getDescription())
              .category(entity.getCategory())
              .price(entity.getPrice())
              .imageUrl(entity.getImageUrl())
              .build();
   }
}
