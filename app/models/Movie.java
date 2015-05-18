package models;

import org.hibernate.annotations.GeneratorType;
import play.data.validation.Constraints;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Entity
public class Movie {


    @Id
    @GeneratedValue
    public Integer id;

    @Constraints.Required
    public String title;

    //    @ManyToOne
//    @JoinColumn(name = "genre")
    public String genre;

    public String year;

    public Boolean watched;


    public static List<Movie> findAll() {

        TypedQuery<Movie> query = JPA.em().createQuery("SELECT m FROM Movie m", Movie.class);
        return query.getResultList();
    }
    public static Movie findById(Integer id) {
        return JPA.em().find(Movie.class, id);
    }

    public void save() {

        JPA.em().persist(this);
    }

    public Movie update() {
        return JPA.em().merge(this);

    }

    public void delete() {
        JPA.em().remove(this);
    }
}
