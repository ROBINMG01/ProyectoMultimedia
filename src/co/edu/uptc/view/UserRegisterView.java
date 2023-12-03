package co.edu.uptc.view;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.BuscarSerieImpl;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import co.edu.uptc.utilitaries.Utilitaries;

public class UserRegisterView {
    private UserController userController;
    private AdminController ad;
    private Utilitaries utilitaries;

    public UserRegisterView(AdminController ad) {
        this.ad = ad;
        this.userController = new UserController(ad);
        this.utilitaries = new Utilitaries();
    }
    public void userRegisterView() {
        BuscarSerieImpl buscarSerieImpl = new BuscarSerieImpl();

        while (userController.isExit()) {
            String option = JOptionPane.showInputDialog("Proyecto Multimedia\n" + "[1]. Ver catálogo de películas\n"
                    + "[2]. Ver catálogo de series\n" + "[3]. Buscar series y películas\n"
                    + "[4]. Ver mis favoritos\n" + "[5]. Configuración de la cuenta\n" + "[6]. Salir\n"
                    + "\nIngrese el número de la opción deseada:");
            if (option == null) {
                break;
            }
            switch (option) {
                case "1":
                    showMovieCatalog();
                    break;
                case "2":
                    showSeriesCatalog();
                    break;
                case "3":
                    buscarSerieImpl.buscar();
                    break;
                case "4":
                    showFavorites();
                    break;
                case "5":
                    showAccountSettings();
                    break;
                case "6":
                    userController.setExit(false);
                    JOptionPane.showMessageDialog(null, "Hasta luego, ¡vuelve pronto!", "Despedida",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opción inválida. Por favor, seleccione una opción válida.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    public void showMovieCatalog() {
        ArrayList<Movie> movies = ad.showListMovies();

        ArrayList<String> movieNames = new ArrayList<>();
        for (Movie movie : movies) {
            movieNames.add(movie.getName());
        }

        String selectedMovie = (String) JOptionPane.showInputDialog(null, "Select a movie:", "Movie Catalog",
                JOptionPane.PLAIN_MESSAGE, null, movieNames.toArray(), movieNames.get(0));

        if (selectedMovie != null) {
            JOptionPane.showMessageDialog(null, "You have selected the movie: " + selectedMovie);

            String[] buttons = { "View Description", "Watch Trailer", "Back" };
            int choice = JOptionPane.showOptionDialog(null, "What would you like to do?",
                    "Movie Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    buttons, buttons[0]);

            switch (choice) {
                case 0:
                    // Ver descripción de la película
                    Movie selectedMovieObj = null;
                    for (Movie movie : movies) {
                        if (movie.getName().equals(selectedMovie)) {
                            selectedMovieObj = movie;
                            break;
                        }
                    }

                    if (selectedMovieObj != null) {
                        String description = selectedMovieObj.getDescription();
                        JOptionPane.showMessageDialog(null, "Movie Description:\n" + description);
                    } else {
                        JOptionPane.showMessageDialog(null, "Movie not found.");
                    }
                    break;

                case 1:
                    // Ver el tráiler de la película
                    JOptionPane.showMessageDialog(null, "Opening trailer...");
                    break;
                case 2:
                    // Volver al menú anterior
                    return;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid option. Please select a valid option.");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "You have exited the movie catalog option.");
        }
    }

    public void showSeriesCatalog() {
        ArrayList<Serie> series = ad.showListSeries();

        ArrayList<String> seriesNames = new ArrayList<>();
        for (Serie serie : series) {
            seriesNames.add(serie.getName());
        }

        String selectedSeries = (String) JOptionPane.showInputDialog(null, "Select a series:", "Series Catalog",
                JOptionPane.PLAIN_MESSAGE, null, seriesNames.toArray(), seriesNames.get(0));

        if (selectedSeries != null) {
            JOptionPane.showMessageDialog(null, "You have selected the series: " + selectedSeries);

            String[] buttons = { "View Description", "Watch Trailer", "Back" };
            int choice = JOptionPane.showOptionDialog(null, "What would you like to do?",
                    "Series Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    buttons, buttons[0]);

            switch (choice) {
                case 0:
                    // Ver descripción de la serie
                    Serie selectedSerieObj = null;
                    for (Serie serie : series) {
                        if (serie.getName().equals(selectedSeries)) {
                            selectedSerieObj = serie;
                            break;
                        }
                    }

                    if (selectedSerieObj != null) {
                        String description = selectedSerieObj.getDescription();
                        JOptionPane.showMessageDialog(null, "Serie Description:\n" + description);
                    } else {
                        JOptionPane.showMessageDialog(null, "Serie not found.");
                    }
                    break;
                case 1:
                    // Ver el tráiler de la serie
                    JOptionPane.showMessageDialog(null, "Opening trailer...");
                    break;
                case 2:
                    // Volver al menú anterior
                    return;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid option. Please select a valid option.");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "You have exited the series catalog option.");
        }
    }

    public void showFavorites() {
        ArrayList<String> favoriteSeries = new ArrayList<>();
        ArrayList<String> favoriteMovies = new ArrayList<>();

        boolean backToMenu = false;
        while (!backToMenu) {
            String[] options = { "View favorites", "Add favorite", "Remove favorite", "Modify favorite" };

            String selectedOption = (String) JOptionPane.showInputDialog(null, "Select an option:",
                    "Favorites Management", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (selectedOption == null) {
                backToMenu = true; // Si el usuario ha cancelado la operación, salir del bucle
                continue;
            }

            switch (selectedOption) {
                case "View favorites":
                    String seriesMessage = "Favorite Series:\n";
                    for (String favoriteSerie : favoriteSeries) {
                        seriesMessage += favoriteSerie + "\n";
                    }

                    String moviesMessage = "Favorite Movies:\n";
                    for (String favoriteMovie : favoriteMovies) {
                        moviesMessage += favoriteMovie + "\n";
                    }

                    String message = seriesMessage + "\n" + moviesMessage;
                    JOptionPane.showMessageDialog(null, message);
                    break;
                case "Add favorite":
                    String[] catalogOptions = { "Series", "Movies" };
                    String selectedCatalogOption = (String) JOptionPane.showInputDialog(null,
                            "Select a catalog:", "Add Favorite", JOptionPane.PLAIN_MESSAGE, null,
                            catalogOptions, catalogOptions[0]);

                    if (selectedCatalogOption != null) {
                        ArrayList<String> catalog;
                        ArrayList<String> favorites;
                        if (selectedCatalogOption.equals("Series")) {
                            catalog = new ArrayList<>();
                            for (Serie serie : ad.showListSeries()) {
                                catalog.add(serie.getName());
                            }
                            favorites = favoriteSeries;
                        } else {
                            catalog = new ArrayList<>();
                            for (Movie movie : utilitaries.loadMovies()) {
                                catalog.add(movie.getName());
                            }
                            favorites = favoriteMovies;
                        }

                        String selectedFavorite = (String) JOptionPane.showInputDialog(null,
                                "Select a favorite:", "Add Favorite", JOptionPane.PLAIN_MESSAGE,
                                null, catalog.toArray(), catalog.get(0));

                        if (selectedFavorite != null) {
                            favorites.add(selectedFavorite);
                            JOptionPane.showMessageDialog(null,
                                    "The favorite has been added successfully.");
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "You have canceled the addition of the favorite.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "You have canceled the addition of the favorite.");
                    }
                    break;

                case "Remove favorite":
                    ArrayList<String> allFavorites = new ArrayList<>();
                    allFavorites.addAll(favoriteSeries);
                    allFavorites.addAll(favoriteMovies);

                    String favoriteToRemove = (String) JOptionPane.showInputDialog(null,
                            "Select the favorite you want to remove:", "Remove Favorite",
                            JOptionPane.PLAIN_MESSAGE, null, allFavorites.toArray(),
                            allFavorites.get(0));
                    if (favoriteToRemove != null) {
                        if (favoriteSeries.contains(favoriteToRemove)) {
                            favoriteSeries.remove(favoriteToRemove);
                        } else if (favoriteMovies.contains(favoriteToRemove)) {
                            favoriteMovies.remove(favoriteToRemove);
                        }
                        JOptionPane.showMessageDialog(null,
                                "The favorite has been removed successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "You have canceled the removal of the favorite.");
                    }
                    break;
                case "Modify favorite":
                    ArrayList<String> allFavorite = new ArrayList<>();
                    allFavorite.addAll(favoriteSeries);
                    allFavorite.addAll(favoriteMovies);

                    String favoriteToModify = (String) JOptionPane.showInputDialog(null,
                            "Select the favorite you want to modify:", "Modify Favorite",
                            JOptionPane.PLAIN_MESSAGE, null, allFavorite.toArray(),
                            allFavorite.get(0));
                    if (favoriteToModify != null) {
                        String newValue = JOptionPane
                                .showInputDialog("Enter the new value for the selected favorite:");
                        if (newValue != null) {
                            if (favoriteSeries.contains(favoriteToModify)) {
                                int index = favoriteSeries.indexOf(favoriteToModify);
                                favoriteSeries.set(index, newValue);
                            } else if (favoriteMovies.contains(favoriteToModify)) {
                                int index = favoriteMovies.indexOf(favoriteToModify);
                                favoriteMovies.set(index, newValue);
                            }
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

    public void showAccountSettings() {
        String[] options = { "Change password" };

        String message = "Account settings:\n";
        for (String option : options) {
            message += option + "\n";
        }

        int choice = JOptionPane.showOptionDialog(null, message, "Account Settings",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            JPasswordField passwordField = new JPasswordField();
            JPasswordField confirmPasswordField = new JPasswordField();

            JLabel passwordLabel = new JLabel("New Password:");
            JLabel confirmPasswordLabel = new JLabel("Confirm Password:");

            Object[] inputFields = { passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField };

            int result = JOptionPane.showConfirmDialog(null, inputFields, "Change Password",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String newPassword = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (newPassword.equals(confirmPassword)) {
                    // Aquí debo realizar la lógica para cambiar la contraseña
                    // Utiliza la variable newPassword para obtener la nueva contraseña ingresada
                    JOptionPane.showMessageDialog(null, "Password changed successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
