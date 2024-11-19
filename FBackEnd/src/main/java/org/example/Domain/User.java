package org.example.Domain;

import jakarta.persistence.*;

/**
 * Entity representing a user in the system.
 */
@jakarta.persistence.Entity
@Table(name = "Users")
public class User implements Entity<Integer> {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    /**
     * Default constructor for JPA.
     * Initializes default values for the fields.
     */
    public User() {
        this.id = 0;
        this.username = "default";
        this.password = "default";
    }

    /**
     * Constructor with all fields.
     *
     * @param id       the user's ID
     * @param username the user's username
     * @param password the user's password
     */
    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the user's ID.
     *
     * @return the user ID
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id the ID to set
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the user's username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
