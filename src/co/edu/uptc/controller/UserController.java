package co.edu.uptc.controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserController {
    public static void showMovieCatalog() {
        // Catálogo de películas con año de estreno y género.
        ArrayList<String> movies = new ArrayList<>();
        movies.add("Avengers: Endgame (2019, Action/Adventure)");
        movies.add("The Dark Knight (2008, Action/Crime)");
        movies.add("Inception (2010, Action/Science fiction)");
        movies.add("Pulp Fiction (1994, Crime/Drama)");
        movies.add("Fight Club (1999, Drama/Thriller)");
        movies.add("The Shawshank Redemption (1994, Drama/Crime)");
        movies.add("The Godfather (1972, Drama/Crime)");
        movies.add("Interstellar (2014, Drama/Science fiction)");
        movies.add("The Matrix (1999, Action/Science fiction)");
        movies.add("Forrest Gump (1994, Drama/Comedy)");
        movies.add("The Lord of the Rings: The Fellowship of the Ring (2001, Adventure/Fantasy)");
        movies.add("The Lion King (1994, Animation/Adventure)");
        movies.add("Titanic (1997, Drama/Romance)");
        movies.add("Star Wars: Episode IV - A New Hope (1977, Action/Adventure)");
        movies.add("Jurassic Park (1993, Adventure/Science fiction)");

        // Muestra el catálogo de películas en un cuadro de diálogo
        String selectedMovie = (String) JOptionPane.showInputDialog(null, "Select a movie:",
                "Movie Catalog", JOptionPane.PLAIN_MESSAGE, null, movies.toArray(), movies.get(0));

        // Comprobar si una película fue seleccionada o cancelada
        if (selectedMovie != null) {
            // Muestra la película seleccionada en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, "You have selected the movie: " + selectedMovie);
        } else {
            // Mostrar mensaje de salida
            JOptionPane.showMessageDialog(null, "You have exited the movie catalog option.");
        }
    }


    public static void showSeriesCatalog() {
        // Catálogo de series con año de lanzamiento y género.
        ArrayList<String> series = new ArrayList<>();
        series.add("Stranger Things (2016, Drama/Fantasy)");
        series.add("Breaking Bad (2008, Drama/Crime)");
        series.add("Friends (1994, Comedy)");
        series.add("Game of Thrones (2011, Drama/Fantasy)");
        series.add("The Office (2005, Comedy)");
        series.add("The Crown (2016, Drama/Historical)");
        series.add("Narcos (2015, Drama/Crime)");
        series.add("Black Mirror (2011, Drama/Science fiction)");
        series.add("The Big Bang Theory (2007, Comedy)");
        series.add("Sherlock (2010, Drama/Crime)");
        series.add("Peaky Blinders (2013, Drama/Crime)");
        series.add("The Walking Dead (2010, Drama/Horror)");
        series.add("Money Heist (2017, Drama/Crime)");
        series.add("The Witcher (2019, Drama/Fantasy)");
        series.add("The Mandalorian (2019, Drama/Science fiction)");

        // Muestra el catálogo de series en un cuadro de diálogo
        String selectedSeries = (String) JOptionPane.showInputDialog(null, "Select a series:",
                "Series Catalog", JOptionPane.PLAIN_MESSAGE, null, series.toArray(), series.get(0));

        // Comprobar si una serie fue seleccionada o cancelada
        if (selectedSeries != null) {
            // Muestra la serie seleccionada en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, "You have selected the series: " + selectedSeries);
        } else {
            // Mostrar mensaje de salida
            JOptionPane.showMessageDialog(null, "You have exited the series catalog option.");
        }
    }

    public static void searchSeries() {
        // Catálogo de series con año de lanzamiento y género.
        ArrayList<String> series = new ArrayList<>();
        series.add("Stranger Things (2016, Drama/Fantasy)");
        series.add("Breaking Bad (2008, Drama/Crime)");
        series.add("Friends (1994, Comedy)");
        series.add("Game of Thrones (2011, Drama/Fantasy)");
        series.add("The Office (2005, Comedy)");
        series.add("The Crown (2016, Drama/Historical)");
        series.add("Narcos (2015, Drama/Crime)");
        series.add("Black Mirror (2011, Drama/Science fiction)");
        series.add("The Big Bang Theory (2007, Comedy)");
        series.add("Sherlock (2010, Drama/Crime)");
        series.add("Peaky Blinders (2013, Drama/Crime)");
        series.add("The Walking Dead (2010, Drama/Horror)");
        series.add("Money Heist (2017, Drama/Crime)");
        series.add("The Witcher (2019, Drama/Fantasy)");
        series.add("The Mandalorian (2019, Drama/Science fiction)");

        // Opciones de búsqueda
        String[] options = {"Search by name", "Search by genre", "Search by release year"};

        // Muestra las opciones de búsqueda en un cuadro de diálogo
        String selectedOption =
                (String) JOptionPane.showInputDialog(null, "Select a search option:",
                        "Series Search", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        // Comprobar si se seleccionó "Cancelar" en el cuadro de diálogo
        if (selectedOption == null) {
            JOptionPane.showMessageDialog(null, "You have canceled the series search.");
            return;
        }

        // Realiza la búsqueda en base a la opción seleccionada
        String result = "";
        switch (selectedOption) {
            case "Search by name":
                String searchedSeries = JOptionPane
                        .showInputDialog("Enter the name of the series you want to search:");
                for (String serie : series) {
                    if (serie.toLowerCase().contains(searchedSeries.toLowerCase())) {
                        result += serie + "\n";
                    }
                }
                break;
            case "Search by genre":
                String searchedGenre = JOptionPane
                        .showInputDialog("Enter the genre of the series you want to search:");
                for (String serie : series) {
                    if (serie.toLowerCase().contains(searchedGenre.toLowerCase())) {
                        result += serie + "\n";
                    }
                }
                break;
            case "Search by release year":
                String searchedYear = JOptionPane.showInputDialog(
                        "Enter the release year of the series you want to search:");
                for (String serie : series) {
                    if (serie.toLowerCase().contains(searchedYear.toLowerCase())) {
                        result += serie + "\n";
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(null,
                        "Invalid option. Please select a valid option.");
                return;
        }

        // Show the search result
        if (!result.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Search results:\n" + result);
        } else {
            JOptionPane.showMessageDialog(null, "No results found for the search.");
        }
    }


    public static void showFavorites() {
        // Lista de favoritos
        ArrayList<String> favorites = new ArrayList<>();

        boolean backToMenu = false;
        while (!backToMenu) {
            // Opciones de gestión favoritas
            String[] options =
                    {"View favorites", "Add favorite", "Remove favorite", "Modify favorite"};

            // Muestra las opciones de gestión favoritas en un cuadro de diálogo
            String selectedOption = (String) JOptionPane.showInputDialog(null, "Select an option:",
                    "Favorites Management", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            // Realiza la acción según la opción seleccionada
            switch (selectedOption) {
                case "View favorites":
                    // Mostrar los favoritos en un cuadro de diálogo
                    String message = "Favorites:\n";
                    for (String favorite : favorites) {
                        message += favorite + "\n";
                    }
                    JOptionPane.showMessageDialog(null, message);
                    break;
                case "Add favorite":
                    // Pide al usuario que ingrese el favorito para agregar
                    String newFavorite =
                            JOptionPane.showInputDialog("Enter the favorite you want to add:");
                    if (newFavorite != null) {
                        favorites.add(newFavorite);
                        JOptionPane.showMessageDialog(null,
                                "The favorite has been added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "You have canceled the addition of the favorite.");
                    }
                    break;
                case "Remove favorite":
                    // Muestra los favoritos en un cuadro de diálogo y pide al usuario que seleccione el favorito para
                    // eliminar
                    String favoriteToRemove = (String) JOptionPane.showInputDialog(null,
                            "Select the favorite you want to remove:", "Remove Favorite",
                            JOptionPane.PLAIN_MESSAGE, null, favorites.toArray(), favorites.get(0));
                    if (favoriteToRemove != null) {
                        favorites.remove(favoriteToRemove);
                        JOptionPane.showMessageDialog(null,
                                "The favorite has been removed successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "You have canceled the removal of the favorite.");
                    }
                    break;
                case "Modify favorite":
                    // Muestra los favoritos en un cuadro de diálogo y pide al usuario que seleccione el favorito para
                    // modificar
                    String favoriteToModify = (String) JOptionPane.showInputDialog(null,
                            "Select the favorite you want to modify:", "Modify Favorite",
                            JOptionPane.PLAIN_MESSAGE, null, favorites.toArray(), favorites.get(0));
                    if (favoriteToModify != null) {
                        // Ask the user to enter the new value for the selected favorite
                        String newValue = JOptionPane
                                .showInputDialog("Enter the new value for the selected favorite:");
                        if (newValue != null) {
                            int index = favorites.indexOf(favoriteToModify);
                            favorites.set(index, newValue);
                            JOptionPane.showMessageDialog(null,
                                    "The favorite has been modified successfully.");
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "You have canceled the modification of the favorite.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "You have canceled the modification of the favorite.");
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid option. Please select a valid option.");
                    break;
            }
        }
    }

    public static void showPlaybackHistory() {
        // Historial de reproducción
        String[] history = {"Series 1 - Episode 1", "Series 2 - Episode 3", "Series 3 - Episode 2",
                "Series 4 - Episode 5", "Series 5 - Episode 4"};

        // Muestra el historial de reproducción en un cuadro de diálogo
        String message = "Playback history:\n";
        for (String playback : history) {
            message += playback + "\n";
        }

        JOptionPane.showMessageDialog(null, message);
    }

    public static void showAccountSettings() {
       // Opciones de configuración de la cuenta
        String[] options = {"Change password"};

       // Muestra las opciones de configuración de la cuenta en un cuadro de diálogo
        String message = "Account settings:\n";
        for (String option : options) {
            message += option + "\n";
        }

        JOptionPane.showMessageDialog(null, message);
    }
}
