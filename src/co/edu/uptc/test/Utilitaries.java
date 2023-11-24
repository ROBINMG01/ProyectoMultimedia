package co.edu.uptc.test;

import java.util.ArrayList;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class Utilitaries {

    private ArrayList<Movie> listMovies;
    private ArrayList<Serie> listSeries;

    
    public Utilitaries() {
        listMovies = new ArrayList<>();
        listSeries = new ArrayList<>();
    }

    public void loadMovies(){
        listMovies.add(new Movie("Avengers: Endgame","Los vengadores defenderan la realidad", "3h", "Robert, Hemwoth, Carolina", "Ficción"));
        listMovies.add(new Movie("El caballero de la noche","una nueva entrega de nuestro superhéroe enmascarado", "2h 32m", "Christian Bale, Heath Ledger, Michael Caine", "Acción"));
        listMovies.add(new Movie("El origen","Dom Cobb (DiCaprio) es un experto en el arte de apropiarse", "1202", "DiCaprio, Joseph Gordon-Levitt, Tom Hardy", "Ficción"));
        listMovies.add(new Movie("Tiempos Violentos","Tiempos Violentos narra tres historias intercaladas", "2h 30m", "John Travolta, Uma Thurman, Samuel L. Jackson", "Aventura"));
        listMovies.add(new Movie("Fight Club","Dolph Lundgren se entrena un poco mientras ayuda a Rebecca", "1h 30m", " Amy Johnston, Cortney Palm, Rey Goyos", "Acción"));
        listMovies.add(new Movie("Sueños de Libertad","Andrew Dufresne es acusado del asesinato de su mujer", "2h 22m", "Morgan Freeman, Gene Hackman, Robert Duvall", "Aventura"));
        listMovies.add(new Movie("El Padrino","América, años 40", "2h 55m", "Marlon Brando, Al Pacino, Robert Duvall", "Acción"));
        listMovies.add(new Movie("Interstellar","Inspirada en la teoría del experto en relatividad Kip Stepehen Thorne", "2h 49m", "Matthew McConaughey, Anne Hathaway, Jessica", "Ficción"));
        listMovies.add(new Movie("Matrix"," La película es cinética, atmosférica, visualmente impresionante y alucinante", "2h 16m", "Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss", "Ficción"));
    }

        public void loadSeries(){
        listSeries.add(new Serie("Loki","Loki es llevado ante la misteriosa organización llamada AVT", "45 m Capitulo", "Andrew, Monica, Robert", "Capitulo1, Capitulo2, Capitulo3, Capitulo4, Capitulo5, Capitulo6", "Ficción"));
        listSeries.add(new Serie("Strange Things","Es una serie de televisión dramática de misterio", "1h Capitulo", "Marthe, Bill, Carlos", "Capitulo1, Capitulo2, Capitulo3", "Drama"));
    }
}
