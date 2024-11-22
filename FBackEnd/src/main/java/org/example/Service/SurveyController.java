package org.example.Service;


import org.example.Domain.Survey;
import org.example.Repository.SurveyHibernateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/{id}/submit")
    public ResponseEntity<Integer> submitSurvey(@PathVariable Integer id, @RequestBody Survey survey) {
        // Define hardcoded constants for specific question IDs
        Map<Integer, Integer> constants = Map.of(
                1, 2,
                2, 3,
                3, 5
        );

        // Initialize a total score
        int totalScore = 0;

        // Iterate through questions and answers
        List<Integer> questionIds = survey.getQuestionsIds();
        List<String> answers = survey.getAnswers();

        for (int i = 0; i < questionIds.size(); i++) {
            Integer questionId = questionIds.get(i);
            String answer = answers.get(i);

            try {
                int numericAnswer = Integer.parseInt(answer);

                totalScore += numericAnswer * constants.getOrDefault(questionId, 1);
            } catch (NumberFormatException e) {
                System.out.println("Invalid answer for question ID " + questionId + ": " + answer);
            }
        }

        return ResponseEntity.ok(totalScore);
    }

    // Endpoint to retrieve by ID
    @GetMapping("/{id}")
    public Survey getSurveyById(@PathVariable Integer id) {
        return SurveyHibernateRepository.find(id);
    }

    // Additional CRUD methods can be added here
}
