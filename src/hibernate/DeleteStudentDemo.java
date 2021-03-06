package hibernate;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args) {
        // create session
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println("Session = " + session);

        try {
            // start transaction
            session.beginTransaction();

            int id = 2;

            Student student = session.get(Student.class, id);

            if (student != null) session.delete(student);

            // commit
            session.getTransaction().commit();

            // start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            session.createQuery("delete from Student where id=4").executeUpdate();

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
