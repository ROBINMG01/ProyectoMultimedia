package co.edu.uptc.controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import co.edu.uptc.utilitaries.Utilitaries;

public class BuscarSerieImpl {
    private Utilitaries utilitaries;
    private boolean backToMenu = false;

    public BuscarSerieImpl() {
        utilitaries = new Utilitaries();
    }

    public void buscar() {
        // Catálogo de películas y series con año de lanzamiento y género.
        ArrayList<Movie> movieCatalog = utilitaries.loadMovies();
        ArrayList<Serie> seriesCatalog = utilitaries.loadSeries();

        // Opciones de búsqueda
        String[] searchOptions = {"Search by name"};

        // Bucle para volver a la pantalla de búsqueda
        boolean backToSearch = true;
        while (backToSearch) {
            // Muestra las opciones de búsqueda en un cuadro de diálogo
            String selectedOption =
                    (String) JOptionPane.showInputDialog(null, "Select a search option:",
                            "Search", JOptionPane.PLAIN_MESSAGE, null, searchOptions, searchOptions[0]);

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
                    if (searchedItem != null) {
                        searchedItem = searchedItem.toLowerCase();
                        for (Movie movie : movieCatalog) {
                            if (movie.getName().toLowerCase().contains(searchedItem)) {
                                // Mostrar solo el nombre de la película
                                result += movie.getName() + "\n";
                            }
                        }
                        for (Serie serie : seriesCatalog) {
                            if (serie.getName().toLowerCase().contains(searchedItem)) {
                                // Mostrar solo el nombre de la serie
                                result += serie.getName() + "\n";
                            }
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
                    String[] buttons = {"View Description", "Watch Trailer", "Back"};
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
                            // OJOOOOOOOOO!!!!! Aca va la logica de poder ver el trailer con la URL
                            JOptionPane.showMessageDialog(null, "Opening trailer...");
                            break;
                        case 2:
                            // Volver al menú anterior
                            backToMenu = true;
                            return;
                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Invalid option. Please select a valid option.");
                            return;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No results found for the search.");
            }

            // Preguntar si desea realizar otra búsqueda o volver al menú anterior
            String[] continueOptions = {"Continue searching", "Back to menu"};
            int continueOption = JOptionPane.showOptionDialog(null, "Do you want to continue searching or go back to the menu?", "Continue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, continueOptions, continueOptions[0]);

            if (continueOption == 1) {
                backToMenu = true;
                return;
            }
        }
    }

    public boolean isBackToMenu() {
        return backToMenu;
    }
}
