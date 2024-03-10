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
    private ArrayList<Season> listSeason;
<<<<<<< HEAD
    private String imageUrl;
    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
=======
    private String videoUrl;
    private String imageUrl;
>>>>>>> feature/JavaFxCreate

    public Serie() {
    }

    public Serie(String name, String description, int duration, ArrayList<String> listAuthors,
            ArrayList<String> listActors, String gender, ArrayList<Season> listSeasons, String imageUrl, int year) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.gender = gender;
        this.listAuthors = listAuthors;
        this.listActors = listActors;
        this.listSeason = listSeasons;
        this.imageUrl = imageUrl;
        this.year = year;
    }

    public void addSeason(Season season) {
        this.listSeason.add(season);
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

    public ArrayList<Season> getListSeason() {
        return listSeason;
    }

    public void setListSeason(ArrayList<Season> listSeason) {
        this.listSeason = listSeason;
    }
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nGender: " + gender + "\nDuration: " + duration + "\nListAuthors: " + listAuthors
                + "\nListActors: " + listActors + "\nDescription: " + description + "\nListSeason: " + listSeason + "]";
    }
}
