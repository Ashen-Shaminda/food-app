package com.ashen_dissanayake.foods.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {

   private String email;

   private String token;

}
