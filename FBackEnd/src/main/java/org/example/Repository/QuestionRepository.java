package org.example.Repository;

import org.example.Domain.Question;

/**
 * Repository interface for managing Question entities.
 * Extends the base Repository interface with Integer as the ID type
 * and Question as the entity type.
 */
public interface QuestionRepository extends Repository<Integer, Question> {
    // Additional specific methods for Question entities can be defined here if needed
}
