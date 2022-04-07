package com.fachrizal.shoppingmall.service.impl;

import com.fachrizal.shoppingmall.config.JwtTokenUtil;
import com.fachrizal.shoppingmall.dto.request.LoginRequest;
import com.fachrizal.shoppingmall.dto.response.DataResponse;
import com.fachrizal.shoppingmall.dto.response.LoginResponse;
import com.fachrizal.shoppingmall.service.JwtUserDetailsService;
import com.fachrizal.shoppingmall.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Override
    public ResponseEntity<?> createAuthenticationToken(LoginRequest authenticationRequest) {
        authenticate(authenticationRequest.getUserId(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUserId());

        final String token = jwtTokenUtil.generateToken(userDetails);
        log.info("token created successfully");
        return ResponseEntity.ok(
                DataResponse.builder()
                        .data(LoginResponse.builder().accessToken(token).build())
                        .build()
        );
    }

    private void authenticate(String userId, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userId, password));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "INVALID_CREDENTIALS", e);
        }
    }

}
