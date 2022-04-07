package com.fachrizal.shoppingmall.controller;

import com.fachrizal.shoppingmall.model.Employee;
import com.fachrizal.shoppingmall.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Slf4j
@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> registerNewEmployee(@Valid @RequestBody Employee employee){
        log.info("Post Request for /registration");
        return registrationService.registerNewEmployee(employee);
    }

}
