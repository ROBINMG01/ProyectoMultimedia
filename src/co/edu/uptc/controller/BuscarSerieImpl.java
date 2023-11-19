package co.edu.uptc.controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import co.edu.uptc.utilitaries.Utilitaries;

public class BuscarSerieImpl {
    private Utilitaries utilitaries;

    public BuscarSerieImpl() {
        utilitaries = new Utilitaries();
    }

    public void buscar() {
        // Catálogo de películas y series con año de lanzamiento y género.
        ArrayList<Movie> movieCatalog = utilitaries.getMovieCatalog();
        ArrayList<Serie> seriesCatalog = utilitaries.getSeriesCatalog();

        // Opciones de búsqueda
        String[] options = {"Search by name"};

        // Bucle para volver a la pantalla de búsqueda
        boolean backToSearch = true;
        while (backToSearch) {
            // Muestra las opciones de búsqueda en un cuadro de diálogo
            String selectedOption =
                    (String) JOptionPane.showInputDialog(null, "Select a search option:",
                            "Search", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            // Comprobar si se seleccionó "Cancelar" en el cuadro de diálogo
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "You have canceled the search.");
                return;
            }

            // Realiza la búsqueda en base a la opción seleccionada
            String result = "";
            switch (selectedOption) {
                case "Search by name":
                    String searchedItem = JOptionPane
                            .showInputDialog("Enter the name of the item you want to search:");
                    for (Movie movie : movieCatalog) {
                        if (movie.getName().toLowerCase().contains(searchedItem.toLowerCase())) {
                            // Mostrar solo el nombre de la película
                            result += movie.getName() + "\n";
                        }
                    }
                    for (Serie serie : seriesCatalog) {
                        if (serie.getName().toLowerCase().contains(searchedItem.toLowerCase())) {
                            // Mostrar solo el nombre de la serie
                            result += serie.getName() + "\n";
                        }
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid option. Please select a valid option.");
                    return;
            }

            // Mostrar el resultado de la búsqueda
            if (!result.isEmpty()) {
                // Mostrar el resultado en un cuadro de diálogo
                String selectedMovie = (String) JOptionPane.showInputDialog(null, "Select a movie or series:",
                        "Catalog", JOptionPane.PLAIN_MESSAGE, null, result.split("\n"), result.split("\n")[0]);

                if (selectedMovie != null) {
                    // Mostrar opciones adicionales
                    String[] buttons = {"View Description", "Watch Trailer"};
                    int choice = JOptionPane.showOptionDialog(null, "What would you like to do?",
                            "Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                            buttons, buttons[0]);

                    switch (choice) {
                        case 0:
                            // Ver descripción de la película o serie
                            for (Movie movie : movieCatalog) {
                                if (movie.getName().equals(selectedMovie)) {
                                    String description = movie.getDescription();
                                    JOptionPane.showMessageDialog(null, "Movie Description:\n" + description);
                                    break;
                                }
                            }
                            for (Serie serie : seriesCatalog) {
                                if (serie.getName().equals(selectedMovie)) {
                                    String description = serie.getDescription();
                                    JOptionPane.showMessageDialog(null, "Serie Description:\n" + description);
                                    break;
                                }
                            }
                            break;
                        case 1:
                            // Ver el tráiler de la película o serie
                            // for (Movie movie : movieCatalog) {
                            //     if (movie.getName().equals(selectedMovie)) {
                            //         String trailerUrl = movie.getTrailerUrl();
                            //         if (trailerUrl != null && !trailerUrl.isEmpty()) {
                            //             // Aquí puedes agregar la lógica para abrir el enlace del tráiler
                            //             JOptionPane.showMessageDialog(null, "Opening trailer...");
                            //         } else {
                            //             JOptionPane.showMessageDialog(null,
                            //                     "Trailer not available for this movie.", "Information",
                            //                     JOptionPane.INFORMATION_MESSAGE);
                            //         }
                            //         break;
                            //     }
                            // }
                            // for (Serie serie : seriesCatalog) {
                            //     if (serie.getName().equals(selectedMovie)) {
                            //         String trailerUrl = serie.getTrailerUrl();
                            //         if (trailerUrl != null && !trailerUrl.isEmpty()) {
                            //             // Aquí puedes agregar la lógica para abrir el enlace del tráiler
                            //             JOptionPane.showMessageDialog(null, "Opening trailer...");
                            //         } else {
                            //             JOptionPane.showMessageDialog(null,
                            //                     "Trailer not available for this series.", "Information",
                            //                     JOptionPane.INFORMATION_MESSAGE);
                            //         }
                            //         break;
                            //     }
                            // }
                            // break;
                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Invalid option. Please select a valid option.");
                            break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No results found for the search.");
            }

            // Preguntar si desea realizar otra búsqueda
            int continueOption = JOptionPane.showConfirmDialog(null, "Do you want to continue searching?", "Continue", JOptionPane.YES_NO_OPTION);
            if (continueOption == JOptionPane.NO_OPTION) {
                backToSearch = false;
            }
        }
    }
}
