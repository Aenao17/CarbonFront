package org.example.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.*;
import java.util.List;

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

    @Column(name = "questionsIds")
    private String questionsIdsJson;

    @Column(name = "answers")
    private String answersJson;

    @Transient
    private List<Integer> questionsIds;

    @Transient
    private List<String> answers;

    private static final Gson gson = new Gson();

    /**
     * Constructor with all fields.
     *
     * @param id        the ID of the survey
     * @param userId    the ID of the associated user
     * @param questionIds a list of question IDs
     * @param answers   a list of corresponding answers
     */
    public Survey(Integer id, Integer userId, List<Integer> questionIds, List<String> answers) {
        this.id = id;
        this.userId = userId;
        setQuestionIds(questionIds);
        setAnswers(answers);
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
     * Gets the list of question IDs for the survey.
     *
     * @return a list of question IDs
     */
    public List<Integer> getQuestionsIds() {
        if (questionsIds == null && questionsIdsJson != null) {
            // Deserialize JSON to List<Integer> correctly using Gson
            questionsIds = gson.fromJson(questionsIdsJson, new TypeToken<List<Integer>>(){}.getType());
        }
        return questionsIds;
    }

    /**
     * Sets the list of question IDs for the survey.
     *
     * @param questionIds a list of question IDs
     */
    public void setQuestionIds(List<Integer> questionIds) {
        this.questionsIds = questionIds;
        this.questionsIdsJson = gson.toJson(questionIds); // Converts list to JSON
    }

    /**
     * Gets the list of answers for the survey.
     *
     * @return a list of answers
     */
    public List<String> getAnswers() {
        if (answers == null && answersJson != null) {
            // Deserialize JSON to List<String> correctly using Gson
            answers = gson.fromJson(answersJson, new TypeToken<List<String>>(){}.getType());
        }
        return answers;
    }

    /**
     * Sets the list of answers for the survey.
     *
     * @param answers a list of answers
     */
    public void setAnswers(List<String> answers) {
        this.answers = answers;
        this.answersJson = gson.toJson(answers); // Converts list to JSON
    }
}
