package controllers;

import models.Genre;
import models.Movie;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.Collection;
import java.util.List;


public class MovieActions extends Controller {

    @Transactional(readOnly = true)
    public static Result showAll() {

        List<Movie> allMovies = Movie.findAll();

        return ok(showAll.render(allMovies));

    }

    @Transactional(readOnly = true)
    public static Result addNew() {
        String successMessage = flash("success");
        return ok(addNew.render(Genre.findAll(), successMessage));
    }

    @Transactional
    public static Result createMovie() {
        DynamicForm form = Form.form().bindFromRequest();

        int genreId = Integer.parseInt(form.get("genre-id"));
        Genre genre;
        if (genreId == 0) {
            genre = new Genre();
            genre.name = form.get("genre-name");
            genre.save();
        } else {
            genre = Genre.findById(genreId);
        }

        String movieName = form.get("name");
        Movie movie = new Movie();
        movie.genre = genre;
        movie.title = movieName;
        movie.save();
        flash("success", "Eintrag erfolgreich gespeichert");
        return redirect(controllers.routes.MovieActions.addNew());
    }


    @Transactional(readOnly = true)
    public static Result showGenre(int genreId) {

        if (genreId == 0) {
            genreId = (session("last_genre_id") != null) ? new Integer(session("last_genre_id")) : 1;
        } else {
            session("last_genre_id", genreId + "");
        }
        Genre genre = Genre.findById(genreId);
        Collection<Movie> movies = genre.movies;
        List<Movie> moviesList = (List) movies;
        List<Genre> allGenres = Genre.findAll();

        return ok(showGenre.render(genre, allGenres, moviesList));
    }

}
