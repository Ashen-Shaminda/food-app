package com.ashen_dissanayake.foods.services;

import com.ashen_dissanayake.foods.io.UserRequest;
import com.ashen_dissanayake.foods.io.UserResponse;

public interface UserService {

   UserResponse registerUser(UserRequest userRequest);
}
