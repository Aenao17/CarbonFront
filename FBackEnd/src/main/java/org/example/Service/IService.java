package org.example.Service;

import org.example.Domain.Question;
import org.example.Domain.User;

import java.util.List;

public interface IService {
    // Method for user login, accepts username, password and an observer object
    User login(String username, String password, IObserver iobs) throws Exception;

    // Method to retrieve all users
    List<User> getAllUsers() throws Exception;

    // Method for user logout, accepts user and observer object
    void logout(User user, IObserver client) throws Exception;

    // Method to add a new user to the system
    void addUser(User user);

    // Method to add a new question to the system
    void addQuestion(Question question);
}
