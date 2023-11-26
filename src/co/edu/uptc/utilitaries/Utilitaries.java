package co.edu.uptc.utilitaries;

import java.util.ArrayList;
import java.util.Arrays;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class Utilitaries {
    private ArrayList<Movie> movieCatalog;
    private ArrayList<Serie> seriesCatalog;

    public Utilitaries() {
        movieCatalog = new ArrayList<>();
        seriesCatalog = new ArrayList<Serie>();
         MovieCatalog();
    }
 
    private void MovieCatalog() {
        movieCatalog.add(new Movie("Avengers: Endgame", "Nuestros queridos héroes entenderán realmente cuán frágil es esta realidad y los sacrificios que deben hacerse para defenderla", 180, new ArrayList<>(Arrays.asList("Robert", "Hemworth", "Carolina"))));
        movieCatalog.add(new Movie("The Dark Knight", "Una nueva entrega de nuestro superhéroe enmascarado, quien con una dosis inmensa de acción y drama nos promete atraparnos con cada escena a lo largo del film", 152, new ArrayList<>(Arrays.asList("Christian Bale", "Heath Ledger", "Michael Caine"))));
        movieCatalog.add(new Movie("Inception", "Dom Cobb (DiCaprio) es un experto en el arte de apropiarse, durante el sueño, de los secretos del subconsciente ajeno", 120, new ArrayList<>(Arrays.asList("DiCaprio", "Joseph Gordon-Levitt", "Tom Hardy"))));
        movieCatalog.add(new Movie("Pulp Fiction", "Tiempos Violentos narra tres historias intercaladas, cargadas de violencia y humor negro, con un prólogo y un epílogo", 150, new ArrayList<>(Arrays.asList("John Travolta", "Uma Thurman", "Samuel L. Jackson"))));
        movieCatalog.add(new Movie("Fight Club", "Antes de su tan anunciado regreso cuando Ivan Drago en 'Creed 2' Dolph Lundgren se entrena un poco mientras ayuda a Rebecca, una luchadora femenina interpretada por Amy Johnston, a regresar al ring después de una brutal tragedia", 90, new ArrayList<>(Arrays.asList("Amy Johnston", "Cortney Palm", "Rey Goyos"))));
        movieCatalog.add(new Movie("The Shawshank Redemption", "Acusado del asesinato de su mujer, Andrew Dufresne, tras ser condenado a cadena perpetua, es enviado a la prisión de Shawshank", 142, new ArrayList<>(Arrays.asList("Morgan Freeman", "Gene Hackman", "Robert Duvall"))));
        movieCatalog.add(new Movie("The Godfather", "América, años 40. Don Vito Corleone (Marlon Brando) es el respetado y temido jefe de una de las cinco familias de la mafia de Nueva York", 175, new ArrayList<>(Arrays.asList("Marlon Brando", "Al Pacino", "Robert Duvall"))));
        movieCatalog.add(new Movie("Interstellar", "Inspirada en la teoría del experto en relatividad Kip Stepehen Thorne sobre la existencia de los agujeros de gusano, y su función como canal para llevar a cabo los viajes en el tiempo", 169, new ArrayList<>(Arrays.asList("Matthew McConaughey", "Anne Hathaway", "Jessica"))));
        movieCatalog.add(new Movie("The Matrix", "La película es cinética, atmosférica, visualmente impresionante y alucinante", 136, new ArrayList<>(Arrays.asList("Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss"))));
    }

    private void  SeriesCatalog() {
        seriesCatalog.add(new Serie("Stranger Things", "Una serie de televisión dramática de misterio que está ambientada en una localidad de Indiana", new ArrayList<>(Arrays.asList("Drama", "Fantasy"))));
        seriesCatalog.add(new Serie("Breaking Bad", "Un profesor de química de secundaria convertido en fabricante y vendedor de metanfetaminas", new ArrayList<>(Arrays.asList("Drama", "Crime"))));
        seriesCatalog.add(new Serie("Friends", "Un grupo de amigos en la ciudad de Nueva York que enfrentan situaciones cómicas y emocionales juntos", new ArrayList<>(Arrays.asList("Comedy"))));
        seriesCatalog.add(new Serie("Game of Thrones", "Una serie épica que sigue las luchas de poder entre las familias nobles en los Siete Reinos de Westeros", new ArrayList<>(Arrays.asList("Drama", "Fantasy"))));
        seriesCatalog.add(new Serie("The Office", "Una comedia que sigue la vida de los empleados de una oficina de papel en Scranton, Pennsylvania", new ArrayList<>(Arrays.asList("Comedy"))));
        seriesCatalog.add(new Serie("The Crown", "Una serie que sigue la vida de la Reina Isabel II y los eventos históricos que ocurrieron durante su reinado", new ArrayList<>(Arrays.asList("Drama", "Historical"))));
        seriesCatalog.add(new Serie("Narcos", "Una serie que narra la historia del auge y caída del narcotraficante colombiano Pablo Escobar", new ArrayList<>(Arrays.asList("Drama", "Crime"))));
        seriesCatalog.add(new Serie("Black Mirror", "Una serie de antología que explora la relación entre la tecnología y la sociedad", new ArrayList<>(Arrays.asList("Drama", "Science fiction"))));
        seriesCatalog.add(new Serie("The Big Bang Theory", "Una comedia que sigue las vidas de un grupo de amigos nerds y su interacción con el mundo exterior", new ArrayList<>(Arrays.asList("Comedy"))));
        seriesCatalog.add(new Serie("Sherlock", "Una serie que sigue las aventuras del famoso detective Sherlock Holmes y su compañero John Watson", new ArrayList<>(Arrays.asList("Drama", "Crime"))));
        seriesCatalog.add(new Serie("Peaky Blinders", "Una serie que sigue a la familia criminal Shelby en la ciudad de Birmingham, Inglaterra, después de la Primera Guerra Mundial", new ArrayList<>(Arrays.asList("Drama", "Crime"))));
        seriesCatalog.add(new Serie("The Walking Dead", "Una serie que sigue a un grupo de sobrevivientes en un mundo post-apocalíptico lleno de zombies", new ArrayList<>(Arrays.asList("Drama", "Horror"))));
        seriesCatalog.add(new Serie("Money Heist", "Una serie que sigue a un grupo de criminales que planean y ejecutan el atraco perfecto a la Casa de la Moneda de España", new ArrayList<>(Arrays.asList("Drama", "Crime"))));
        seriesCatalog.add(new Serie("The Witcher", "Una serie basada en la saga de libros y videojuegos del mismo nombre, que sigue las aventuras del cazador de monstruos Geralt de Rivia", new ArrayList<>(Arrays.asList("Drama", "Fantasy"))));
        seriesCatalog.add(new Serie("The Mandalorian", "Una serie ambientada en el universo de Star Wars, que sigue las aventuras de un cazarrecompensas en los confines de la galaxia", new ArrayList<>(Arrays.asList("Drama", "Science fiction"))));
        seriesCatalog.add(new Serie("Loki", "Loki es llevado ante la misteriosa organización llamada AVT (Autoridad de Variación Temporal) después de los acontecimientos ocurridos en Avengers: Endgame (2019) y se le da a elegir enfrentarse a ser borrado de la existencia debido a que es una variante de tiempo o ayudar a arreglar la línea de tiempo y detener una amenaza mayor.", new ArrayList<>(Arrays.asList("Drama", "Science fiction"))));
        seriesCatalog.add(new Serie("Strange Things", "Es una serie de televisión dramática de misterio que está ambientada en una localidad de Indiana", new ArrayList<>(Arrays.asList("Drama", "Fantasy"))));
    }

    public ArrayList<Movie> getMovieCatalog() {
        return movieCatalog;
    }

    public ArrayList<Serie> getSeriesCatalog() {
        return seriesCatalog;
    }
}
