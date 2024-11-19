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
        this.jwtUtil = jwtUtil; // Spring injectează automat bean-ul JwtUtil
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User user = userRepository.getByCredentials(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok().body(token);
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        String username = jwtUtil.validateToken(token);
        if (username != null) {
            return ResponseEntity.ok().body("Token is valid for user: " + username);
        }
        return ResponseEntity.status(401).body("Invalid token");
    }
}
