package com.microservices.auth_service.Controllers;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
 
import com.microservices.auth_service.Model.Dao.Services.AuthService;
import com.microservices.auth_service.Model.Pojo.OurUsers;
import com.microservices.auth_service.Model.Repositories.UserRepo;
import com.microservices.auth_service.Model.dto.AuthRequest;
 
//@CrossOrigin(origins = "*") 
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
	    newUser.setPassword(passwordEncoder.encode(authRequest.getPassword())); // Encrypt password
	    newUser.setUsertype(authRequest.getUsertype());
 
	    userRepo.save(newUser);
	    response.put("message", "User registered successfully.");
	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
 
 
	@GetMapping("/users")
	//@PreAuthorize("hasAnyAuthority('user', 'admin')")
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
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				response.put("Error", "Invalid Credentials");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		} catch (Exception ex) {
			response.put("Error", "Authentication failed");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
 
	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token) {
		authService.validate(token);
		return "Token Valid";
	}
}