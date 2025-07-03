package com.ashen_dissanayake.foods.services.impl;

import com.ashen_dissanayake.foods.services.AuthenticationFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {
   @Override
   public Authentication getAuthentication() {
      return SecurityContextHolder.getContext().getAuthentication();
   }
}
