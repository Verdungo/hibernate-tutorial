package hibernate;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {

        // create session
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println("Session = " + session);

        // create students
        Student student1 = new Student("Ivan", "Ivanov", "ii@mail.com");
        Student student2 = new Student("Peter", "Petrov", "pp@mail.com");
        Student student3 = new Student("Vasiliy", "Vasiliev", "vv@mail.com");
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);
        try {
            // start transaction
            session.beginTransaction();

            // save
            session.save(student1);
            session.save(student2);
            session.save(student3);

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
