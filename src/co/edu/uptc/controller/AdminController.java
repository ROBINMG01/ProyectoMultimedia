package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import co.edu.uptc.utilitaries.Utilitaries;

public class AdminController {

    private Movie movie;
    private Serie serie;
    private ArrayList<Movie> listMovies;
    private ArrayList<Serie> listSeries;
 

    private ArrayList<String> listActorsTwo;
    private ArrayList<String> listChaptersTwo;
    private ArrayList<String> namesMovies;
    private ArrayList<String> namesSeries;
    private Utilitaries utilitaries;

    // ROBIN
    public AdminController() {
        listMovies = new ArrayList<>();
        listSeries = new ArrayList<>();
        listActorsTwo = new ArrayList<>();
        listChaptersTwo = new ArrayList<>();
        utilitaries = new Utilitaries();
        listMovies = utilitaries.loadMovies();
        listSeries = utilitaries.loadSeries();
        movie = new Movie();
        serie = new Serie();
    }

    // ROBIN
    public boolean addMovie(String name, String description, int duration, ArrayList<String> listActors,
            String gender) {
        movie.setName(name);
        movie.setDescription(description);
        movie.setDuration(duration);
        movie.setListActors(listActors);
        movie.setGender(gender);

        if (name.equals(movie.getName()) && duration == movie.getDuration()) {
            listMovies.add(movie);
            return true;
        }
        return false;

    }

    // ROBIN
    public boolean addSerie(String name, String description, int duration, ArrayList<String> listActors,
            ArrayList<String> chapters, String gender) {
        serie.setName(name);
        serie.setDescription(description);
        serie.setDuration(duration);
        serie.setListActors(listActors);
        serie.setListChapters(chapters);
        serie.setGender(gender);

        if (name.equals(serie.getName()) && duration == serie.getDuration()) {
            listSeries.add(new Serie(name, description, duration, listActors, chapters, gender));
            return true;
        }
        return false;
    }

    // ROBIN
    public int searchMovie(String name) {
        for (int i = 0; i < listMovies.size(); i++) {
            if (listMovies.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    // ROBIN
    public int searchSeries(String name) {
        for (int i = 0; i < listSeries.size(); i++) {
            if (listSeries.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    // ROBIN
    public boolean updateMovie(String name, String nameUpdate, String description, int duration,
            ArrayList<String> actors, String gender) {
        if (searchMovie(name) != -1) {
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

    // ROBIN
    public boolean updateSeries(String name, String nameUpdate, String description, int duration,
            ArrayList<String> listActors, ArrayList<String> chapters, String gender) {
        if (searchSeries(name) != -1) {
            int position = searchSeries(name);
            listSeries.get(position).setName(nameUpdate);
            listSeries.get(position).setDescription(description);
            listSeries.get(position).setDuration(duration);
            listSeries.get(position).setListActors(listActors);
            listSeries.get(position).setListChapters(chapters);
            listSeries.get(position).setGender(gender);
            return true;
        }
        return false;
    }

    // ROBIN
    public ArrayList<Movie> showListMovies() {
        return listMovies;
    }

    // ROBIN
    public ArrayList<Serie> showListSeries() {
        return listSeries;
    }

    public void addListActors(String name) {
        listActorsTwo.add(name);
    }

    // ROBIN
    public void addListChapters(String chapter) {
        listChaptersTwo.add(chapter);
    }

    public ArrayList<String> showListActorsTwo() {
        return listActorsTwo;
    }

    // ROBIN
    public ArrayList<String> showListChaptersTwo() {
        return listChaptersTwo;
    }

    // ROBIN
    public ArrayList<String> namesMovies() {
        namesMovies = new ArrayList<>();
        for (int i = 0; i < listMovies.size(); i++) {
            namesMovies.add(listMovies.get(i).getName());
        }
        return namesMovies;
    }

    // ROBIN
    public ArrayList<String> namesSeries() {
        namesSeries = new ArrayList<>();
        System.out.println(listSeries.size());
        for (int i = 0; i < listSeries.size(); i++) {
            namesSeries.add(listSeries.get(i).getName());
        }
        return namesSeries;
    }
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
}
