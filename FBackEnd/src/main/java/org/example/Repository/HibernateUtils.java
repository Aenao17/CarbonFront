package org.example.Repository;

import org.example.Domain.Question;
import org.example.Domain.Survey;
import org.example.Domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtils {

    private static SessionFactory sessionFactory;

    // Initialize the SessionFactory once as a singleton
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            sessionFactory = createNewSessionFactory();
        }
        return sessionFactory;
    }

    // This method creates the SessionFactory and sets Hibernate properties
    private static SessionFactory createNewSessionFactory() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Question.class)
                .addAnnotatedClass(Survey.class)
                .setProperty("hibernate.dialect", "org.hibernate.community.dialect.SQLiteDialect")
                .setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC")
                .setProperty("hibernate.connection.url", "jdbc:sqlite:C:/Users/letar/Desktop/FBackEnd/CarbonDB.sqlite")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.format_sql", "true")
                .setProperty("spring.jpa.hibernate.ddl-auto", "true")
                .setProperty("spring.jpa.properties.hibernate.dialect=org.hibernate.community.dialect.SQLiteDialect", "true")
                .setProperty("spring.jpa.properties.hibernate.show_sql", "true")
                .setProperty("spring.jpa.properties.hibernate.format_sql", "true");
        sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }

    // Close the SessionFactory when the application stops
    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
