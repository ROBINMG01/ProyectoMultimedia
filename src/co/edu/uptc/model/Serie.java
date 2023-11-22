package co.edu.uptc.model;

import java.util.ArrayList;

public class Serie extends Film{
    private String name;
    private String description;
    private String duration;
    private String listActors;
    private ArrayList<Serie> listSeries;
    private String listChapters;
    private String gender;

    public Serie() {
    }
    
    public Serie(String name, String description, String duration, String listActors,
            String listChapters, String gender) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.listActors = listActors;
        this.listChapters = listChapters;
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
    public String getListChapters() {
        return listChapters;
    }
    public void setListChapters(String listChapters) {
        this.listChapters = listChapters;
    }
    public ArrayList<Serie> getListSeries() {
        return listSeries;
    }

    public void setListSeries(ArrayList<Serie> listSeries) {
        this.listSeries = listSeries;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Override
    public void AddDates() {
        listSeries.add(new Serie(name, description, duration, listActors, listChapters, gender));
    }

    @Override
    public String toString() {
        return "Serie [name=" + name + ".\ndescription=" + description + ".\nduration=" + duration + ".\ngender=" 
                + gender +".\nlistActors=" + listActors + ".\nlistChapters=" + listChapters 
                + "]\n\n";
    }
}
