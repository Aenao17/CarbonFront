package org.example.Repository;

import org.example.Domain.Survey;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SurveyHibernateRepository implements SurveyRepository {

    public static Survey find(Integer id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from Survey where id=:idS", Survey.class)
                    .setParameter("idS", id)
                    .getSingleResultOrNull();
        }
    }

    @Override
    public void add(Survey survey) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(survey));
    }

    @Override
    public List<Survey> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Survey", Survey.class).getResultList();
        }
    }

    public void delete(Integer id) {
        HibernateUtils.getSessionFactory().inTransaction(session -> {
            Survey survey = session.find(Survey.class, id);
            if (survey != null) {
                session.remove(survey);
            }
        });
    }
}
