package co.edu.uptc.model;

import java.util.ArrayList;

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

    public boolean updateMovie(String name, String nameUpdate, String description, int duration,
    ArrayList<String> listAuthors, ArrayList<String> listActors, String gender, int year) {
        try {
            for (int i = 0; i < movies.size(); i++) {
                if (movies.get(i).getName().equals(name)) {
                    movies.get(i).setName(nameUpdate);
                    movies.get(i).setYear(year);
                    movies.get(i).setGender(gender);
                    movies.get(i).setDuration(duration);
                    movies.get(i).setlistAuthors(listAuthors);
                    movies.get(i).setListActors(listActors);
                    movies.get(i).setDescription(description);
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}