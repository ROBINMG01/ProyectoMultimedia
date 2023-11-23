package co.edu.uptc.model;

import java.util.ArrayList;

public class Serie extends Film{
    private String name;
    private String description;
    private String duration;
    private String listActors;
    private ArrayList<Serie> listSeries;
    private String listChapters;

    public Serie(String name2, String description2, ArrayList<String> actors) {
    }
    
    public Serie(String name, String description, String duration, String listActors,
            String listChapters) {
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
    public String getListChapters() {
        return listChapters;
    }
    public void setListChapters(String listChapters) {
        this.listChapters = listChapters;
    }
    @Override
    public void AddDates() {
        listSeries.add(new Serie(name, description, duration, listActors, listChapters));
    }
    @Override
    public String toString() {
        return "Series [name=" + name + ", description=" + description + ", duration=" + duration + ", listActors="
                + listActors + ", ListChapters=" + listChapters + "]";
    }
}
