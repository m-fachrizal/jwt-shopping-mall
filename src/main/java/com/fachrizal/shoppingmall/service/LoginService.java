package com.fachrizal.shoppingmall.service;

import com.fachrizal.shoppingmall.dto.request.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<?> createAuthenticationToken(LoginRequest authenticationRequest);
}
