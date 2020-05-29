package hibernate;

import hibernate.entity.Course;
import hibernate.entity.Instructor;
import hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
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
            /*Course course1 = new Course("Java");
            Course course2 = new Course("Python");
            Course course3 = new Course("Ruby");*/
            Instructor instructor = new Instructor("Vasya", "Pupkin","pupkin@mail.com");
            InstructorDetail instructorDetail = new InstructorDetail("youtube.com/vp", "Video Games");
            instructor.setInstructorDetail(instructorDetail);

            // start transaction
            session.beginTransaction();

            // save
            session.save(instructor);

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
