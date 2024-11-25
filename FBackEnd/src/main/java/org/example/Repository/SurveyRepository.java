package org.example.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.Domain.Survey;

import java.util.List;

public interface SurveyRepository extends Repository<Integer, Survey> {
    List<Integer> getAllSurveyQuestions();
}
