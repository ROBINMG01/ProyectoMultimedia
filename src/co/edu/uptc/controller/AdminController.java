package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class AdminController {
    private Movie movie;
    private Serie serie;
    private ArrayList<Movie> listMovies;
    private ArrayList<Serie> listSeries;

    public ArrayList<Movie> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<Movie> listMovies) {
        this.listMovies = listMovies;
    }

    public ArrayList<Serie> getListSeries() {
        return listSeries;
    }

    public void setListSeries(ArrayList<Serie> listSeries) {
        this.listSeries = listSeries;
    }

    public AdminController() {
        listMovies = new ArrayList<>();
        listSeries = new ArrayList<>();
        movie = new Movie();
        serie = new Serie();
    }

    public boolean addMovie(String name, String description, int duration, String gender, ArrayList<String> actors) {
        movie.setName(name);
        movie.setDescription(description);
        movie.setDuration(duration);
        movie.setGender(gender);
        movie.setListActors(actors);

        if (name.equals(movie.getName()) && duration == movie.getDuration()) {
            listMovies.add(new Movie(name, description, duration, gender, actors));
            return true;
        }
        return false;
    }

    public boolean addSerie(String name, String description, int duration, String gender, ArrayList<String> actors,
            ArrayList<String> chapters) {
        serie.setName(name);
        serie.setDescription(description);
        serie.setDuration(duration);
        serie.setGender(gender);
        serie.setListActors(actors);
        serie.setListChapters(chapters);

        if (name.equals(serie.getName()) && duration == serie.getDuration()) {
            listSeries.add(new Serie(name, description, duration, gender, actors, chapters));
            return true;
        }
        return false;
    }

    public ArrayList<Movie> showListMovies() {
        return listMovies;
    }

    public ArrayList<Serie> showListSeries() {
        return listSeries;
    }

    public ArrayList<Movie> filterMoviesByGenre(ArrayList<Movie> listMovies, String gener) {
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();
        for (Movie movie : listMovies) {
            if (movie.getGender().equals(gener)) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    public ArrayList<Serie> filterSeriesByGenre(ArrayList<Serie> listSeries, String gender) {
        ArrayList<Serie> filteredSeries = new ArrayList<Serie>();
        for (Serie serie : listSeries) {
            if (movie.getGender().equals(gender)) {
                filteredSeries.add(serie);
            }
        }
        return filteredSeries;
    }

}
