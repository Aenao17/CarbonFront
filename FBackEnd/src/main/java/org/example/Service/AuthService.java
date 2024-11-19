package org.example.Service;

import org.example.Domain.User;
import org.example.Repository.UserRepository;
import org.example.Security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(String username, String password) {
        User user = userRepository.getByCredentials(username, password); // Asigură-te că verifici parolele corect (hash).
        if (user != null) {
            return jwtUtil.generateToken(user.getUsername());
        }
        return null;
    }

    public String validateToken(String token) {
        return jwtUtil.validateToken(token); // Returnează username-ul dacă token-ul este valid.
    }
}
