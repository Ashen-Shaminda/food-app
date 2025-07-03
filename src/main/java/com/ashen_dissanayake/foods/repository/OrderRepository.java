package com.ashen_dissanayake.foods.repository;

import com.ashen_dissanayake.foods.domain.entities.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {

   List<OrderEntity> findByUserId(String userID);

   Optional<OrderEntity> findBySessionId(String stripeSessionId);
}
