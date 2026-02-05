package kg.kut.os;


import kg.kut.os.config.HibernateConfig;
import kg.kut.os.entity.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;

public class HibernateExample {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        EntityManagerFactory entityManagerFactory = context.getBean(EntityManagerFactory.class);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Section basketballSection = new Section(SectionType.BASKETBALL);
            entityManager.persist(basketballSection);
            Section footballSection = new Section(SectionType.FOOTBALL);
            entityManager.persist(footballSection);
            Section swimmingSection = new Section(SectionType.SWIMMING);
            entityManager.persist(swimmingSection);
            Section rugbySection = new Section(SectionType.RUGBY);
            entityManager.persist(rugbySection);

            Student student1  = new Student(21, "Lincoln", "Washington");
            student1.setSections(Arrays.asList(basketballSection, footballSection, rugbySection));
            entityManager.persist(student1);
            Student student2  = new Student(22, "John", "Travolta");
            student2.setSections(Arrays.asList(swimmingSection));
            entityManager.persist(student2);
            Student student3  = new Student(23, "Michael", "Jackson");
            student3.setSections(Arrays.asList(basketballSection, footballSection));
            entityManager.persist(student3);
            Student student4  = new Student(24, "Stephen", "Curry");
            student4.setSections(Arrays.asList(swimmingSection));
            entityManager.persist(student4);
            Student student5  = new Student(25, "Michael", "Jackson");
            student5.setSections(Arrays.asList(basketballSection, footballSection));
            entityManager.persist(student5);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Caught exception, doing rollback...");
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        context.close();
    }
}
