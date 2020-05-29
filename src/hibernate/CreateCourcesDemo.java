package hibernate;

import hibernate.entity.Course;
import hibernate.entity.Instructor;
import hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourcesDemo {
    public static void main(String[] args) {
        // create session
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println("Session = " + session);


        try {
            // create obj


            // start transaction
            session.beginTransaction();

            int id = 6;
            Instructor instructor = session.get(Instructor.class, id);
            /*Course course1 = new Course("Java");
            Course course2 = new Course("Python");
            Course course3 = new Course("Ruby");
            instructor.add(course1);
            instructor.add(course2);
            instructor.add(course3);

            // save
            session.save(course1);
            session.save(course2);
            session.save(course3);*/

            System.out.println(instructor);

            // commit
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
