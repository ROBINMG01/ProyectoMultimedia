package co.edu.uptc.viewFX;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewVisit {
    private boolean backToMenu = false;
    private AdminController adminController;

    public ViewVisit(AdminController ad) {
        this.adminController = ad;
    }

    public ViewVisit() {
    }

    public void visitView() {

        int w = 0;
        String[] viewOptions = { "View movies", "View series", "Search" };
        do {

            // Muestra las opciones de búsqueda en un cuadro de diálogo
            String selectedOption = (String) JOptionPane.showInputDialog(null, "Select an option:",
                    "View", JOptionPane.PLAIN_MESSAGE, null, viewOptions, viewOptions[0]);

            // Comprobar si se seleccionó "Cancelar" en el cuadro de diálogo
            if (selectedOption == null) {
                return;
            }

            // Realiza la búsqueda en base a la opción seleccionada
            String result = "";
            switch (selectedOption) {
                case "View movies":

                    for (Movie movies : adminController.getListMovies()) {
                        result += movies.getName() + "\n";
                    }
                    break;
                case "View series":
                    for (Serie series : adminController.getListSeries()) {
                        result += series.getName() + "\n";
                    }

                    break;
                case "Search":
                    String[] searchOptions = { "Search by name", "Search by gender" };
                    selectedOption = (String) JOptionPane.showInputDialog(null, "Select a search option:",
                            "Search", JOptionPane.PLAIN_MESSAGE, null, searchOptions, searchOptions[0]);

                    // Comprobar si se seleccionó "Cancelar" en el cuadro de diálogo
                    if (selectedOption == null) {
                        JOptionPane.showMessageDialog(null, "You have canceled the search.", "Canceled",
                                JOptionPane.CANCEL_OPTION);
                        return;
                    }

                    // Realiza la búsqueda en base a la opción seleccionada
                    result = "";
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

                            gender = viewGender(gender);
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

                    break;
                case "exit":
                    w = 10;
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
                    String[] buttons = { "View Description", "Back" };
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
                                    List<String> listChapters = serie.getListChapters();
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
                            backToMenu = true;
                            return;
                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Invalid option. Please select a valid option.");
                            return;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No results found for the search.", "Null",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } while (w != 10);
    }

    public String viewGender(String gender) {
        String optionHome[] = { "Accion", "Comedia", "Aventura", "Drama", "Terror", "Ficcion" };
        UIManager.put("OptionPane.cancelButtonText", "Back");
        UIManager.put("OptionPane.okButtonText", "select");
        // icono de la imagen
        ImageIcon icon = new ImageIcon("");

        // Obtener la imagen del ImageIcon original
        Image originalImage = icon.getImage();

        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
        int newWidth = 250;
        int newHeight = 250;

        // Redimensionar la imagen
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        String seleccion = (String) JOptionPane.showInputDialog(null, "Select a gender:",
                "filtering options", JOptionPane.QUESTION_MESSAGE, resizedIcon, optionHome,
                optionHome[0]);
        // para que influya en todos
        UIManager.put("OptionPane.cancelButtonText", "Back");
        UIManager.put("OptionPane.okButtonText", "Accept");
        if (seleccion == null) {
            seleccion = "exit";
        }
        switch (seleccion) {
            case "Accion":
                gender = "Accion";

                break;
            case "Comedia":
                gender = "Comedia";

                break;
            case "Aventura":
                gender = "Aventura";
                break;
            case "Drama":
                gender = "Drama";
                break;
            case "Terror":
                gender = "Terror";
                break;
            case "Ficcion":
                gender = "Ficcion";

                break;

            default:
                JOptionPane.showMessageDialog(null, "Invalid command", "Error",
                        JOptionPane.ERROR_MESSAGE);
                break;
        }
        return gender;

    }

    public void buscar() {

    }

    public boolean isBackToMenu() {
        return backToMenu;
    }
}
