package ua.ypon.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.ypon.hibernate.model.Item;
import ua.ypon.hibernate.model.Person;
import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main( String[] args ) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person = new Person("Test cascading", 30);

            person.addItem(new Item("Item 1"));
            person.addItem(new Item("Item 2"));
            person.addItem(new Item("Item 3"));

            session.save(person);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
