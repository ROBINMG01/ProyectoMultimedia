package co.edu.uptc.model;

import java.util.ArrayList;

public class Movie {
    private String name;
    private String description;
    private int duration;
    private ArrayList<Movie> listMovies;
    private ArrayList<String> listActors;
    private String gender;

    public Movie() {
    }

    public Movie(String name, String description, int duration, ArrayList<String> listActors, String gender) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.listActors = listActors;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public ArrayList<String> getListActors() {
        return listActors;
    }
    public void setListActors(ArrayList<String> listActors) {
        this.listActors = listActors;
    }
    
    public ArrayList<Movie> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<Movie> listMovies) {
        this.listMovies = listMovies;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Movie [name=" + name + ".\ndescription=" + description + ".\nduration=" + duration + ", gender=" 
        + gender  + ", listActors=" + listActors + "]\n\n";
    }
}
