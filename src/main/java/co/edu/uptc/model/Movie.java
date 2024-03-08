package co.edu.uptc.model;

import java.util.ArrayList;

import javafx.collections.ObservableList;

public class Movie {
    private String name;
    private String gender;
    private int duration;
    private ObservableList<Movie> listMovies;
    private ObservableList<String> listAuthors;
    private ObservableList<String> listActors;
    private String description;
    private String director;
    private int year;
    private String gener;

    public String getDirector() {
        return director;
    }



    public void setDirector(String director) {
        this.director = director;
    }



    public int getYear() {
        return year;
    }



    public void setYear(int year) {
        this.year = year;
    }



    public String getGener() {
        return gener;
    }



    public void setGener(String gener) {
        this.gener = gener;
    }



    public Movie() {
    }

     

    public Movie(String name, String description, int duration, ObservableList<String> listAuthors,
    ObservableList<String> listActors, String gender) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.listAuthors = listAuthors;
        this.listActors = listActors;
        this.gender = gender;
    }

    public Movie(String name, String description, int duration, ObservableList<String> listAuthors,
    ObservableList<String> listActors, String gender, int year) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.listAuthors = listAuthors;
        this.listActors = listActors;
        this.gender = gender;
        this.year = year;
    }

    public Movie(String name, String gender, int duration, int year) {
        this.name = name;
        this.gender = gender;
        this.duration = duration;
        this.year = year;
    }

    public Movie(String name, String director, int year, String gender) {
        this.name = name;
        this.gender = gender;
        this.director = director;
        this.year = year;
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

    public ObservableList<String> getlistAuthors() {
        return listAuthors;
    }

    public void setlistAuthors(ObservableList<String> listAuthors) {
        this.listAuthors = listAuthors;
    }

    public ObservableList<Movie> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ObservableList<Movie> listMovies) {
        this.listMovies = listMovies;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ObservableList<String> getListActors() {
        return listActors;
    }

    public void setListActors(ObservableList<String> listActors) {
        this.listActors = listActors;
    }

    @Override
    public String toString() {
        return "Name = " + name + ".\nGender = " + gender + ".\nDuration = " + duration
                + ".\nAuthors = " + listAuthors + "\nActors = " + listActors
                + "\nDescription = " + description;
    }
    
}
