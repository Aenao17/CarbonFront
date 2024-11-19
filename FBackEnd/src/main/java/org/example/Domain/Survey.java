package org.example.Domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

/**
 * Entity representing a survey.
 */
@jakarta.persistence.Entity
@Table(name = "Surveys")
public class Survey implements Entity<Integer> {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "questionAnswers")
    private String questionAnswersJson;

    @Transient
    private Map<Integer, List<String>> questionAnswers;

    private static final Gson gson = new Gson();

    /**
     * Constructor with all fields.
     *
     * @param id the ID of the survey
     * @param userId the ID of the associated user
     * @param questionAnswers a map of question IDs to their corresponding answers
     */
    public Survey(Integer id, Integer userId, Map<Integer, List<String>> questionAnswers) {
        this.id = id;
        this.userId = userId;
        setQuestionAnswers(questionAnswers); // Converts map to JSON
    }

    /**
     * Default constructor for JPA.
     */
    public Survey() {
        // Default constructor
    }

    /**
     * Gets the ID of the survey.
     *
     * @return the survey ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the survey.
     *
     * @param id the ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the user ID associated with the survey.
     *
     * @return the user ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with the survey.
     *
     * @param userId the user ID to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets the question-answer map for the survey.
     *
     * @return a map of question IDs to lists of answers
     */
    public Map<Integer, List<String>> getQuestionAnswers() {
        if (questionAnswers == null && questionAnswersJson != null) {
            // Deserialize JSON to Map if not already done
            questionAnswers = gson.fromJson(questionAnswersJson, new TypeToken<Map<Integer, List<String>>>() {}.getType());
        }
        return questionAnswers;
    }

    /**
     * Sets the question-answer map for the survey.
     *
     * @param questionAnswers a map of question IDs to lists of answers
     */
    public void setQuestionAnswers(Map<Integer, List<String>> questionAnswers) {
        this.questionAnswers = questionAnswers;
        this.questionAnswersJson = gson.toJson(questionAnswers); // Converts map to JSON
    }
}
