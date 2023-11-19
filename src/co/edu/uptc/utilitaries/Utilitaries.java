package co.edu.uptc.utilitaries;

import java.util.ArrayList;

public class Utilitaries {
    private ArrayList<String> movieCatalog;
    private ArrayList<String> seriesCatalog;

    public Utilitaries() {
        movieCatalog = new ArrayList<>();
        seriesCatalog = new ArrayList<>();
        initializeMovieCatalog();
        initializeSeriesCatalog();
    }

    private void initializeMovieCatalog() {
        movieCatalog.add("Avengers: Endgame (2019, Action/Adventure)");
        movieCatalog.add("The Dark Knight (2008, Action/Crime)");
        movieCatalog.add("Inception (2010, Action/Science fiction)");
        movieCatalog.add("Pulp Fiction (1994, Crime/Drama)");
        movieCatalog.add("Fight Club (1999, Drama/Thriller)");
        movieCatalog.add("The Shawshank Redemption (1994, Drama/Crime)");
        movieCatalog.add("The Godfather (1972, Drama/Crime)");
        movieCatalog.add("Interstellar (2014, Drama/Science fiction)");
        movieCatalog.add("The Matrix (1999, Action/Science fiction)");
        movieCatalog.add("Forrest Gump (1994, Drama/Comedy)");
        movieCatalog.add("The Lord of the Rings: The Fellowship of the Ring (2001, Adventure/Fantasy)");
        movieCatalog.add("The Lion King (1994, Animation/Adventure)");
        movieCatalog.add("Titanic (1997, Drama/Romance)");
        movieCatalog.add("Star Wars: Episode IV - A New Hope (1977, Action/Adventure)");
        movieCatalog.add("Jurassic Park (1993, Adventure/Science fiction)");
    }

    private void initializeSeriesCatalog() {
        seriesCatalog.add("Stranger Things (2016, Drama/Fantasy)");
        seriesCatalog.add("Breaking Bad (2008, Drama/Crime)");
        seriesCatalog.add("Friends (1994, Comedy)");
        seriesCatalog.add("Game of Thrones (2011, Drama/Fantasy)");
        seriesCatalog.add("The Office (2005, Comedy)");
        seriesCatalog.add("The Crown (2016, Drama/Historical)");
        seriesCatalog.add("Narcos (2015, Drama/Crime)");
        seriesCatalog.add("Black Mirror (2011, Drama/Science fiction)");
        seriesCatalog.add("The Big Bang Theory (2007, Comedy)");
        seriesCatalog.add("Sherlock (2010, Drama/Crime)");
        seriesCatalog.add("Peaky Blinders (2013, Drama/Crime)");
        seriesCatalog.add("The Walking Dead (2010, Drama/Horror)");
        seriesCatalog.add("Money Heist (2017, Drama/Crime)");
        seriesCatalog.add("The Witcher (2019, Drama/Fantasy)");
        seriesCatalog.add("The Mandalorian (2019, Drama/Science fiction)");
    }

    public ArrayList<String> getMovieCatalog() {
        return movieCatalog;
    }

    public ArrayList<String> getSeriesCatalog() {
        return seriesCatalog;
    }
}
