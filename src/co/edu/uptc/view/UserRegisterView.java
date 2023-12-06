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
    private static ArrayList<Object> favorites;

    public UserRegisterView(AdminController ad) {
        this.ad = ad;
        this.userController = new UserController(ad);
        this.utilitaries = new Utilitaries();
        UserRegisterView.favorites = new ArrayList<>();
    }

    public void userRegisterView() {
        BuscarSerieImpl buscarSerieImpl = new BuscarSerieImpl();

        while (userController.isExit()) {
            String option = JOptionPane
                    .showInputDialog("Proyecto Multimedia\n" + "[1]. Ver catálogo de películas\n"
                            + "[2]. Ver catálogo de series\n" + "[3]. Buscar series y películas\n"
                            + "[4]. Ver mis favoritos\n" + "[5]. Configuración de la cuenta\n"
                            + "[6]. Salir\n" + "\nIngrese el número de la opción deseada:");

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

        String selectedMovie =
                (String) JOptionPane.showInputDialog(null, "Select a movie:", "Movie Catalog",
                        JOptionPane.PLAIN_MESSAGE, null, movieNames.toArray(), movieNames.get(0));

        if (selectedMovie != null) {
            JOptionPane.showMessageDialog(null, "You have selected the movie: " + selectedMovie);

            String[] buttons = {"View Description", "Watch Trailer", "Back"};
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

        String selectedSeries =
                (String) JOptionPane.showInputDialog(null, "Select a series:", "Series Catalog",
                        JOptionPane.PLAIN_MESSAGE, null, seriesNames.toArray(), seriesNames.get(0));

        if (selectedSeries != null) {
            JOptionPane.showMessageDialog(null, "You have selected the series: " + selectedSeries);

            String[] buttons = {"View Description", "Watch Trailer", "Back"};
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
        boolean backToMenu = false;
        while (!backToMenu) {
            String[] options = {"View series favorites", "View movie favorites",
                    "Add series favorite", "Add movie favorite", "Remove series favorite",
                    "Remove movie favorite", "Back"};

            String selectedOption = (String) JOptionPane.showInputDialog(null, "Select an option:",
                    "Favorites Management", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (selectedOption == null) {
                backToMenu = true; // Si el usuario ha cancelado la operación, salir del bucle
                continue;
            }

            switch (selectedOption) {
                case "View series favorites":
                    viewSeriesFavorites();
                    break;
                case "View movie favorites":
                    viewMovieFavorites();
                    break;
                case "Add series favorite":
                    addSeriesFavorite();
                    break;
                case "Add movie favorite":
                    addMovieFavorite();
                    break;
                case "Remove series favorite":
                    removeSeriesFavorite();
                    break;
                case "Remove movie favorite":
                    removeMovieFavorite();
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid option. Please select a valid option.");
                    break;
            }
        }
    }

public void viewSeriesFavorites() {
        ArrayList<Serie> seriesFavorites = new ArrayList<>();
        for (Object favorite : favorites) {
            if (favorite instanceof Serie) {
                seriesFavorites.add((Serie) favorite);
            }
        }
    
        if (seriesFavorites.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No series favorites found.");
            return;
        }
    
        String[] seriesNames = new String[seriesFavorites.size()];
        for (int i = 0; i < seriesFavorites.size(); i++) {
            seriesNames[i] = seriesFavorites.get(i).getName();
        }
    
        String selectedSeries = (String) JOptionPane.showInputDialog(null,
                "Select a series to view:", "View Series",
                JOptionPane.PLAIN_MESSAGE, null, seriesNames, seriesNames[0]);
    
        if (selectedSeries != null) {
            // Aquí puedes agregar la lógica para ver la información de la serie seleccionada
            JOptionPane.showMessageDialog(null, "Viewing series: " + selectedSeries);
    
            String[] buttons = {"View Description", "Watch Trailer", "Back"};
            int choice = JOptionPane.showOptionDialog(null, "What would you like to do?", "Series Options",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);
    
            switch (choice) {
                case 0:
                    // Ver descripción de la serie
                    Serie selectedSerieObj = null;
                    for (Serie serie : seriesFavorites) {
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
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please select a valid option.");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "You have canceled viewing the series.");
        }
    }
    
    public void viewMovieFavorites() {
        ArrayList<Movie> movieFavorites = new ArrayList<>();
        for (Object favorite : favorites) {
            if (favorite instanceof Movie) {
                movieFavorites.add((Movie) favorite);
            }
        }
    
        if (movieFavorites.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No movie favorites found.");
            return;
        }
    
        String[] movieNames = new String[movieFavorites.size()];
        for (int i = 0; i < movieFavorites.size(); i++) {
            movieNames[i] = movieFavorites.get(i).getName();
        }
    
        String selectedMovie = (String) JOptionPane.showInputDialog(null,
                "Select a movie to view:", "View Movie",
                JOptionPane.PLAIN_MESSAGE, null, movieNames, movieNames[0]);
    
        if (selectedMovie != null) {
            // Aquí puedes agregar la lógica para ver la información de la película seleccionada
            JOptionPane.showMessageDialog(null, "Viewing movie: " + selectedMovie);
    
            String[] buttons = {"View Description", "Watch Trailer", "Back"};
            int choice = JOptionPane.showOptionDialog(null, "What would you like to do?", "Movie Options",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);
    
            switch (choice) {
                case 0:
                    // Ver descripción de la película
                    Movie selectedMovieObj = null;
                    for (Movie movie : movieFavorites) {
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
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please select a valid option.");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "You have canceled viewing the movie.");
        }
    }
    


    public void addSeriesFavorite() {
        ArrayList<Serie> seriesCatalog = ad.showListSeries();
        ArrayList<String> seriesNames = new ArrayList<>();
        for (Serie serie : seriesCatalog) {
            seriesNames.add(serie.getName());
        }

        String selectedSeries = (String) JOptionPane.showInputDialog(null,
                "Select a series to add to favorites:", "Add Series Favorite",
                JOptionPane.PLAIN_MESSAGE, null, seriesNames.toArray(), seriesNames.get(0));

        if (selectedSeries != null) {
            for (Serie serie : seriesCatalog) {
                if (serie.getName().equals(selectedSeries)) {
                    if (!favorites.contains(serie)) {
                        favorites.add(serie);
                        JOptionPane.showMessageDialog(null,
                                "The series has been added to favorites successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "The series is already in favorites.");
                    }
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "You have canceled adding the series to favorites.");
        }
    }

    public void addMovieFavorite() {
        ArrayList<Movie> movieCatalog = ad.showListMovies();
        ArrayList<String> movieNames = new ArrayList<>();
        for (Movie movie : movieCatalog) {
            movieNames.add(movie.getName());
        }

        String selectedMovie = (String) JOptionPane.showInputDialog(null,
                "Select a movie to add to favorites:", "Add Movie Favorite",
                JOptionPane.PLAIN_MESSAGE, null, movieNames.toArray(), movieNames.get(0));

        if (selectedMovie != null) {
            for (Movie movie : movieCatalog) {
                if (movie.getName().equals(selectedMovie)) {
                    if (!favorites.contains(movie)) {
                        favorites.add(movie);
                        JOptionPane.showMessageDialog(null,
                                "The movie has been added to favorites successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "The movie is already in favorites.");
                    }
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "You have canceled adding the movie to favorites.");
        }
    }
public void removeSeriesFavorite() {
        ArrayList<Serie> seriesFavorites = new ArrayList<>();
        for (Object favorite : favorites) {
            if (favorite instanceof Serie) {
                seriesFavorites.add((Serie) favorite);
            }
        }
    
        if (seriesFavorites.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No series favorites found.");
            return;
        }
    
        String[] seriesNames = new String[seriesFavorites.size()];
        for (int i = 0; i < seriesFavorites.size(); i++) {
            seriesNames[i] = seriesFavorites.get(i).getName();
        }
    
        String selectedSeries = (String) JOptionPane.showInputDialog(null,
                "Select a series to remove from favorites:", "Remove Series Favorite",
                JOptionPane.PLAIN_MESSAGE, null, seriesNames, seriesNames[0]);
    
        if (selectedSeries != null) {
            favorites.removeIf(favorite -> {
                if (favorite instanceof Serie) {
                    return ((Serie) favorite).getName().equals(selectedSeries);
                }
                return false;
            });
            JOptionPane.showMessageDialog(null, "The series has been removed from favorites successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "You have canceled removing the series from favorites.");
        }
    }
    
    public void removeMovieFavorite() {
        ArrayList<Movie> movieFavorites = new ArrayList<>();
        for (Object favorite : favorites) {
            if (favorite instanceof Movie) {
                movieFavorites.add((Movie) favorite);
            }
        }
    
        if (movieFavorites.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No movie favorites found.");
            return;
        }
    
        String[] movieNames = new String[movieFavorites.size()];
        for (int i = 0; i < movieFavorites.size(); i++) {
            movieNames[i] = movieFavorites.get(i).getName();
        }
    
        String selectedMovie = (String) JOptionPane.showInputDialog(null,
                "Select a movie to remove from favorites:", "Remove Movie Favorite",
                JOptionPane.PLAIN_MESSAGE, null, movieNames, movieNames[0]);
    
        if (selectedMovie != null) {
            favorites.removeIf(favorite -> {
                if (favorite instanceof Movie) {
                    return ((Movie) favorite).getName().equals(selectedMovie);
                }
                return false;
            });
            JOptionPane.showMessageDialog(null, "The movie has been removed from favorites successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "You have canceled removing the movie from favorites.");
        }
    }
    


    public void showAccountSettings() {
        String[] options = {"Change password"};

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

            Object[] inputFields =
                    {passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField};

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
