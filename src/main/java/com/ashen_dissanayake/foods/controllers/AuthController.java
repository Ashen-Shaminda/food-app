package com.ashen_dissanayake.foods.controllers;

import com.ashen_dissanayake.foods.io.AuthenticationRequest;
import com.ashen_dissanayake.foods.io.AuthenticationResponse;
import com.ashen_dissanayake.foods.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class AuthController {

   private final AuthenticationManager authenticationManager;
   private final UserDetailsService userDetailsService;
   private final JwtUtil jwtUtil;

   // TODO : Implement save token as a cookie.
   @PostMapping(path = "/login")
   public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
      final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
      final String jwtToken = jwtUtil.generateToken(userDetails);

      return new AuthenticationResponse(request.getEmail(), jwtToken);
   }
}
