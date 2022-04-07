package com.fachrizal.shoppingmall.service.impl;

import com.fachrizal.shoppingmall.dto.response.DataResponse;
import com.fachrizal.shoppingmall.model.Employee;
import com.fachrizal.shoppingmall.repository.EmployeeRepository;
import com.fachrizal.shoppingmall.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<?> registerNewEmployee(Employee employee) {
        if(validatingExistingEmployee(
                employee.getMailId(), employee.getMobileNumber(), employee.getNationalId()
        )) {
            log.info("employee already exist");
            throw new IllegalStateException("employee already exist");
        } else {
            Employee newEmployee = employeeRepository.save(employee);
            log.info("successfully add new employee");
            return ResponseEntity.ok(DataResponse.builder().data(newEmployee).build());
        }
    }

    private boolean validatingExistingEmployee(String mailId, String mobileNumber, String nationalId) {
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
