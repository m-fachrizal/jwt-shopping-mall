package com.fachrizal.shoppingmall.config;

import com.fachrizal.shoppingmall.model.Admin;
import com.fachrizal.shoppingmall.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminConfig {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Bean
    CommandLineRunner commandLineRunner(AdminRepository adminRepository) {
        return args -> {
            Admin admin = new Admin(
                    "admin",
                    bcryptEncoder.encode("password")
            );

            adminRepository.save(admin);
        };
    }
}
