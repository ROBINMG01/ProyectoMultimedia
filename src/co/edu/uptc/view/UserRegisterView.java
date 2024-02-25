package co.edu.uptc.view;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.ControlerInitialMenuView;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.controller.BuscarController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Role;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

// Constructor
public class UserRegisterView {
    private UserController userController;
    private AdminController ad;
    private User user;
    private ControlerInitialMenuView controlerInitialMenuView;

    public UserRegisterView(AdminController ad, User user, ControlerInitialMenuView controlerInitialMenuView) {
        this.ad = ad;
        this.userController = new UserController(ad);
        this.controlerInitialMenuView = controlerInitialMenuView;
        this.user = user;
    }

    // Entrada principal
    public void userRegisterView() {

        BuscarController buscarSerieImpl = new BuscarController(ad);

        while (userController.isExit()) {

            String option = JOptionPane.showInputDialog("Multimedia Project\n"
                    + "[1]. View movie catalog\n" + "[2]. View series catalog\n"
                    + "[3]. Search for series and movies\n" + "[4]. View my favorites\n"
                    + "[5]. Account settings\n" + "\nEnter the number of the desired option:");

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
                case "Back":
                    userController.setExit(false);
                    JOptionPane.showMessageDialog(null, "Goodbye, come back soon!", "Farewell",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid option. Please select a valid option.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }

        // Guardar los favoritos antes de salir del programa
        updateFavoritesOnExit(user);
    }

    // Actualiza la lista de favoritos del ususario
    private void updateFavoritesOnExit(User user) {
        user.setListMoviesFavorites(null);
    }

    // Muestra el catalogo de peliculas
    public void showMovieCatalog() {
        ArrayList<Movie> movies = ad.showListMovies();

        ArrayList<String> movieNames = new ArrayList<>();
        for (Movie movie : movies) {
            movieNames.add(movie.getName());
        }

        // Colocar imagen
        ImageIcon movieIcon = new ImageIcon("src/co/edu/uptc/image/13432033-película-tema-de-diseño.jpg");
        JLabel movieLabel = new JLabel(movieIcon);

        String selectedMovie = (String) JOptionPane.showInputDialog(null, movieLabel, "Movie Catalog",
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
                        showMovieCatalog();
                    } else {
                        JOptionPane.showMessageDialog(null, "Movie not found.");
                    }
                    break;

                case 1:

                    // Ver el tráiler de la película
                    ReproduccionFrame();

                    // Mostrar cuadro de diálogo con opciones después de ver el tráiler
                    Object[] trailerOptions = { "Back", "Back to Menu" };
                    int trailerChoice = JOptionPane.showOptionDialog(null,
                            "What would you like to do?", "Trailer Options",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                            trailerOptions, trailerOptions[0]);

                    if (trailerChoice == 0) {
                        // Volver a mostrar el cuadro de diálogo de selección de series
                        showMovieCatalog();
                    } else if (trailerChoice == 1) {
                        // Volver al menú principal
                        return;
                    }

                    break;
                case 2:
                    // Volver al menú anterior
                    showMovieCatalog();
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid option. Please select a valid option.");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "You have exited the movie catalog option.");
        }
    }

