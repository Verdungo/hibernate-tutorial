package hibernate;

import hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForStudentDemo {
    public static void main(String[] args) {
        // create session
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println("Session = " + session);


        try {
            // start transaction
            session.beginTransaction();

            Course course = session.get(Course.class, 1);

            Student student = session.get(Student.class, 11);

            course.add(student);

            session.save(course);
            //session.save(student);//?

            // commit
            session.getTransaction().commit();
            System.out.println("Saved successfully!");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
            System.out.println("Factory closed.");
        }
    }
}
