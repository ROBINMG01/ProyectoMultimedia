package co.edu.uptc.model;

import java.util.ArrayList;

public class Serie {
    private String name;
    private String description;
    private int duration;
    private String gender;
    private ArrayList<String> listActors;
    private ArrayList<Serie> listSeries;
    private ArrayList<String> listChapters;

    public Serie() {
    }

    public Serie(String name, String description, int duration, ArrayList<String> listActors,
            ArrayList<String> listChapters, String gender) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.gender = gender;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<String> getListActors() {
        return listActors;
    }

    public void setListActors(ArrayList<String> listActors) {
        this.listActors = listActors;
    }

    public ArrayList<String> getListChapters() {
        return listChapters;
    }

    public void setListChapters(ArrayList<String> listChapters) {
        this.listChapters = listChapters;
    }

    public ArrayList<Serie> getListSeries() {
        return listSeries;
    }

    public void setListSeries(ArrayList<Serie> listSeries) {
        this.listSeries = listSeries;
    }

    @Override
    public String toString() {
        return "Serie [name=" + name + ".\ndescription=" + description + ".\nduration=" + duration + ".\ngender="
                + gender + ".\nlistActors=" + listActors + ".\nlistChapters=" + listChapters
                + "]\n\n";
    }
}
