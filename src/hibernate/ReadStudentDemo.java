package hibernate;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args) {
        // create session
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println("Session = " + session);

        // create student
        Student student = new Student("Daffy", "Duck", "duffyduck@mail.com");
        System.out.println(student);
        try {
            // start transaction
            session.beginTransaction();

            // save
            session.save(student);

            // commit
            session.getTransaction().commit();

            // -------------------------------------- READING
            //find PK
            int id = student.getId();

            //retrieve student by ID
            session = factory.getCurrentSession();
            session.beginTransaction();

            Student studentFromBase = session.get(Student.class, id);

            System.out.println("Got from base: " + studentFromBase);

            session.getTransaction().commit();

            System.out.println("Saved successfully!");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            factory.close();
            System.out.println("Factory closed.");
        }
    }
}
