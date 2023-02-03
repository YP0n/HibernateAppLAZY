package ua.ypon.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.ypon.hibernate.model.Person;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            //Person person = session.get(Person.class, 2);
            //person.setName("New name");//оновлення імені
            //session.delete(person);//видалення об'єкту

            Person person1 = new Person("Test1", 30);
            Person person2 = new Person("Test2", 34);
            Person person3 = new Person("Test3", 45);

            session.save(person1);
            session.save(person2);
            session.save(person3);

            session.getTransaction().commit();

            System.out.println(person1.getId());//отримуємо id сутності

        } finally {
            sessionFactory.close();
        }
    }
}
