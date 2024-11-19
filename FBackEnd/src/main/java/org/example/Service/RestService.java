package org.example.Service;

import org.example.Domain.User;
import org.example.Repository.QuestionRepository;
import org.example.Repository.SurveyRepository;
import org.example.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class RestService {

    // Repositories for handling User, Question, and Survey data
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

    // Endpoint to retrieve all users
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() throws Exception {
        System.out.println("GET ALL USERS FROM REST SERVICE");
        return userRepository.getAll();
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
}
