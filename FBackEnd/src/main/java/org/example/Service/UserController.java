package org.example.Service;

import org.example.Domain.User;
import org.example.Repository.UserHibernateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    // Injecting the UserRepository to access user data
    @Autowired
    private UserHibernateRepository userRepository;

    // Method to get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.getAll(); // Returns a list of all users
    }

    // Method to get a user by their ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return UserHibernateRepository.find(id); // Returns a user by their ID
    }

    // Additional CRUD methods can be added here
}
