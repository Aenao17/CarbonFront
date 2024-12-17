package org.example.Service;

import org.example.Domain.Question;
import org.example.Domain.Survey;
import org.example.Domain.User;
import org.example.Repository.QuestionRepository;
import org.example.Repository.SurveyRepository;
import org.example.Repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements IService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;

    private final Map<String, IObserver> loggedClients = new HashMap<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public Service(UserRepository userRepository, QuestionRepository questionRepository,
                   SurveyRepository surveyRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
    }

    @Override
    public User login(String username, String password, IObserver iobs) {
        User user = userRepository.getByCredentials(username, password);
        if (user != null) {
            loggedClients.put(user.getUsername(), iobs);
            return user;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        return userRepository.getAll();
    }

    @Override
    public void logout(User user, IObserver client) {
        loggedClients.remove(user.getUsername());
    }

    @Override
    public void addUser(User user) {
        userRepository.add(user);
        notifyClients();
    }

    @Override
    public void addSurvey(Survey survey) {
        surveyRepository.add(survey);
        notifyClients();
    }

    @Override
    public void addQuestion(Question question) {
        questionRepository.add(question);
        notifyClients();
    }

    private void notifyClients() {
        executorService.submit(() -> {
            for (IObserver client : loggedClients.values()) {
                client.update();
            }
        });
    }
}
