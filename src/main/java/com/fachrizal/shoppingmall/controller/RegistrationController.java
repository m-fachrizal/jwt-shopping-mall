package com.fachrizal.shoppingmall.controller;

import com.fachrizal.shoppingmall.model.Employee;
import com.fachrizal.shoppingmall.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RegistrationController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> registerNewEmployee(@RequestBody Employee employee) throws Exception {
        if(validatingExistingEmployee(
                employee.getMailId(), employee.getMobileNumber(), employee.getNationalId()
        )) {
            throw new IllegalStateException("employee already exist");
        } else {
            return ResponseEntity.ok(employeeRepository.save(employee));
        }
    }

    private boolean validatingExistingEmployee(String mailId, String mobileNumber, String nationalId) throws Exception {
        Optional<Employee> mailIdOptional = employeeRepository.findEmployeesByMailId(mailId);
        Optional<Employee> mobileNumberOptional = employeeRepository.findEmployeesByMobileNumber(mobileNumber);
        Optional<Employee> nationalIdOptional = employeeRepository.findEmployeesByNationalId(nationalId);
        if(mailIdOptional.isPresent() &&
                mobileNumberOptional.isPresent() &&
                nationalIdOptional.isPresent()
        ) {
            return true;
        } else {
            return false;
        }
    }

}
