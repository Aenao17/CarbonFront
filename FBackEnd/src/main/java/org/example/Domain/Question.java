package org.example.Domain;

import jakarta.persistence.*;

@jakarta.persistence.Entity
@Table(name = "Questions")
public class Question implements Entity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "type")
    private String type; // Ex: single-choice, multiple-choice, open-ended

    public Question() {}

    public Question(Integer id, String text, String type) {
        this.id = id;
        this.text = text;
        this.type = type;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
