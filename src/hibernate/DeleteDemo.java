package hibernate;

import hibernate.entity.Instructor;
import hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
    public static void main(String[] args) {
        // create session
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println("Session = " + session);

        try {
            // start transaction
            session.beginTransaction();

            Instructor instructor = session.get(Instructor.class, 1);

            // save
            if (instructor != null) {
                session.delete(instructor);
            }

            // commit
            session.getTransaction().commit();
            System.out.println("Saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            System.out.println("Factory closed.");
        }
    }
}
