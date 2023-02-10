package ua.ypon.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.ypon.hibernate.model.Actor;
import ua.ypon.hibernate.model.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Actor actor = session.get(Actor.class, 2);
            System.out.println(actor.getMovies());

            Movie movieToRemove = actor.getMovies().get(0);//отримуємо фільм для актора(який хочемо видалити) за індексом(0)

            actor.getMovies().remove(0);//отримуємо список фільмів і видаляємо фільм за індексом(0)(сторона актера)
            movieToRemove.getActors().remove(actor);//отримуємо актера і видаляємо його(сторона вільму)


//            Movie movie = new Movie("Reservoir Dogs", 1992);
//            Actor actor = session.get(Actor.class, 1);
//            movie.setActors(new ArrayList<>(Collections.singleton(actor)));
//            actor.getMovies().add(movie);//отримуємо список стрічок з актором і за допомогою
            // persistentСТАНУ-новий фільм hibernate зв'яже з актором
            //session.save(movie);


 //            Actor actor = session.get(Actor.class, 2);
//            System.out.println(actor.getMovies());

//            Movie movie = session.get(Movie.class, 1);
//            System.out.println(movie.getActors());

            //Movie movie = new Movie("Pulp fiction", 1994);
//            Actor actor1 = new Actor("Harvey Keitel", 81);
//            Actor actor2 = new Actor("Samuel L. Jackson", 72);
//
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
//
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//
//            session.save(movie);

            //session.save(actor1);
            //session.save(actor2);

            session.getTransaction().commit();
        }
    }
}
