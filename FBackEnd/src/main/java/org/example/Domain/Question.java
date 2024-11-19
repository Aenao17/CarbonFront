package org.example.Domain;

import jakarta.persistence.*;

/**
 * Entity representing a question.
 */
@jakarta.persistence.Entity
@Table(name = "Questions")
public class Question implements Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates ID values
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    private String text;

    /**
     * Constructor with ID and text.
     *
     * @param id the ID of the question
     * @param text the text of the question
     */
    public Question(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    /**
     * Constructor with text only.
     *
     * @param text the text of the question
     */
    public Question(String text) {
        this.text = text;
    }

    /**
     * Default constructor for JPA.
     */
    public Question() {
        // Default constructor
    }

    /**
     * Gets the ID of the question.
     *
     * @return the ID of the question
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the question.
     *
     * @param id the ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the text of the question.
     *
     * @return the text of the question
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text of the question.
     *
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
}
