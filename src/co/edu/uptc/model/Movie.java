package co.edu.uptc.model;

import java.util.ArrayList;

public class Movie extends Film{
    private String name;
    private String description;
    private String duration;
    private ArrayList<Movie> listMovies;
    private String listActors;
    private String gender;

    public Movie() {
    }

    public Movie(String name, String description, String duration, String string, String gender) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.listActors = string;
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
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getListActors() {
        return listActors;
    }
    public void setListActors(String listActors) {
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
    public void AddDates() {
        listMovies.add(new Movie(name, description, duration, listActors, gender));
    }

    @Override
    public String toString() {
        return "Movie [name=" + name + ".\ndescription=" + description + ".\nduration=" + duration + ", gender=" 
        + gender  + ", listActors=" + listActors + "]\n\n";
    }
}
