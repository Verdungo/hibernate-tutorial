package hibernate;

import hibernate.entity.Course;
import hibernate.entity.Instructor;
import hibernate.entity.InstructorDetail;
import hibernate.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviewsDemo {
    public static void main(String[] args) {
        // create session
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println("Session = " + session);


        try {
            // start transaction
            session.beginTransaction();

            int id = 7;

            Course course = session.get(Course.class,id);

            System.out.println(course.getReviews());

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
