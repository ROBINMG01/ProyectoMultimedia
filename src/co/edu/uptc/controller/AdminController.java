package co.edu.uptc.controller;

import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.util.FileManagement;
import co.edu.uptc.utilitaries.Utilitaries;

public class AdminController {

    private Movie movie;
    private Serie serie;
    private FileManagement FileManager;
    private ArrayList<Movie> listMovies;
    private ArrayList<Serie> listSeries;
    private ArrayList<String> listAuthors;
    private ArrayList<String> listActors;
    private ArrayList<String> listChaptersTwo;
    private ArrayList<String> namesMovies;
    private ArrayList<String> namesSeries;
    private ArrayList<String> namesSesons;
    private ArrayList<Season> listSeasons;
    private ArrayList<Chapter> listChapters;
    private Utilitaries utilitaries;
    private Season season;
    private Chapter chapter;

    // ROBIN
    public AdminController() {
        FileManager = new FileManagement();
        listMovies = new ArrayList<>();
        listSeries = new ArrayList<>();
        listAuthors = new ArrayList<>();
        listActors = new ArrayList<>();
        listChaptersTwo = new ArrayList<>();
        listSeasons = new ArrayList<>();
        listChapters = new ArrayList<>();
        utilitaries = new Utilitaries();
        listMovies = utilitaries.loadMovies();
        listSeries = utilitaries.loadSeries();
        movie = new Movie();
        serie = new Serie();
        season = new Season();
        chapter = new Chapter();
    }

    // ROBIN
    public boolean addMovie(String name, String description, int duration, ArrayList<String> listAuthors,
            String gender, ArrayList<String> listActors) {
        movie.setName(name);
        movie.setDescription(description);
        movie.setDuration(duration);
        movie.setlistAuthors(listAuthors);
        movie.setListActors(listActors);
        movie.setGender(gender);

        if (name.equals(movie.getName()) && duration == movie.getDuration()) {
            listMovies.add(new Movie(name, description, duration, listAuthors, listActors, gender));
            return true;
        }
        return false;

    }

    // ROBIN
    /*
     * public boolean addSerie(String name, String description, int duration,
     * ArrayList<String> listAuthors,
     * ArrayList<String> chapters, String gender, ArrayList<String> listActors,
     * String nameSeason,
     * String descriptionSeason, int index) {
     * serie.setName(name);
     * serie.setDescription(description);
     * serie.setDuration(duration);
     * serie.setlistAuthors(listAuthors);
     * serie.setGender(gender);
     * serie.setListActors(listActors);
     * 
     * if (name.equals(serie.getName()) && duration == serie.getDuration()
     * && addSeason(nameSeason, descriptionSeason, index)
     * && addChapter(name, duration)) {
     * listSeries.add(new Serie(name, description, duration, listAuthors,
     * listActors, gender, listSeason));
     * return true;
     * }
     * return false;
     * }
     */

    /*
     * public boolean addSerie(String name, String description, int duration,
     * ArrayList<String> listAuthors, String gender,
     * ArrayList<String> listActors, ArrayList<Season> listSeason, int index) {
     * 
     * Serie serie = new Serie();
     * serie.setName(name);
     * serie.setDescription(description);
     * serie.setDuration(duration);
     * serie.setlistAuthors(listAuthors);
     * serie.setGender(gender);
     * serie.setListActors(listActors);
     * 
     * for (Season season : listSeason) {
     * ArrayList<Chapter> chapters = new ArrayList<>();
     * for (Chapter chapter : season.getListChapters()) {
     * chapters.add(new Chapter(chapter.getName(), chapter.getDuration()));
     * }
     * serie.addSeason(new Season(season.getName(), season.getDescription(),
     * chapters));
     * }
     * 
     * listSeries.add(serie);
     * return true;
     * }
     */

    public boolean addSerie(String name, String description, int duration, ArrayList<String> listAuthors, String gender,
            ArrayList<String> listActors, String nameSeason, String descriptionSeason, String nameChapter,
            int durationChapter) {

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
        listSeason.add(new Season(nameSeason, descriptionSeason, chapters));

        listSeries.add(new Serie(name, description, duration, listAuthors, listActors, gender, listSeason));
        return true;
    }

    public boolean addSeason(int index, int index2, String nameSeason, String descriptionSeason, String nameChapter,
            int durationChapter) {

        Serie serie = listSeries.get(index);
        ArrayList<Season> listSeason = serie.getListSeason();
        ArrayList<Chapter> listChapter = new ArrayList<>();

        
        for (Season season : listSeason) {
            if (season.getName().equals(nameSeason)) {
                return false; // La temporada ya existe
            }
        }
        
        
        listChapter.add(new Chapter(nameChapter, durationChapter));
        
        season.setName(nameChapter);
        season.setDescription(descriptionSeason);
        season.setListChapters(listChapter);
        
        if (season.getName().equals(nameSeason) && season.getDescription().equals(descriptionSeason)) {
            // Si la temporada no existe, crear una nueva temporada
            Season newSeason = new Season(nameSeason, descriptionSeason, listChapter);
            listSeason.add(newSeason);
    
            // Actualizar la lista de temporadas en la serie
            serie.setListSeason(listSeason);
    
            // Actualizar la serie en la lista de series
            listSeries.set(index, serie);
            return true;
        }
        return false;
    }

