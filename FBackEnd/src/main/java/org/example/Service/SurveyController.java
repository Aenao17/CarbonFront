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

    @PostMapping("/{id}/submit")
    public ResponseEntity<Integer> submitSurvey(@PathVariable Integer id, @RequestBody Survey survey) {
        // Retrieve the existing survey from the repository based on the provided id
        Survey existingSurvey = surveyHibernateRepository.getAll().get(id - 1);
        if (existingSurvey == null) {
            return ResponseEntity.notFound().build();  // Return 404 if survey not found
        }

        List<Integer> existingQuestions = existingSurvey.getQuestionsIds();


        // Validate responses

        for(int existingQuestionId: survey.getQuestionsIds())
                for (List<String> answers : survey.getAnswers())
                    for( String answer: answers){
                        if (!validateAnswer(questionRepository.findById(existingQuestionId), answer)) {
                            return ResponseEntity.badRequest().body(-2); // Return error if answer validation fails
                }
            }

        // Calculate the score if all answers are valid
        int totalScore = calculateScore(survey);
        return ResponseEntity.ok(totalScore);  // Return the calculated score
    }

    private boolean validateAnswer(Question question, String answer) {
        if ("single-choice".equals(question.getType()) || "multiple-choice".equals(question.getType())) {
            try {
                // Validate that the answer is numeric and greater than 0

                return Integer.parseInt(answer) >= 0;

            } catch (NumberFormatException e) {
                return false;  // Return false if the answer cannot be parsed as an integer
            }
        }
        return true;
    }

    private int calculateScore(Survey survey) {
        int totalScore = 0;

        // Iterate through each question ID in the survey
        for (int i = 0; i < survey.getQuestionsIds().size(); i++) {
            Integer questionId = survey.getQuestionsIds().get(i);
            Question question = questionRepository.findById(questionId);

            if (question == null) {
                continue; // Skip if the question is not found
            }

            List<String> answerFromSurvey = survey.getAnswers().get(i);
            List<Double> co2_values = question.getCo2Values();

            // Calculate CO2 score based on answers for each question
            try {
                for (int j = 0; j < answerFromSurvey.size() - 1; j++) {
                    String answer = answerFromSurvey.get(j);
                    totalScore += (int) (Integer.parseInt(answer) * co2_values.get(j));  // Use the correct index for co2 values
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid answer for question ID " + questionId);
            }
        }
        return totalScore;  // Return the final calculated score
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