    // Muestra el catalogo de series
    public void showSeriesCatalog() {
        ArrayList<Serie> series = ad.showListSeries();

        ArrayList<String> seriesNames = new ArrayList<>();
        for (Serie serie : series) {
            seriesNames.add(serie.getName());
        }

        // Colocar imagen
        ImageIcon seriesIcon = new ImageIcon("src/co/edu/uptc/image/descarga.jpeg");
        JLabel seriesLabel = new JLabel(seriesIcon);

        String selectedSeries = (String) JOptionPane.showInputDialog(null, seriesLabel, "Series Catalog",
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
                        showSeriesCatalog();
                    } else {
                        JOptionPane.showMessageDialog(null, "Serie not found.");
                    }
                    break;
                case 1:
                    // Ver el tráiler de la serie
                    ReproduccionFrame();

                    // Mostrar cuadro de diálogo con opciones después de ver el tráiler
                    Object[] trailerOptions = { "Back", "Back to Menu" };
                    int trailerChoice = JOptionPane.showOptionDialog(null,
                            "What would you like to do?", "Trailer Options",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                            trailerOptions, trailerOptions[0]);

                    if (trailerChoice == 0) {
                        // Volver a mostrar el cuadro de diálogo de selección de series
                        showSeriesCatalog();
                    } else if (trailerChoice == 1) {
                        // Volver al menú principal
                        return;
                    }
                    break;
                case 2:
                    // Volver al menú anterior
                    showSeriesCatalog();

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
            String[] options = { "Movie", "Serie" };

            String selectedOption = (String) JOptionPane.showInputDialog(null, "Select an option:",
                    "Favorites Management", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (selectedOption == null) {
                backToMenu = true;
                continue;
            }

            switch (selectedOption) {
                case "Movie":

                    // Colocar imagen
                    ImageIcon movieIcon = new ImageIcon(
                            "src/co/edu/uptc/image/13432033-película-tema-de-diseño.jpg");
                    JLabel movieLabel = new JLabel(movieIcon);

                    String[] movieOptions = { "View favorites", "Add favorite", "Remove favorite" };
                    String selectedMovieOption = (String) JOptionPane.showInputDialog(null,
                            movieLabel, "Movie Favorites", JOptionPane.PLAIN_MESSAGE, null,
                            movieOptions, movieOptions[0]);

                    if (selectedMovieOption == null) {
                        backToMenu = true;
                        continue;
                    }

                    switch (selectedMovieOption) {
                        case "View favorites":
                            viewMovieFavorites(user);
                            break;
                        case "Add favorite":
                            addMovieFavorite(user);
                            break;
                        case "Remove favorite":
                            removeMovieFavorite(user);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Invalid option. Please select a valid option.");
                            break;
                    }
                    break;
                case "Serie":

                    // Colocar imagen
                    ImageIcon seriesIcon = new ImageIcon("src/co/edu/uptc/image/descarga.jpeg");
                    JLabel seriesLabel = new JLabel(seriesIcon);

                    String[] serieOptions = { "View favorites", "Add favorite", "Remove favorite" };
                    String selectedSerieOption = (String) JOptionPane.showInputDialog(null,
                            seriesLabel, "Series Favorites Management", JOptionPane.PLAIN_MESSAGE,
                            null, serieOptions, serieOptions[0]);

                    if (selectedSerieOption == null) {
                        backToMenu = true;
                        continue;
                    }

                    switch (selectedSerieOption) {
                        case "View favorites":
                            viewSeriesFavorites(user);
                            break;
                        case "Add favorite":
                            addSeriesFavorite(user);
                            break;
                        case "Remove favorite":
                            removeSeriesFavorite(user);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Invalid option. Please select a valid option.");
                            break;
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid option. Please select a valid option.");
                    break;
            }
        }
    }

    // Metodos que permiten ver al ususario sus "Movies" "Series"
    public void viewSeriesFavorites(User user) {

        if (user.getListSeriesFavorites().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No series favorites found.");
            return;
        }

        String[] seriesNames = new String[user.getListSeriesFavorites().size()];
        for (int i = 0; i < user.getListSeriesFavorites().size(); i++) {
            seriesNames[i] = user.getListSeriesFavorites().get(i).getName();
        }

        String selectedSeries = (String) JOptionPane.showInputDialog(null,
                "Select a series to view:", "View Series", JOptionPane.PLAIN_MESSAGE, null,
                seriesNames, seriesNames[0]);

        if (selectedSeries != null) {
            // Agregar la lógica para ver la información de la serie seleccionada
            JOptionPane.showMessageDialog(null, "Viewing series: " + selectedSeries);

            String[] buttons = { "View Description", "Watch Trailer", "Back" };
            int choice = JOptionPane.showOptionDialog(null, "What would you like to do?",
                    "Series Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    buttons, buttons[0]);

            switch (choice) {
                case 0:
                    // Ver descripción de la serie
                    Serie selectedSerieObj = null;
                    for (Serie serie : user.getListSeriesFavorites()) {
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
                    // Ver el tráiler de la película
                    ReproduccionFrame();
                    // Preguntar si desea realizar otra búsqueda o volver al menú anterior
                    String[] continueOptions = { "menu" };
                    JOptionPane.showOptionDialog(null, "back to menu", "Continue",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                            continueOptions, continueOptions[0]);
                    break;
                case 2:
                    // Volver al menú anterior
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid option. Please select a valid option.");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "You have canceled viewing the series.");
        }
    }

    public void viewMovieFavorites(User user) {

        if (user.getListMoviesFavorites().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No movie favorites found.");
            return;
        }

        String[] movieNames = new String[user.getListMoviesFavorites().size()];
        for (int i = 0; i < user.getListMoviesFavorites().size(); i++) {
            movieNames[i] = user.getListMoviesFavorites().get(i).getName();
        }

        String selectedMovie = (String) JOptionPane.showInputDialog(null, "Select a movie to view:",
                "View Movie", JOptionPane.PLAIN_MESSAGE, null, movieNames, movieNames[0]);

        if (selectedMovie != null) {
            // Aquí puedes agregar la lógica para ver la información de la película
            // seleccionada
            JOptionPane.showMessageDialog(null, "Viewing movie: " + selectedMovie);

            String[] buttons = { "View Description", "Watch Trailer", "Back" };
            int choice = JOptionPane.showOptionDialog(null, "What would you like to do?",
                    "Movie Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    buttons, buttons[0]);

            switch (choice) {
                case 0:
                    // Ver descripción de la película
                    Movie selectedMovieObj = null;
                    for (Movie movie : user.getListMoviesFavorites()) {
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
                    ReproduccionFrame();
                    // Preguntar si desea realizar otra búsqueda o volver al menú anterior
                    String[] continueOptions = { "menu" };
                    JOptionPane.showOptionDialog(null, "back to menu", "Continue",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                            continueOptions, continueOptions[0]);
                    break;
                case 2:
                    // Volver al menú anterior
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid option. Please select a valid option.");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "You have canceled viewing the movie.");
        }
    }

    // Métodos permiten al usuario añadir series y películas a su lista de favoritos
    public void addSeriesFavorite(User user) {
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
                    if (!user.getListSeriesFavorites().contains(serie)) {
                        user.getListSeriesFavorites().add(serie);
                        user.assignSerieToFavorite(user, serie);
                        controlerInitialMenuView.saveUsers(controlerInitialMenuView.getUsers(), "Users");
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

    public void addMovieFavorite(User user) {
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
                    if (!user.getListMoviesFavorites().contains(movie)) {
                        user.getListMoviesFavorites().add(movie);
                        user.assignMovieToFavorite(user, movie);
                        controlerInitialMenuView.saveUsers(controlerInitialMenuView.getUsers(), "Users");
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

    // Métodos permiten al usuario quitar series y películas de su lista de
    // favoritos.
    public void removeSeriesFavorite(User user) {
        ArrayList<Serie> seriesFavorites = new ArrayList<>();
        for (Object favorite : user.getListSeriesFavorites()) {
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
            user.deleteSerie(selectedSeries, user);
            user.getListSeriesFavorites().removeIf(favorite -> {
                if (favorite instanceof Serie) {
                    return ((Serie) favorite).getName().equals(selectedSeries);
                }
                return false;
            });
            JOptionPane.showMessageDialog(null,
                    "The series has been removed from favorites successfully.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "You have canceled removing the series from favorites.");
        }
    }

    public void removeMovieFavorite(User user) {
        ArrayList<Movie> movieFavorites = new ArrayList<>();
        for (Object favorite : user.getListMoviesFavorites()) {
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
            user.deleteMovie(selectedMovie, user);
            user.getListMoviesFavorites().removeIf(favorite -> {
                if (favorite instanceof Movie) {
                    return ((Movie) favorite).getName().equals(selectedMovie);

                }

                return false;
            });
            JOptionPane.showMessageDialog(null,
                    "The movie has been removed from favorites successfully.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "You have canceled removing the movie from favorites.");
        }
    }

    // Metodo que gestiona la configuracion de la cuenta del usuario
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
                        int emailRevi;
                        if (email.equals(user.getEmail())) {
                            emailRevi = 0;
                        } else {
                            emailRevi = controler.isEmailUnique(email);
                        }

                        int validePassworMin;
                        // valida que si modifico la contraseña
                        if (confirmPassword.equals(user.getPassword())) {
                            validePassworMin = 0;
                        } else {
                            // valida que la contraseña cumpla con lo minimo
                            validePassworMin = controler.validatePassword(confirmPassword);
                        }

                        // vreificar de que el cooreo no se repita
                        int uniqueEmail = controler.uniqueEmailUserRegister(email, user);
                        if (emailRevi == 0 && validePassworMin == 0 && uniqueEmail == 0) {
                            // crear usuario
                            controler.user(
                                    new User(firstName, lastName, email, password, Role.user));

                            // modificar al la lista de usuarios
                            user.setLastName(lastName);
                            user.setFirstName(firstName);
                            user.setEmail(email);
                            user.setPassword(confirmPassword);

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

                            JOptionPane.showMessageDialog(null,
                                    "la contraseña no comple con lo esperado");

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

    public void ReproduccionFrame() {
        // Ver el tráiler de la película
        SwingUtilities.invokeLater(() -> {
            JOptionPane pane = new JOptionPane();
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setIndeterminate(false);
            progressBar.setStringPainted(true);
            pane.setMessage(new Object[] { "Playing", progressBar });

            JDialog dialog = pane.createDialog("Playing");
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
                timer.stop();
            });
            closeTimer.setRepeats(false);
            closeTimer.start();

            dialog.setVisible(true);
        });
    }

}
