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

    @Autowired
    private AdminRepository adminRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Admin admin = Admin.builder()
                    .userId("admin")
                    .password(bcryptEncoder.encode("password1"))
                    .build();

            adminRepository.save(admin);
        };
    }
}
