package hibernate;

import hibernate.entity.DateUtils;
import hibernate.entity.Instructor;
import hibernate.entity.InstructorDetail;
import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
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
            // create obj
//            InstructorDetail instructorDetail = new InstructorDetail("youtube.com/jdoe", "swimming");
//            Instructor instructor = new Instructor("John", "Doe", "johndoe@mail.com", instructorDetail);

            //InstructorDetail instructorDetail2 = new InstructorDetail("--", "guitear");
            Instructor instructor2 = new Instructor("Lol", "Kek", "cheburek@mail.com", null);

            // start transaction
            session.beginTransaction();

            // save
            session.save(instructor2);

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
