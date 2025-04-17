package com.microservices.auth_service.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.microservices.auth_service.Model.Dao.Services.AuthService;
import com.microservices.auth_service.Model.Pojo.OurUsers;
import com.microservices.auth_service.Model.Repositories.UserRepo;
import com.microservices.auth_service.Model.dto.AuthRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> saveUser(@RequestBody AuthRequest authRequest) {
        Optional<OurUsers> existingUser = userRepo.findByEmail(authRequest.getEmail());
        Map<String, String> response = new HashMap<>();

        if (existingUser.isPresent()) {
            response.put("message", "User already exists.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        OurUsers newUser = new OurUsers();
        newUser.setName(authRequest.getName());
        newUser.setEmail(authRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        newUser.setUsertype(authRequest.getUsertype());
        newUser.setSecurityQuestion1(authRequest.getSecurityQuestion1());
        newUser.setSecurityQuestion2(authRequest.getSecurityQuestion2());
        newUser.setAnswer1(passwordEncoder.encode(authRequest.getAnswer1()));
        newUser.setAnswer2(passwordEncoder.encode(authRequest.getAnswer2()));

        userRepo.save(newUser);
        response.put("message", "User registered successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/users")
    public List<OurUsers> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> getToken(@RequestBody AuthRequest request) {
        Map<String, String> response = new HashMap<>();

        try {
            Optional<OurUsers> user = userRepo.findByEmail(request.getEmail());
            if (user.isEmpty()) {
                response.put("Error", "User not found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            if (!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
                response.put("Error", "Invalid Credentials");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            if (authentication.isAuthenticated()) {
                response.put("usertype", user.get().getUsertype());
                response.put("token", authService.generateToken(request.getEmail()));
                return ResponseEntity.ok(response);
            } else {
                response.put("Error", "Invalid Credentials");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception ex) {
            response.put("Error", "Authentication failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

@PostMapping("/forgot-password")
public ResponseEntity<Map<String, String>> forgotPassword(@RequestBody Map<String, String> request) {
    Map<String, String> response = new HashMap<>();
    try {
        String email = request.get("email");
        Optional<OurUsers> user = userRepo.findByEmail(email);
        if (user.isEmpty()) {
            response.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        OurUsers existingUser = user.get();
        response.put("securityQuestion1", existingUser.getSecurityQuestion1());
        response.put("securityQuestion2", existingUser.getSecurityQuestion2());

        return ResponseEntity.ok(response);
    } catch (Exception e) {
        e.printStackTrace(); 
        response.put("error", "Server error: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String answer1 = request.get("answer1");
        String answer2 = request.get("answer2");
        String newPassword = request.get("password");

        Map<String, String> response = new HashMap<>();

        Optional<OurUsers> user = userRepo.findByEmail(email);
        if (user.isEmpty()) {
            response.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        OurUsers existingUser = user.get();
        if (!passwordEncoder.matches(answer1, existingUser.getAnswer1()) ||
            !passwordEncoder.matches(answer2, existingUser.getAnswer2())) {
            response.put("error", "Security answers are incorrect");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        existingUser.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(existingUser);

        response.put("message", "Password reset successful");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/change-password")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");

        Map<String, String> response = new HashMap<>();

        boolean isChanged = authService.changePassword(email, oldPassword, newPassword);
        if (isChanged) {
            response.put("message", "Password change successful");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Invalid old password or user not found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validate(token);
        return "Token Valid";
    }
}
