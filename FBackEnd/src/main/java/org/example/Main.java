package org.example;

import org.example.Domain.Question;
import org.example.Repository.*;
import org.example.Service.IService;
import org.example.Service.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

@SpringBootApplication
public class Main {

    // URL for external API interaction (not currently used)
    public static final String URL = "http://localhost:8080";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String args[]) throws Exception {
        // Initializing repositories and service layer
        UserRepository userRepository = new UserHibernateRepository();
        QuestionRepository questionRepository = new QuestionHibernateRepository();
        SurveyRepository surveyRepository = new SurveyHibernateRepository();

        // Service layer to handle business logic
        IService service = new Service(userRepository, questionRepository, surveyRepository);

        // Starting the Spring Boot application
        SpringApplication.run(Main.class, args);
    }

    // Utility method for handling exceptions while executing callable tasks
    private static <T> T execute(Callable<T> callable) throws Exception {
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
