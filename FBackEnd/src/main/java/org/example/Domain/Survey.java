package org.example.Domain;

import jakarta.persistence.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

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

    public Survey(Integer id, Integer userId, List<Integer> questionIds, List<String> answers) {
        this.id = id;
        this.userId = userId;
        setQuestionIds(questionIds);
        setAnswers(answers);
    }

    public Survey() {
        // Default constructor
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getQuestionsIds() {
        if (questionsIds == null && questionsIdsJson != null) {
            questionsIds = gson.fromJson(questionsIdsJson, new TypeToken<List<Integer>>(){}.getType());
        }
        return questionsIds;
    }

    public void setQuestionIds(List<Integer> questionIds) {
        this.questionsIds = questionIds;
        this.questionsIdsJson = gson.toJson(questionIds);
    }

    public List<String> getAnswers() {
        if (answers == null && answersJson != null) {
            answers = gson.fromJson(answersJson, new TypeToken<List<String>>(){}.getType());
        }
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
        this.answersJson = gson.toJson(answers);
    }
}
