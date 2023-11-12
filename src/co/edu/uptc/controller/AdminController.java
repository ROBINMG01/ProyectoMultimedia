package co.edu.uptc.controller;

import java.util.ArrayList;

import javax.security.auth.Subject;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class AdminController {
    private String id;
    private Movie movie;
    private Serie serie;
    private ArrayList<Movie> listMovies;
    private ArrayList<Serie> listSeries;

    public AdminController() {
        listMovies = new ArrayList<>();
        listSeries = new ArrayList<>();
        movie = new Movie();
        serie = new Serie();
    }

    public boolean addMovie(String name, String description, int duration, ArrayList<String> actors){
        movie.setName(name);
        movie.setDescription(description);
        movie.setDuration(duration);
        movie.setListActors(actors);
        
        if (name.equals(movie.getName()) && duration == movie.getDuration()) {
            listMovies.add(new Movie(name, description, duration, actors));
            return true;
        }
        return false;
    }

    public boolean addSerie(String name, String description, int duration, ArrayList<String> actors, ArrayList<String> chapters){
        serie.setName(name);
        serie.setDescription(description);
        serie.setDuration(duration);
        serie.setListActors(actors);
        serie.setListChapters(chapters);

        
        if (name.equals(serie.getName()) && duration == serie.getDuration()) {
            listSeries.add(new Serie(name, description, duration, actors, chapters));
            return true;
        }
        return false;
    }

    public ArrayList<Movie> showListMovies(){
        return listMovies;
    }

    public ArrayList<Serie> showListSeries(){
        return listSeries;
    }
}
