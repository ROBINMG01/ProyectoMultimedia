package co.edu.uptc.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieRepository {

    private ObservableList<Movie> movies = FXCollections.observableArrayList();

    // Instancia singleton
    private static MovieRepository instance;

    private MovieRepository() {}

    // Método para obtener la instancia
    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    public ObservableList<Movie> getMovies() {
        return movies;
    }

    public boolean addMovie(Movie movie) {
        try {
            movies.add(movie);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}