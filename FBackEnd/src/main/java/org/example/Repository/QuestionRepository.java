package org.example.Repository;

import org.example.Domain.Question;

import java.util.List;

/**
 * Repository interface for managing Question entities.
 * Extends the base Repository interface with Integer as the ID type
 * and Question as the entity type.
 */
public interface QuestionRepository extends Repository<Integer, Question> {
    Question findById(Integer id);
    void add(Question question);
    List<Question> getAll();
    void delete(Integer id);
}
