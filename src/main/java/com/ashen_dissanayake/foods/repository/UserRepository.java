package com.ashen_dissanayake.foods.repository;

import com.ashen_dissanayake.foods.domain.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

   Optional<UserEntity> findByEmail(String email);
}
