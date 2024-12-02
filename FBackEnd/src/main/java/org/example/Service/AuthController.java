package org.example.Service;

import org.example.Domain.User;
import org.example.Repository.UserRepository;
import org.example.Security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User user = userRepository.getByCredentials(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
        }
        return ResponseEntity.status(401).body("{\"status\":\"Invalid username or password\"}");
    }

    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User registerRequest) {
        if (userRepository.getByUsername(registerRequest.getUsername()) != null) {
            return ResponseEntity.status(409).body("{\"status\":\"Username already exists\"}");
        }
        userRepository.add(registerRequest);
        return ResponseEntity.ok("{\"status\":\"ok\"}");
    }

    // Validate JWT token
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        String username = jwtUtil.validateToken(token);
        if (username != null) {
            return ResponseEntity.ok("{\"status\":\"Token is valid for user: " + username + "\"}");
        }
        return ResponseEntity.status(401).body("{\"status\":\"Invalid token\"}");
    }
}
