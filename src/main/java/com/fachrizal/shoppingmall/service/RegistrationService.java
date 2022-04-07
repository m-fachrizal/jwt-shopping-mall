package com.fachrizal.shoppingmall.service;

import com.fachrizal.shoppingmall.model.Employee;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<?> registerNewEmployee(Employee employee);
}
