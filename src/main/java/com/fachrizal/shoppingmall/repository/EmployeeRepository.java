package com.fachrizal.shoppingmall.repository;

import com.fachrizal.shoppingmall.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findEmployeesByMailId(String mailId);
    Optional<Employee> findEmployeesByMobileNumber(String mobileNumber);
    Optional<Employee> findEmployeesByNationalId(String nationalId);
}
