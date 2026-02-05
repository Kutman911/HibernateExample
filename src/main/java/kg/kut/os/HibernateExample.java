package kg.kut.os;


import kg.kut.os.config.HibernateConfig;
import kg.kut.os.entity.FacultyType;
import kg.kut.os.entity.Group;
import kg.kut.os.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class HibernateExample {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        EntityManagerFactory entityManagerFactory = context.getBean(EntityManagerFactory.class);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

//            Group group = new Group("240104", FacultyType.MATHEMATICS);
//            Student student = new Student(21, "John", "Doe");
//            student.setGroup(group);
//            entityManager.persist(group);
//            entityManager.persist(student);

            Student student = entityManager.find(Student.class, 1);
            Group group = student.getGroup();
            System.out.println(student);
            System.out.println(group);



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
