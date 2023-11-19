package co.edu.uptc.controller;

import java.util.ArrayList;

import javax.security.auth.Subject;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class AdminController {
   // private String id;
    private Movie movie;
    private Serie serie;
    private ArrayList<Movie> listMovies;
    private ArrayList<Serie> listSeries;
    private ArrayList<String> listActors;

    //private Movie movie1 = new Movie(); for class abstract
    //private Serie serie1 = new Serie(); for class abstract

    public AdminController() {
        listMovies = new ArrayList<>();
        listSeries = new ArrayList<>();
        listActors = new ArrayList<>();
        //listCharapter = new ArrayList<>();
        loadMovies();
        loadSeries();
        movie = new Movie();
        serie = new Serie();
    }

    public void loadMovies(){
        listMovies.add(new Movie("Avengers: Endgame","Nuestros queridos héroes entenderán realmente cuán frágil es esta realidad y los sacrificios que deben hacerse para defenderla", "3h", "Robert, Hemworth, Carolina"));
        listMovies.add(new Movie("El caballero de la noche","una nueva entrega de nuestro superhéroe enmascarado, quien con una dosis inmensa de acción y drama nos promete atraparnos con cada escena a lo largo del film", "2h 32m", "Christian Bale, Heath Ledger, Michael Caine"));
        listMovies.add(new Movie("El origen","Dom Cobb (DiCaprio) es un experto en el arte de apropiarse, durante el sueño, de los secretos del subconsciente ajeno", "1202", "DiCaprio, Joseph Gordon-Levitt, Tom Hardy"));
        listMovies.add(new Movie("Tiempos Violentos","Tiempos Violentos narra tres historias intercaladas, cargadas de violencia y humor negro, con un prólogo y un epílogo", "2h 30m", "John Travolta, Uma Thurman, Samuel L. Jackson"));
        listMovies.add(new Movie("Fight Club","Antes de su tan anunciado regreso cuando Ivan Drago en 'Creed 2' Dolph Lundgren se entrena un poco mientras ayuda a Rebecca, una luchadora femenina interpretada por Amy Johnston, a regresar al ring después de una brutal tragedia", "1h 30m", " Amy Johnston, Cortney Palm, Rey Goyos"));
        listMovies.add(new Movie("Sueños de Libertad","Acusado del asesinato de su mujer, Andrew Dufresne, tras ser condenado a cadena perpetua, es enviado a la prisión de Shawshank", "2h 22m", "Morgan Freeman, Gene Hackman, Robert Duvall"));
        listMovies.add(new Movie("El Padrino","América, años 40. Don Vito Corleone (Marlon Brando) es el respetado y temido jefe de una de las cinco familias de la mafia de Nueva York", "2h 55m", "Marlon Brando, Al Pacino, Robert Duvall"));
        listMovies.add(new Movie("Interstellar","Inspirada en la teoría del experto en relatividad Kip Stepehen Thorne sobre la existencia de los agujeros de gusano, y su función como canal para llevar a cabo los viajes en el tiempo", "2h 49m", "Matthew McConaughey, Anne Hathaway, Jessica"));
        listMovies.add(new Movie("Matrix"," La película es cinética, atmosférica, visualmente impresionante y alucinante", "2h 16m", "Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss"));
    }

    public void loadSeries(){
        listSeries.add(new Serie("Loki","Loki es llevado ante la misteriosa organización llamada AVT (Autoridad de Variación Temporal) después de los acontecimientos ocurridos en Avengers: Endgame (2019) y se le da a elegir enfrentarse a ser borrado de la existencia debido a que es una variante de tiempo o ayudar a arreglar la línea de tiempo y detener una amenaza mayor.", "45 m Capitulo", "Andrew, Monica, Robert", "Capitulo1, Capitulo2, Capitulo3, Capitulo4, Capitulo5, Capitulo6"));
        listSeries.add(new Serie("Strange Things","Es una serie de televisión dramática de misterio que está ambientada en una localidad de Indiana", "1h Capitulo", "Marthe, Bill, Carlos", "Capitulo1, Capitulo2, Capitulo3"));
    }

    /*
     * Method for class abstract
    public void addMoviee(String name, String description, String duration, String actors){
        movie1.setName(name);
        movie1.setDescription(description);
        movie1.setDuration(duration);
        movie1.setListActors(actors);


    }
    
    Method for class abstract
    public void addSeriee(String name, String description, String duration, String actors, String chapters){
        serie1.setName(name);
        serie1.setDescription(description);
        serie1.setDuration(duration);
        serie1.setListActors(actors);
        serie1.setListChapters(chapters);
    }

    public String showMovie(){
        return movie1.toString();
    }

    public String showSerie(){
        return serie1.toString();
    }*/

    public boolean addMovie(String name, String description, String duration, String actors){
        movie.setName(name);
        movie.setDescription(description);
        movie.setDuration(duration);
        movie.setListActors(actors);
        
        if (name.equals(movie.getName()) && duration == movie.getDuration()) {
            listMovies.add(new Movie(name, description, duration, actors));
            return true;
        }
        return false;
    }

    public boolean addSerie(String name, String description, String duration, String actors, String chapters){
        serie.setName(name);
        serie.setDescription(description);
        serie.setDuration(duration);
        serie.setListActors(actors);
        serie.setListChapters(chapters);

        
        if (name.equals(serie.getName()) && duration == serie.getDuration()) {
            listSeries.add(new Serie(name, description, duration, actors, chapters));
            return true;
        }
        return false;
    }

    public int searchMovie(String name){
        for (int i = 0; i < listMovies.size(); i++) {
            if(listMovies.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public int searchSeries(String name){
        for (int i = 0; i < listSeries.size(); i++) {
            if(listSeries.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public boolean updateMovie(String name, String nameUpdate, String description, String duration, String actors){
        if (searchMovie(name)!= -1) {
            int position = searchMovie(name);
            listMovies.get(position).setName(nameUpdate);
            listMovies.get(position).setDescription(description);
            listMovies.get(position).setDuration(duration);
            listMovies.get(position).setListActors(actors);
            return true;
        }
        return false;
    }

    public boolean updateSeries(String name, String nameUpdate, String description, String duration, String actors, String chapters){
        if (searchSeries(name)!= -1) {
            int position = searchSeries(name);
            listSeries.get(position).setName(nameUpdate);
            listSeries.get(position).setDescription(description);
            listSeries.get(position).setDuration(duration);
            listSeries.get(position).setListActors(actors);
            listSeries.get(position).setListChapters(chapters);
            return true;
        }
        return false;
    }

    public ArrayList<Movie> showListMovies(){
        return listMovies;
    }

    public ArrayList<Serie> showListSeries(){
        return listSeries;
    }
}
