package org.example.Service;

import org.example.Domain.Survey;
import org.example.Domain.User;
import org.example.Repository.QuestionRepository;
import org.example.Repository.SurveyRepository;
import org.example.Repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.example.Security.JwtUtil;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@CrossOrigin
public class RestService {

    // Repositories for handling User, Question, and Survey data
    private final JwtUtil jwtUtil = new JwtUtil();
    UserRepository userRepository;
    QuestionRepository questionRepository;
    SurveyRepository surveyRepository;

    // Map to hold logged-in clients
    private Map<String, IObserver> loggedClients = new HashMap<>();

    // ExecutorService for handling concurrent operations
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    // Constructor to inject dependencies
    public RestService(UserRepository userRepository, QuestionRepository questionRepository,
                       SurveyRepository surveyRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/secure-data")
    public ResponseEntity<?> getSecureData(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.validateToken(token);

        if (username != null) {
            return ResponseEntity.ok("Secure data accessed by user: " + username);
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }


    // Endpoint to create a new user
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        System.out.println("Creating user...");
        userRepository.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Global exception handler for any exceptions thrown in the controller
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userError(Exception e) {
        return e.getMessage();
    }

    @Configuration
    public class CorsConfig {

        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/")
                            .allowedOrigins("http://localhost:8100")
                            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                            .allowedHeaders("*")
                            .allowCredentials(true);
                }
            };
        }
    }
}