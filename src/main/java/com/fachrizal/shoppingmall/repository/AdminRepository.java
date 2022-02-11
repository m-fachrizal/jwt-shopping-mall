package com.fachrizal.shoppingmall.repository;

import com.fachrizal.shoppingmall.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUserId(String userId);
}
