package com.ashen_dissanayake.foods.services;

import com.ashen_dissanayake.foods.domain.dtos.UserRequest;
import com.ashen_dissanayake.foods.domain.dtos.UserResponse;

public interface UserService {

   UserResponse registerUser(UserRequest userRequest);

   String findByUserId();
}
