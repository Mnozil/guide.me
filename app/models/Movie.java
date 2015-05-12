package models;

import play.data.validation.Constraints;
import play.db.jpa.JPA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TypedQuery;
import javax.validation.Constraint;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    public int id;

    @Constraints.Required
    public String title;

    public void save() {
        JPA.em().persist(this);
    }

    public static List<Movie> findAll() {
        TypedQuery<Movie> query = JPA.em().createQuery("SELECT m FROM Movie m", Movie.class);
        return query.getResultList();
    }
}
