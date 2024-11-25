package org.example.Service;

import org.example.Domain.Question;
import org.example.Domain.Survey;
import org.example.Domain.User;
import org.example.Repository.QuestionRepository;
import org.example.Repository.SurveyRepository;
import org.example.Repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements IService {

    // Repositories for handling User, Question, and Survey data
    UserRepository userRepository;
    QuestionRepository questionRepository;
    SurveyRepository surveyRepository;

    // Map to hold logged-in clients
    private Map<String, IObserver> loggedClients = new HashMap<>();

    // ExecutorService for handling concurrent operations
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    // Constructor to inject repositories
    public Service(UserRepository userRepository, QuestionRepository questionRepository,
                   SurveyRepository surveyRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
    }

    // Method to log in a user
    public User login(String username, String password, IObserver iobs) {
        User user = userRepository.getByCredentials(username, password);
        if (user != null) {
            // Add the client observer if login is successful
            loggedClients.put(user.getUsername(), iobs);
            return user;
        }
        return null;
    }

    // Method to get all users
    @Override
    public List<User> getAllUsers() throws Exception {
        return userRepository.getAll();
    }

    // Method to log out a user (not implemented yet)
    @Override
    public void logout(User user, IObserver client) throws Exception {
        // Can implement logout logic here if needed
    }

    // Method to add a new user and notify all logged-in clients
    @Override
    public void addUser(User user) {
        userRepository.add(user);
        // Notify all logged-in clients about the new user
        executorService.submit(() -> {
            for (IObserver client : loggedClients.values()) {
                client.update();
            }
        });
    }

    // Method to add a new survey and notify all logged-in clients
    @Override
    public void addSurvey(Survey survey) {
        surveyRepository.add(survey);
        // Notify all logged-in clients about the new user
        executorService.submit(() -> {
            for (IObserver client : loggedClients.values()) {
                client.update();
            }
        });
    }

    // Method to add a new question and notify all logged-in clients
    @Override
    public void addQuestion(Question question) {
        questionRepository.add(question);
        // Notify all logged-in clients about the new question
        executorService.submit(() -> {
            for (IObserver client : loggedClients.values()) {
                client.update();
            }
        });
    }
}
