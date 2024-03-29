package co.edu.uptc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.view.ViewVisit;

public class BuscarController {

    private boolean backToMenu = false;
    private ViewVisit viewVisit;
    private AdminController adminController;
    private Season season;

    public BuscarController(AdminController ad) {
        viewVisit = new ViewVisit();
        this.adminController = ad;
    }

    public void buscar() {
        // Catálogo de películas y series con año de lanzamiento y género.

        // Opciones de búsqueda
        String[] searchOptions = { "Search by name", "Search by gender" };

        // Bucle para volver a la pantalla de búsqueda
        boolean backToSearch = true;
        while (backToSearch) {
            // Muestra las opciones de búsqueda en un cuadro de diálogo
            String selectedOption = (String) JOptionPane.showInputDialog(null, "Select a search option:",
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
                        for (Movie movie : adminController.getListMovies()) {
                            if (movie.getName().toLowerCase().contains(searchedItem)) {
                                // Mostrar solo el nombre de la película
                                result += movie.getName() + "\n";
                            }
                        }
                        for (Serie serie : adminController.getListSeries()) {
                            if (serie.getName().toLowerCase().contains(searchedItem)) {
                                // Mostrar solo el nombre de la serie
                                result += serie.getName() + "\n";
                            }
                        }
                    }
                    break;
                case "Search by gender":
                    String gender = "";

                    gender = viewVisit.viewGender(gender);
                    if (gender != null) {
                        for (Movie movies : adminController.getListMovies()) {
                            if (movies.getGender().equalsIgnoreCase(gender)) {
                                result += movies.getName() + "\n";

                            }

                        }
                        for (Serie series : adminController.getListSeries()) {
                            if (series.getGender().equalsIgnoreCase(gender)) {
                                // Mostrar solo el nombre de la serie
                                result += series.getName() + "\n";
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
                    String[] buttons = { "View Description", "Watch Trailer", "Back" };
                    int choice = JOptionPane.showOptionDialog(null, "What would you like to do?",
                            "Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                            buttons, buttons[0]);

                    switch (choice) {
                        case 0:
                            // Ver descripción de la película o serie
                            for (Movie movie : adminController.getListMovies()) {
                                if (movie.getName().equals(selectedMovie)) {
                                    String name = movie.getName();
                                    String gender = movie.getGender();
                                    int duration = movie.getDuration();
                                    String description = movie.getDescription();
                                    List<String> listAuthors = movie.getlistAuthors();
                                    List<String> listActors = movie.getListActors();
                                    JOptionPane.showMessageDialog(null, "\nName: " + name
                                            + "\nGender: " + gender + "\nDuration: " + duration + "\nDescription: "
                                            + description + "\nListaAuthors: " + listAuthors + "\nListActors: "
                                            + listActors, "Movie Description: ", JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                }
                            }
                            for (Serie serie : adminController.getListSeries()) {
                                if (serie.getName().equals(selectedMovie)) {
                                    String name = serie.getName();
                                    String gender = serie.getGender();
                                    int duration = serie.getDuration();
                                    String description = serie.getDescription();
                                    List<String> listAuthors = serie.getlistAuthors();
                                    List<String> listActors = serie.getListActors();
                                    List<Chapter> listChapters = season.getListChapters();
                                    JOptionPane.showMessageDialog(null, "\nName: " + name
                                            + "\nGender: " + gender + "\nDuration: " + duration + "\nDescription: "
                                            + description + "\nListaAuthors: " + listAuthors + "\nListActors: "
                                            + listActors + "\nListChapters: " + listChapters, "Serie Description: ",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                }
                            }
                            break;
                        case 1:
                            // Ver el tráiler de la película o serie
                            SwingUtilities.invokeLater(() -> {
                                JOptionPane pane = new JOptionPane();
                                JProgressBar progressBar = new JProgressBar(0, 100);
                                progressBar.setIndeterminate(false);
                                progressBar.setStringPainted(true);
                                pane.setMessage(new Object[] { "Reproduciendo", progressBar });

                                JDialog dialog = pane.createDialog("Reproduciendo");
                                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                                Timer timer = new Timer(1000, e -> {
                                    int value = progressBar.getValue();
                                    if (value < 100) {
                                        progressBar.setValue(value + 10);
                                    }
                                });
                                timer.setRepeats(true);
                                timer.start();

                                Timer closeTimer = new Timer(10000, e -> {
                                    dialog.dispose();
                                    timer.stop(); // para el tiempo cuando el dialogo se cierra
                                });
                                closeTimer.setRepeats(false);
                                closeTimer.start();

                                dialog.setVisible(true);
                            });
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
            String[] continueOptions = { "Continue searching", "Back to menu" };
            int continueOption = JOptionPane.showOptionDialog(null,
                    "Do you want to continue searching or go back to the menu?", "Continue", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, continueOptions, continueOptions[0]);

            if (continueOption == 1) {
                backToMenu = true;
                return;
            }
        }
    }

    public ArrayList<Movie> getMovies() {
        return adminController.getListMovies();
    }
    public boolean isBackToMenu() {
        return backToMenu;
    }

    public ArrayList<Movie> buscar(String movieName) {
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie movie : adminController.getListMovies()) {
            if (movie.getName().toLowerCase().contains(movieName.toLowerCase())) {
                result.add(movie);
            }
        }
        return result;
    }
}
