package hibernate;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args) {
        // create session
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println("Session = " + session);

        try {

            //find PK
            int id = 1;

            //retrieve student by ID
            session.beginTransaction();

            Student studentFromBase = session.get(Student.class, id);

            System.out.println("Got from base: " + studentFromBase);

            System.out.println("Editing...........");

            studentFromBase.setFirstName("Scooby");

            session.getTransaction().commit();



            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Update email far ALL!");
            session.createQuery("update Student set email = 'foo@gmail.com'").executeUpdate();

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
