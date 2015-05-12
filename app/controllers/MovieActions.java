package controllers;

import models.Movie;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import javax.transaction.Transactional;
import java.util.List;

public class MovieActions extends Controller {

    @play.db.jpa.Transactional(readOnly = true)
    public static Result showAll() {
        List<Movie> allMovies = Movie.findAll();
        return ok(views.html.showAll.render(allMovies));
    }


    @play.db.jpa.Transactional
    public static Result createMovie() {

        DynamicForm form = Form.form().bindFromRequest();
        String movieTitle = form.get("title");

        Movie movie = new Movie();
        movie.title=movieTitle;
        movie.save();
        flash("success", "Movie sucessfull created");
        return redirect(controllers.routes.MovieActions.addMovie());
    }

    public static Result addMovie() {
        return ok(views.html.addNew.render());
    }
}
