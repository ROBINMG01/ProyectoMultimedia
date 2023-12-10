package co.edu.uptc.model;

import java.util.ArrayList;

public class Serie {
    private String name;
    private String gender;
    private int duration;
    private ArrayList<Serie> listSeries;
    private ArrayList<String> listAuthors;
    private ArrayList<String> listActors;
    private String description;
    private ArrayList<String> listChapters;

    public Serie() {}

    public Serie(String name, String description, int duration, ArrayList<String> listAuthors,
            ArrayList<String> listChapters, String gender, ArrayList<String> listActors) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.gender = gender;
        this.listAuthors = listAuthors;
        this.listChapters = listChapters;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<String> getlistAuthors() {
        return listAuthors;
    }

    public void setlistAuthors(ArrayList<String> listAuthors) {
        this.listAuthors = listAuthors;
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

    public ArrayList<String> getListActors() {
        return listActors;
    }

    public void setListActors(ArrayList<String> listActors) {
        this.listActors = listActors;
    }

    @Override
    public String toString() {
        return "Name = " + name + ".\nGender = " + gender + ".\nDuration = " + duration
                + ".\nAuthors = " + listAuthors + "\nActors = " + listActors + "\nDescription = "
                + description + ".\nChapters=" + listChapters;
    }

}
