package com.ashen_dissanayake.foods.repository;

import com.ashen_dissanayake.foods.entity.FoodEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends MongoRepository<FoodEntity, String> {

}
