package ua.ypon.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.ypon.hibernate.model.Items;
import ua.ypon.hibernate.model.Person;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Items.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();//Об'єкт який предоставляє можливість роботи з Hibernate
        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Person person = session.get(Person.class, 1);
            System.out.println("Отримуємо людину з таблиці");

            session.getTransaction().commit();

            System.out.println("Сесія закінчилась (session.close)");

            //Відкриваємо сесію ще раз(в будь-якому місці коду)
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Всередені транзакції");

            person = (Person) session.merge(person);//зв'язуємо сутність з новою сесією
            Hibernate.initialize(person.getItems());
            session.getTransaction().commit();

            System.out.println("Поза другою сесією");

            //Це працює, бо зв'язані товари були завантажені
            System.out.println(person.getItems());
        }
    }
}
