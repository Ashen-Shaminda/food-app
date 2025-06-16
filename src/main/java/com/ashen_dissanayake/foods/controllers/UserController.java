package com.ashen_dissanayake.foods.controllers;

import com.ashen_dissanayake.foods.io.UserRequest;
import com.ashen_dissanayake.foods.io.UserResponse;
import com.ashen_dissanayake.foods.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class UserController {

   private final UserService userService;

   @PostMapping(path = "/register")
   public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {

      return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
   }
}
