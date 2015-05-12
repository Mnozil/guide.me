package models;

import play.data.validation.Constraints;
import play.db.jpa.JPA;

import javax.persistence.*;
import javax.validation.Constraint;
import java.util.Collection;
import java.util.List;

@Entity
public class Genre {

    @Id
    @GeneratedValue
    public Integer id;

    @Constraints.Required
    public String name;

    @OneToMany(mappedBy = "genre")
    public Collection<Movie> movies;


    public static List<Genre> findAll() {

        TypedQuery<Genre> query = JPA.em().createQuery("SELECT g FROM Genre g", Genre.class);
        return query.getResultList();
    }

    public static Genre findById(Integer id) {
        return JPA.em().find(Genre.class, id);
    }

    public void save() {

        JPA.em().persist(this);
    }


}
