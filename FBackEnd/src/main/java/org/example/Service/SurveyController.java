package org.example.Service;


import org.example.Domain.Survey;
import org.example.Repository.SurveyHibernateRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/surveys")
public class SurveyController {
    private final SurveyHibernateRepository surveyHibernateRepository;

    public SurveyController(SurveyHibernateRepository surveyHibernateRepository) {
        this.surveyHibernateRepository = surveyHibernateRepository;
    }

    // Endpoint to retrieve all
    @GetMapping
    public List<Survey> getAllSurveys() {
        return surveyHibernateRepository.getAll();
    }

    // Endpoint to retrieve all questions
    @GetMapping("/{id}/questions")
    public List<Integer> getAllSurveyQuestions(@PathVariable int id) {
        return SurveyHibernateRepository.find(id).getQuestionsIds();
    }

    // Endpoint to retrieve by ID
    @GetMapping("/{id}")
    public Survey getSurveyById(@PathVariable Integer id) {
        return SurveyHibernateRepository.find(id);
    }

    // Additional CRUD methods can be added here
}
