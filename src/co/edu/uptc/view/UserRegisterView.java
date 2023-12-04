package co.edu.uptc.view;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.BuscarSerieImpl;
import co.edu.uptc.controller.ControlerInitialMenuView;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Role;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;
import co.edu.uptc.utilitaries.Utilitaries;

public class UserRegisterView {
    private UserController userController;
    private AdminController ad;
    private Utilitaries utilitaries;
    private User user;

    public UserRegisterView(AdminController ad, User user) {
        this.ad = ad;
        this.userController = new UserController(ad);
        this.utilitaries = new Utilitaries();
        this.user = user;
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
                    showAccountSettings(user);
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

    public void showAccountSettings(User user) {
        ControlerInitialMenuView controler = new ControlerInitialMenuView();
        int au = 0;
        boolean exits = false;
        String firstName = "";
        String lastName = "";

        String email = "";
        do {

            if (au == 0) {
                firstName = "";
                lastName = "";

                email = "";
            }
            JPanel panel = new JPanel();

            JTextField firstNameField = new JTextField(user.getFirstName(), 10);
            JPasswordField passwordField = new JPasswordField(user.getPassword(), 10);
            JPasswordField confirmPasswordField = new JPasswordField(user.getPassword(), 10);
            JTextField lastNameField = new JTextField(user.getLastName(), 10);
            JTextField emailField = new JTextField(user.getEmail(), 10);

            // Agregar los componentes al panel
            panel.add(new JLabel("First Name:"));
            panel.add(firstNameField);
            panel.add(new JLabel("Last Name:"));
            panel.add(lastNameField);

            panel.add(new JLabel("Email:"));
            panel.add(emailField);
            panel.add(new JLabel("Password:"));
            panel.add(passwordField);
            panel.add(new JLabel("Confirm Password:"));
            panel.add(confirmPasswordField);

            // para que aparezca en vertical
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            // color del panel
            panel.setBackground(Color.CYAN);

            // icono de la imagen
            ImageIcon iconChef = new ImageIcon("img\\chef.png");

            // Obtener la imagen del ImageIcon original
            Image chefImg = iconChef.getImage();

            // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
            int newWidth = 150;
            int newHeight = 150;

            // Redimensionar la imagen
            Image chefImgs = chefImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // Crear un nuevo ImageIcon a partir de la imagen redimensionada
            ImageIcon imgchef = new ImageIcon(chefImgs);

            int resultado = JOptionPane.showConfirmDialog(null, panel, "Ingrese sus datos",
                    JOptionPane.OK_CANCEL_OPTION, 1, imgchef);

            if (resultado == JOptionPane.OK_OPTION) {
                firstName = firstNameField.getText();
                lastName = lastNameField.getText();

                email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (!firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()
                        && !confirmPassword.isEmpty()) {
                    if (password.equals(confirmPassword)) {
                        // valida que el email cumpla con lo minimi
                        int emailRevi = controler.isEmailUnique(email);
                        // valida que la contraseña cumpla con lo minimo
                        int validePassworMin = controler.validatePassword(confirmPassword);
                        // vreificar de que el cooreo no se repita
                        int uniqueEmail = controler.uniqueEmail(email);
                        if (emailRevi == 0 && validePassworMin == 0 && uniqueEmail == 0) {
                            // crear usuario
                            controler.user(new User(firstName, lastName, email, password, Role.user));

                            // modificar al la lista de usuarios
                            controler.modifyUser(user);
                            au = 0;
                            JOptionPane.showMessageDialog(null, "modified user");
                        } else if (uniqueEmail == 2) {
                            au = 1;
                            email = "****";
                            JOptionPane.showMessageDialog(null, "Email used error");
                        } else if (emailRevi == 1) {
                            au = 1;
                            email = "****";
                            JOptionPane.showMessageDialog(null, "Invalid email format error");
                        } else if (validePassworMin == 3) {
                            au = 1;

                            JOptionPane.showMessageDialog(null, "la contraseña no comple con lo esperado");

                        }

                    } else {
                        au = 1;
                        JOptionPane.showMessageDialog(null, "Passwords do not match");
                    }
                } else {
                    au = 1;
                    JOptionPane.showMessageDialog(null, "Fill in all the fields correctly");
                }

                int option = JOptionPane.showConfirmDialog(null, "Do you want to modify something else?",
                        "Continue?", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.NO_OPTION) {
                    exits = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Operation cancelled");
                exits = true;
            }
        } while (!exits);
    }
}
