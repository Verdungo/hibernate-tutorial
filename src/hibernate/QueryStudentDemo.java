package hibernate;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {
        // create session
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println("Session = " + session);

        try {
            // start transaction
            session.beginTransaction();

            List<Student> studentList = session.createQuery("from Student").getResultList();

            System.out.println("All students:");
            displayStudents(studentList);

            // query: lastName = 'Ivanov'
            studentList = session.createQuery("from Student where lastName = 'Ivanov'").getResultList();
            System.out.println("Ivanov query:");
            displayStudents(studentList);

            // query: lastName = 'Ivanov' OR firstName = 'Daffy'
            studentList = session.createQuery("from Student where lastName = 'Ivanov'" +
                    " OR firstName = 'Daffy'").getResultList();
            System.out.println("Daffy OR Ivanov query:");
            displayStudents(studentList);

            // query: email like '%mail.com'
            studentList = session.createQuery("from Student where email like '%mail.com'").getResultList();
            System.out.println("Email query:");
            displayStudents(studentList);

            // commit
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            factory.close();
            System.out.println("Factory closed.");
        }
    }

    private static void displayStudents(List<Student> studentList) {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
