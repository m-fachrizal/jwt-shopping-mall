package com.fachrizal.shoppingmall.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
}
