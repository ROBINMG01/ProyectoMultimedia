package co.edu.uptc.utilitaries;

import java.util.ArrayList;
import java.util.Arrays;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class Utilitaries {

        private ArrayList<Movie> listMovies;
        private ArrayList<Serie> listSeries;

        public Utilitaries() {
                listMovies = new ArrayList<>();
                listSeries = new ArrayList<>();
        }

        public ArrayList<Movie> loadMovies() {

                listMovies.add(new Movie("Avengers: Endgame", "Los vengadores defenderan la realidad", 195,
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")),
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")), "Ficcion"));
                listMovies.add(new Movie("Avengers: Endgame", "Los vengadores defenderan la realidad", 120,
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")),
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")), "Ficcion"));
                listMovies.add(new Movie("El caballero de la noche",
                                "una nueva entrega de nuestro superhéroe enmascarado", 120,
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")),
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")), "Ficcion"));
                listMovies.add(new Movie("El origen", "Dom Cobb (DiCaprio) es un experto en el arte de apropiarse", 120,
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")),
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")), "Ficcion"));
                listMovies.add(new Movie("Tiempos Violentos", "Tiempos Violentos narra tres historias intercaladas",
                                120, new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")),
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")), "Ficcion"));
                listMovies.add(new Movie("Fight Club", "Dolph Lundgren se entrena un poco mientras ayuda a Rebecca",
                                120, new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")),
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")), "Ficcion"));
                listMovies.add(new Movie("Sueños de Libertad", "Andrew Dufresne es acusado del asesinato de su mujer",
                                120, new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")),
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")), "Ficcion"));
                listMovies.add(new Movie("El Padrino", "América, años 40", 120,
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")),
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")), "Ficcion"));
                listMovies.add(new Movie("Interstellar",
                                "Inspirada en la teoría del experto en relatividad Kip Stepehen Thorne",
                                120, new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")),
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")), "Ficcion"));
                listMovies.add(new Movie("Matrix",
                                " La película es cinética, atmosférica, visualmente impresionante y alucinante",
                                120, new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")),
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")), "Ficcion"));
                return listMovies;
        }

        public ArrayList<Serie> loadSeries() {
                listSeries.add(new Serie("Loki", "Loki es llevado ante la misteriosa organización llamada AVT", 120,
                                new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina")),
                                new ArrayList<>(Arrays.asList("Tiem´po", "Busqueda", "Contralejo")),
                                "Marvel", new ArrayList<>(Arrays.asList("Tiem´po", "Busqueda", "Contralejo"))));
                listSeries.add(new Serie("Strange Things", "Es una serie de televisión dramática de misterio", 45,
                                new ArrayList<>(Arrays.asList("Marthe", "Bill", "Carlos")),
                                new ArrayList<>(Arrays.asList("Capitulo1, Capitulo2, Capitulo3")),
                                "Ficcion", new ArrayList<>(Arrays.asList("Marthe", "Bill", "Carlos"))));
                return listSeries;
        }
}
