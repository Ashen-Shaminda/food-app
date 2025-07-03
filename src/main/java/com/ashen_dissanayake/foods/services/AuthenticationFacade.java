package com.ashen_dissanayake.foods.services;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

   Authentication getAuthentication();
}
