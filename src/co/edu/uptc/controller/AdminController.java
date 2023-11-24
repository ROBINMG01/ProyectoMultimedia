package co.edu.uptc.controller;

import java.util.ArrayList;

import javax.security.auth.Subject;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class AdminController {

    private Movie movie;
    private Serie serie;
    private ArrayList<Movie> listMovies;
    private ArrayList<Serie> listSeries;
    private ArrayList<String> listActors;

    //private Movie movie1 = new Movie(); for class abstract
    //private Serie serie1 = new Serie(); for class abstract

    public AdminController() {
        listMovies = new ArrayList<>();
        listSeries = new ArrayList<>();
        listActors = new ArrayList<>();
        //listCharapter = new ArrayList<>();
        loadMovies();
        loadSeries();
        movie = new Movie();
        serie = new Serie();
    }

    

    public void loadSeries(){
        listSeries.add(new Serie("Loki","Loki es llevado ante la misteriosa organización llamada AVT", "45 m Capitulo", "Andrew, Monica, Robert", "Capitulo1, Capitulo2, Capitulo3, Capitulo4, Capitulo5, Capitulo6", "Ficción"), "Ficción");
        listSeries.add(new Serie("Strange Things","Es una serie de televisión dramática de misterio", "1h Capitulo", "Marthe, Bill, Carlos", "Capitulo1, Capitulo2, Capitulo3", "Drama"));
    }

    /*
     * Method for class abstract
    public void addMoviee(String name, String description, String duration, String actors){
        movie1.setName(name);
        movie1.setDescription(description);
        movie1.setDuration(duration);
        movie1.setListActors(actors);


    }
    
    Method for class abstract
    public void addSeriee(String name, String description, String duration, String actors, String chapters){
        serie1.setName(name);
        serie1.setDescription(description);
        serie1.setDuration(duration);
        serie1.setListActors(actors);
        serie1.setListChapters(chapters);
    }

    public String showMovie(){
        return movie1.toString();
    }

    public String showSerie(){
        return serie1.toString();
    }*/

    public boolean addMovie(String name, String description, String duration, String actors, String gender){
        movie.setName(name);
        movie.setDescription(description);
        movie.setDuration(duration);
        movie.setListActors(actors);
        movie.setGender(gender);
        
        if (name.equals(movie.getName()) && duration == movie.getDuration()) {
            listMovies.add(new Movie(name, description, duration, actors, gender));
            return true;
        }
        return false;
    }

    public boolean addSerie(String name, String description, String duration, String actors, String chapters, String gender){
        serie.setName(name);
        serie.setDescription(description);
        serie.setDuration(duration);
        serie.setListActors(actors);
        serie.setListChapters(chapters);
        serie.setGender(gender);

        
        if (name.equals(serie.getName()) && duration == serie.getDuration()) {
            listSeries.add(new Serie(name, description, duration, actors, chapters, gender));
            return true;
        }
        return false;
    }

    public int searchMovie(String name){
        for (int i = 0; i < listMovies.size(); i++) {
            if(listMovies.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public int searchSeries(String name){
        for (int i = 0; i < listSeries.size(); i++) {
            if(listSeries.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public boolean updateMovie(String name, String nameUpdate, String description, String duration, String actors, String gender){
        if (searchMovie(name)!= -1) {
            int position = searchMovie(name);
            listMovies.get(position).setName(nameUpdate);
            listMovies.get(position).setDescription(description);
            listMovies.get(position).setDuration(duration);
            listMovies.get(position).setListActors(actors);
            listMovies.get(position).setGender(gender);
            return true;
        }
        return false;
    }

    public boolean updateSeries(String name, String nameUpdate, String description, String duration, String actors, String chapters, String gender){
        if (searchSeries(name)!= -1) {
            int position = searchSeries(name);
            listSeries.get(position).setName(nameUpdate);
            listSeries.get(position).setDescription(description);
            listSeries.get(position).setDuration(duration);
            listSeries.get(position).setListActors(actors);
            listSeries.get(position).setListChapters(chapters);
            listSeries.get(position).setGender(gender);
            return true;
        }
        return false;
    }

    public ArrayList<Movie> showListMovies(){
        return listMovies;
    }

    /*public String showNamesMovies(){
        return listMovies;
    }*/

    public ArrayList<Serie> showListSeries(){
        return listSeries;
    }
}
