package org.example.Repository;

import org.example.Domain.Question;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for managing Question entities with Hibernate.
 */
@Repository
public class QuestionHibernateRepository implements QuestionRepository {

    /**
     * Finds a Question by its ID.
     *
     * @param id the ID of the Question to find
     * @return the Question object, or null if not found
     */
    public static Question find(Integer id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Question where id = :idQ", Question.class)
                    .setParameter("idQ", id)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Adds a new Question to the database.
     *
     * @param question the Question entity to add
     */
    @Override
    public void add(Question question) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(question);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieves all Questions from the database.
     *
     * @return a list of all Question entities
     */
    public List<Question> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Question", Question.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Deletes a Question from the database by its ID.
     *
     * @param id the ID of the Question to delete
     */
    public void delete(Integer id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Question question = session.find(Question.class, id);
                if (question != null) {
                    session.remove(question);
                }
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }
}
