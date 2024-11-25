// QuestionController.java
package org.example.Service;

import org.example.Domain.Question;
import org.example.Repository.QuestionHibernateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    // Autowired to inject the repository dependency
    private final QuestionHibernateRepository questionRepository;

    public QuestionController(QuestionHibernateRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // Endpoint to retrieve all questions
    @GetMapping
    public List<Question> getAllQuestions() {
        return questionRepository.getAll();
    }

    // Endpoint to retrieve a question by its ID
    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Integer id) {
        return QuestionHibernateRepository.find(id);
    }

    // Additional CRUD methods can be added here
}
