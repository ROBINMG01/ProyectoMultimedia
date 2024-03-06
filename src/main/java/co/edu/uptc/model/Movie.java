package co.edu.uptc.model;

import java.util.ArrayList;

public class Movie {
    private String name;
    private String gender;
    private int duration;
    private ArrayList<Movie> listMovies;
    private ArrayList<String> listAuthors;
    private ArrayList<String> listActors;
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

     

    public Movie(String name, String description, int duration, ArrayList<String> listAuthors,
            ArrayList<String> listActors, String gender) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.listAuthors = listAuthors;
        this.listActors = listActors;
        this.gender = gender;
    }

    public Movie(String name, String director, int year, String gener) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.gener = gener;
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

    public ArrayList<String> getlistAuthors() {
        return listAuthors;
    }

    public void setlistAuthors(ArrayList<String> listAuthors) {
        this.listAuthors = listAuthors;
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

    public ArrayList<String> getListActors() {
        return listActors;
    }

    public void setListActors(ArrayList<String> listActors) {
        this.listActors = listActors;
    }

    @Override
    public String toString() {
        return "Name = " + name + ".\nGender = " + gender + ".\nDuration = " + duration
                + ".\nAuthors = " + listAuthors + "\nActors = " + listActors
                + "\nDescription = " + description;
    }
    
}
