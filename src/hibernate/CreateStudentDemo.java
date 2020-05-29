package hibernate;

import hibernate.entity.DateUtils;
import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {
    public static void main(String[] args) {
        // create session
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println("Session = " + session);


        try {
            // create student
            String birthDay = "25.01.1985";
            Student student = new Student("John", "Doe", "jhondoe@mail.com", DateUtils.parseDate(birthDay));
            System.out.println(student);

            // start transaction
            session.beginTransaction();

            // save
            session.save(student);

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
