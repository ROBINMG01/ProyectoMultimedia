package co.edu.uptc.model;

import java.util.ArrayList;

public class Movie {
    private String name;
    private String description;
    private int duration;
    private ArrayList<String> listActors;

    public Movie() {
    }

    public Movie(String name, String description, int duration, ArrayList<String> listActors) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.listActors = listActors;
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
    @Override
    public String toString() {
        return "Movie [name=" + name + ", description=" + description + ", duration=" + duration + ", actors=" + listActors
                + "]";
    }
}