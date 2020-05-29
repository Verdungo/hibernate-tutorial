package employee;

import employee.entity.Employee;
import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CreateEmployeeDemo {
    public static void main(String[] args) {
        // create session
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println("Session = " + session);

        // create employee
        Employee john = new Employee("John", "Doe", "Microsoft");
        System.out.println(john);
        try {
            // start transaction
            session.beginTransaction();
            // save
            session.save(john);
            // commit
            session.getTransaction().commit();

            // start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();
            // save
            int id = john.getId();
            Employee fromBase = session.get(Employee.class, id);
            System.out.println(fromBase);
            // commit
            session.getTransaction().commit();

            // start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();
            // save
            String company = "Microsoft";
            List<Employee> m = session.createQuery("from Employee where company = '" + company + "'").getResultList();
            for (Employee employee : m) {
                System.out.println(employee);
            }
            // commit
            session.getTransaction().commit();

            // start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();
            // save
            session.delete(session.get(Employee.class, id));
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
