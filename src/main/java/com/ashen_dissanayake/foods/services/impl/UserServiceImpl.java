package com.ashen_dissanayake.foods.services.impl;

import com.ashen_dissanayake.foods.entity.UserEntity;
import com.ashen_dissanayake.foods.io.UserRequest;
import com.ashen_dissanayake.foods.io.UserResponse;
import com.ashen_dissanayake.foods.repository.UserRepository;
import com.ashen_dissanayake.foods.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final AuthenticationFacade authenticationFacade;

   @Override
   public UserResponse registerUser(UserRequest request) {
      UserEntity convertToEntity = convertToEntity(request);
      UserEntity newUser = userRepository.save(convertToEntity);

      return convertToResponse(newUser);
   }

   @Override
   public String findByUserId() {
      String loggedInUserEmail = authenticationFacade.getAuthentication().getName();
      UserEntity loggedInUser = userRepository
              .findByEmail(loggedInUserEmail)
              .orElseThrow(() -> new UsernameNotFoundException("User not found."));

      return loggedInUser.getId();
   }

   // TODO: implement these methods in mappers
   private UserEntity convertToEntity(UserRequest request) {
      return UserEntity.builder()
              .email(request.getEmail())
              .password(passwordEncoder.encode(request.getPassword()))
              .name(request.getName())
              .build();
   }

   private UserResponse convertToResponse(UserEntity userEntity) {
      return UserResponse.builder()
              .id(userEntity.getId())
              .name(userEntity.getName())
              .build();
   }


}
