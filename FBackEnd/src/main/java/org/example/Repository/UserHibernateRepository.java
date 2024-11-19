package org.example.Repository;

import org.example.Domain.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserHibernateRepository implements UserRepository {

    // Method to delete a User by its ID (currently not implemented)
    public void delete(Integer id) {}

    // Method to find a User by its ID
    public static User find(Integer id) {
        // Open a session, execute the query, and return the result
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from User where id=:idM ", User.class)
                    .setParameter("idM", id)  // Set the parameter for the ID
                    .getSingleResultOrNull();  // Return the found User or null if not found
        }
    }

    // Method to add a User to the database
    @Override
    public void add(User elem) {
        // Start a transaction and persist the User to the database
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(elem));
    }

    // Method to get all Users from the database
    @Override
    public List<User> getAll() {
        // Open a session, execute the query, and return the list of Users
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from User ", User.class).getResultList();
        }
    }

    // Method to get a User by username and password (authentication)
    @Override
    public User getByCredentials(String username, String password) {
        // Open a session, execute the query to find User by username and password
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from User a where a.username like: usern and a.password like: passw ", User.class)
                    .setParameter("usern", username)  // Set the parameter for username
                    .setParameter("passw", password)  // Set the parameter for password
                    .getSingleResultOrNull();  // Return the found User or null if not found
        }
    }
}
