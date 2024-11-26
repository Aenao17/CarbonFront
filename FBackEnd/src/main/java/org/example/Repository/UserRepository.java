package org.example.Repository;

import org.example.Domain.User;

public interface UserRepository extends Repository<Integer, User> {

    // Method to get a User by username and password (for authentication)
    User getByCredentials(String username, String password);
    User getByUsername(String username);
}
