package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;

public class UserController {
    private Serie serie;
    private Chapter chapter;
    private Season season;
    private ArrayList<Movie> listMovies;
    private ArrayList<Serie> listSeries;
    private boolean exit;
    private ArrayList<Movie> favoriteMovies;
    private ArrayList<Serie> favoriteSeries;

    public UserController(AdminController ad) {
        listMovies = new ArrayList<>();
        listSeries = new ArrayList<>();
        exit = true;
        favoriteMovies = new ArrayList<>();
        favoriteSeries = new ArrayList<>();
    }

    public boolean addMovie(String name, String description, int duration,
            ArrayList<String> authors, ArrayList<String> actors, String gender, String imageUrl) {
        Movie movie = new Movie(name, description, duration, authors, actors, gender, imageUrl);
        listMovies.add(movie);

        return true;
    }

    public boolean addSerie(String name, String description, int duration, ArrayList<String> listAuthors, String gender,
            ArrayList<String> listActors, String nameSeason, String descriptionSeason, String nameChapter,
            int durationChapter, String file, String imageUrl) {

        ArrayList<Chapter> chapters = new ArrayList<>();
        ArrayList<Season> listSeason = new ArrayList<>();
        serie.setName(name);
        serie.setDescription(description);
        serie.setDuration(duration);
        serie.setlistAuthors(listAuthors);
        serie.setGender(gender);
        serie.setListActors(listActors);

        chapter.setName(nameChapter);
        chapter.setDuration(durationChapter);

        chapters.add(new Chapter(nameChapter, durationChapter));
        season.setName(nameSeason);
        season.setDescription(descriptionSeason);
        season.setListChapters(chapters);

        if (serie.getName().equals(name) && serie.getDescription().equals(description)) {
            listSeason.add(new Season(nameSeason, descriptionSeason, chapters));
            listSeries
                    .add(new Serie(name, description, duration, listAuthors, listActors, gender, listSeason, imageUrl));
            return true;
        }
        return false;
    }

    public ArrayList<Movie> showListMovies() {
        return listMovies;
    }

    public ArrayList<Serie> showListSeries() {
        return listSeries;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public ArrayList<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public ArrayList<Serie> getFavoriteSeries() {
        return favoriteSeries;
    }

}