    public boolean addChapter(int index, int index2, String nameChapter, int durationChapter) {

        Serie serie = listSeries.get(index);
        ArrayList<Season> listSeason = serie.getListSeason();
        Season season = listSeason.get(index2);
        ArrayList<Chapter> listChapter = season.getListChapters();

        for (Chapter chapter : listChapter) {
            if (chapter.getName().equals(nameChapter)) {
                return false; // El capítulo ya existe
            }
        }

        chapter.setName(nameChapter);
        chapter.setDuration(durationChapter);

        if (chapter.getName().equals(nameChapter) && chapter.getDuration()==durationChapter) {
            
            // Si el capítulo no existe, crear un nuevo capítulo
            Chapter newChapter = new Chapter(nameChapter, durationChapter);
            listChapter.add(newChapter);
    
            // Actualizar la lista de capítulos en la temporada
            season.setListChapters(listChapter);
    
            // Actualizar la temporada en la lista de temporadas
            listSeason.set(index2, season);
    
            // Actualizar la serie en la lista de series
            serie.setListSeason(listSeason);
    
            // Actualizar la serie en la lista de series
            listSeries.set(index, serie);
    
            return true;
        }
        return false;
    }

    // ROBIN
    public int searchMovie(String name) {
        for (int i = 0; i < listMovies.size(); i++) {
            if (listMovies.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    // ROBIN
    public int searchSeries(String name) {
        for (int i = 0; i < listSeries.size(); i++) {
            if (listSeries.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public int searchSeason(String nameSerie, String name) {
        for (int i = 0; i < listSeries.size(); i++) {
            if (listSeries.get(i).getName().equals(nameSerie)) {
                for (int j = 0; j < listSeries.get(i).getListSeason().size(); j++) {
                    if (listSeries.get(i).getListSeason().get(j).getName().equals(name)) {
                        return j;
                    }
                }
            }
        }
        return -1;
    }

    // ROBIN
    public boolean updateMovie(String name, String nameUpdate, String description, int duration,
            ArrayList<String> listAuthors, ArrayList<String> listActors, String gender) {
        if (searchMovie(name) != -1) {
            int position = searchMovie(name);
            listMovies.get(position).setName(nameUpdate);
            listMovies.get(position).setDescription(description);
            listMovies.get(position).setDuration(duration);
            listMovies.get(position).setlistAuthors(listAuthors);
            listMovies.get(position).setListActors(listActors);
            listMovies.get(position).setGender(gender);
            return true;
        }
        return false;
    }

    // ROBIN
    public boolean updateSeries(String name, String nameUpdate, String description, int duration,
            ArrayList<String> listAuthors, ArrayList<String> chapters, String gender, ArrayList<String> listActors) {
        if (searchSeries(name) != -1) {
            int position = searchSeries(name);
            listSeries.get(position).setName(nameUpdate);
            listSeries.get(position).setDescription(description);
            listSeries.get(position).setDuration(duration);
            listSeries.get(position).setlistAuthors(listAuthors);
            // listSeries.get(position).setListChapters(chapters);
            listSeries.get(position).setGender(gender);
            listSeries.get(position).setListActors(listActors);
            return true;
        }
        return false;
    }

    // ROBIN
    public ArrayList<Movie> showListMovies() {
        return listMovies;
    }

    // ROBIN
    public ArrayList<Serie> showListSeries() {
        return listSeries;
    }

    public void addlistAuthors(String name) {
        listAuthors.add(name);
    }

    public void addlistActors(String name) {
        listActors.add(name);
    }

    // ROBIN
    public void addListChapters(String chapter) {
        listChaptersTwo.add(chapter);
    }

    public ArrayList<String> showlistAuthors() {
        return listAuthors;
    }

    public ArrayList<String> showlistActors() {
        return listActors;
    }

    // ROBIN
    public ArrayList<String> showListChaptersTwo() {
        return listChaptersTwo;
    }

    // ROBIN
    public ArrayList<String> namesMovies() {
        namesMovies = new ArrayList<>();
        for (int i = 0; i < listMovies.size(); i++) {
            namesMovies.add(listMovies.get(i).getName());
        }
        return namesMovies;
    }

    // ROBIN
    public ArrayList<String> namesSeries() {
        namesSeries = new ArrayList<>();
        for (int i = 0; i < listSeries.size(); i++) {
            namesSeries.add(listSeries.get(i).getName());
        }
        return namesSeries;
    }

    public ArrayList<String> namesSesons(int position) {
        namesSesons = new ArrayList<>();
        for (int i = 0; i < listSeries.get(position).getListSeason().size(); i++) {
            namesSesons.add(listSeries.get(position).getListSeason().get(i).getName());
        }
        return namesSesons;
    }

    public ArrayList<Movie> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<Movie> listMovies) {
        this.listMovies = listMovies;
    }

    public ArrayList<Serie> getListSeries() {
        return listSeries;
    }

    public void setListSeries(ArrayList<Serie> listSeries) {
        this.listSeries = listSeries;
    }

    public boolean deleteMovie(String name) {
        for (int i = 0; i < listMovies.size(); i++) {
            if (listMovies.get(i).getName().equals(name)) {
                listMovies.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean deleteSerie(String name) {
        for (int i = 0; i < listMovies.size(); i++) {
            if (listSeries.get(i).getName().equals(name)) {
                listSeries.remove(i);
                return true;
            }
        }
        return false;
    }

    public void saveMovie(List<Movie> listMovies, String file) {
        FileManager.writeJsonToFile(file, listMovies);
    }

    public List<Movie> loadMovie(String file) {
        return FileManager.loadMoviesFromJson(file);
    }
}