package com.microservices.auth_service.Model.Dao.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservices.auth_service.Model.Pojo.OurUsers;
import com.microservices.auth_service.Model.Repositories.UserRepo;
import com.microservices.auth_service.Model.dto.AuthRequest;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    jwtService service;

    public String saveUser(AuthRequest authRequest) {
        OurUsers users = new OurUsers();
        users.setName(authRequest.getName());  
        users.setEmail(authRequest.getEmail());
        users.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        users.setUsertype(authRequest.getUsertype());  
        userRepo.save(users);
        return "User saved";
    }

    public String generateToken(String email) {
        OurUsers user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return service.generateToken(email, user.getUsertype()); 
    }

    public void validate(String token) {
        service.validatetoken(token);
    }

    public String getRoleFromToken(String token) {
        return service.extractRoleFromToken(token);
    }
}
