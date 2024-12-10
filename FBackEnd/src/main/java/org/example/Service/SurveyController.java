package org.example.Service;

import org.example.Domain.Question;
import org.example.Domain.Survey;
import org.example.Repository.QuestionRepository;
import org.example.Repository.SurveyHibernateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/surveys")
public class SurveyController {
    private final SurveyHibernateRepository surveyHibernateRepository;
    private final QuestionRepository questionRepository;

    public SurveyController(SurveyHibernateRepository surveyHibernateRepository, QuestionRepository questionRepository) {
        this.surveyHibernateRepository = surveyHibernateRepository;
        this.questionRepository = questionRepository;
    }

    // Retrieve all surveys
    @GetMapping
    public List<Survey> getAllSurveys() {
        return surveyHibernateRepository.getAll();
    }

    // Retrieve survey questions details
    @GetMapping("/{id}/questions/details")
    public List<Question> getSurveyQuestionsDetails(@PathVariable int id) {
        Survey survey = surveyHibernateRepository.find(id);
        return survey.getQuestionsIds().stream().map(questionRepository::findById).collect(Collectors.toList());
    }

    // Submit survey responses
    @PostMapping("/{id}/submit")
    public ResponseEntity<Integer> submitSurvey(@PathVariable Integer id, @RequestBody Survey survey) {
        Survey existingSurvey = surveyHibernateRepository.find(id);
        List<Integer> existingQuestions = existingSurvey.getQuestionsIds();

        if (survey.getQuestionsIds().size() != existingQuestions.size()) {
            return ResponseEntity.badRequest().body(-1); // Return an error score
        }

        // Validate responses
        for (int i = 0; i < existingQuestions.size(); i++) {
            Question question = questionRepository.findById(existingQuestions.get(i));
            String answer = String.valueOf(survey.getAnswers().get(i));

            if (!validateAnswer(question, answer)) {
                return ResponseEntity.badRequest().body(-1);
            }
        }

        // Calculate score
        int totalScore = calculateScore(survey);
        return ResponseEntity.ok(totalScore);
    }

    private boolean validateAnswer(Question question, String answer) {
        if ("single-choice".equals(question.getType()) || "multiple-choice".equals(question.getType())) {
            try {
                int numericAnswer = Integer.parseInt(answer);
                return numericAnswer > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private int calculateScore(Survey survey) {
        Map<Integer, Integer> constants = Map.of(1, 2, 2, 3, 3, 5);
        int totalScore = 0;

        for (int i = 0; i < survey.getQuestionsIds().size(); i++) {
            Integer questionId = survey.getQuestionsIds().get(i);
            Question question = questionRepository.findById(questionId);
            List<String> dbAnswer = question.getOptions();
            List<String> answerFromSurvey = survey.getAnswers().get(i);

            totalScore = 0;
            try {
                // Mergem prin fiecare raspuns dat si vedem daca da match cu cele salvate in DB. daca da adaugam scorul atasat
                for (String answer : answerFromSurvey) {
                    for (int j = 1; j <= dbAnswer.size(); j++) {
                        if (dbAnswer.get(i).equals(answer)) {
                            totalScore += question.getCo2Values().get(i);
                        }
                    }

                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid answer for question ID " + questionId);
            }
        }
        return totalScore;
    }

    // Add a new survey
    @PostMapping
    public ResponseEntity<?> createSurvey(@RequestBody Survey survey) {
        if (survey.getQuestionsIds() == null || survey.getQuestionsIds().isEmpty()) {
            return ResponseEntity.badRequest().body("Survey must include at least one question.");
        }
        surveyHibernateRepository.add(survey);
        return ResponseEntity.ok("Survey created successfully.");
    }
}
