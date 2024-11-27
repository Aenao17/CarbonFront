package org.example.Repository;

import org.example.Domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void add(User user) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                if (session.contains(user)) {
                    session.persist(user);
                } else {
                    session.merge(user);
                }
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
            finally {
                session.close();
            }
        }
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

    @Override
    public User getByUsername(String username) {
        // Open a session, execute the query to find User by username
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from User a where a.username like: usern ", User.class)
                    .setParameter("usern", username)  // Set the parameter for username
                    .getSingleResultOrNull();  // Return the found User or null if not found
        }
    }
}