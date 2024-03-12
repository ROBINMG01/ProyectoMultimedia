package co.edu.uptc.controllerFx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import co.edu.uptc.model.Favorite;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

import java.lang.reflect.Type;
import java.io.IOException;

public class FavoritesController {

    @FXML
    private TableView<Favorite> tableMovieSerie;
    @FXML
    private TableView<Favorite> tableFavorite;
    @FXML
    private TableColumn<Favorite, String> nameColumn;
    @FXML
    private TableColumn<Favorite, String> durationColumn;
    @FXML
    private TableColumn<Favorite, String> genderColumn;
    @FXML
    private TableColumn<Favorite, String> typeColumn;
    @FXML
    private TableColumn<Favorite, String> nameFavorite1;
    @FXML
    private TableColumn<Favorite, String> durationFavorite1;
    @FXML
    private TableColumn<Favorite, String> genderFavorite1;
    @FXML
    private TableColumn<Favorite, String> typeFavorite1;
    @FXML
    private Button BtnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnBack;

    private ObservableList<Favorite> moviesAndSeries = FXCollections.observableArrayList();
    private ObservableList<Favorite> favorites = FXCollections.observableArrayList();

    private Object originalObject;

    private Gson gson;
    private User user;

    @FXML
    public void handleButtonBack(ActionEvent event) {
        abrirVista1();
    }

    public void setUser(User user) {
        this.user = user;
        loadFavoritesFromUser();
        loadMoviesAndSeries();
    }

    public FavoritesController() {
        this.gson = new Gson();
        this.user = Prueba.getInstance().getUser();
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        nameFavorite1.setCellValueFactory(new PropertyValueFactory<>("name"));
        durationFavorite1.setCellValueFactory(new PropertyValueFactory<>("duration"));
        genderFavorite1.setCellValueFactory(new PropertyValueFactory<>("gender"));
        typeFavorite1.setCellValueFactory(new PropertyValueFactory<>("type"));

        tableMovieSerie.setItems(moviesAndSeries);
        tableFavorite.setItems(favorites);

        BtnAdd.setOnAction(event -> {
            addFavorite();
            saveFavorites();
        });

        btnDelete.setOnAction(event -> {
            deleteFavorite();
            saveFavorites();
        });

        btnBack.setOnAction(event -> handleButtonBack(event));

        if (user != null) {
            setUser(user);
        }

    }

    private void saveFavorites() {
        // Carga todos los usuarios
        ArrayList<User> users = loadFromJson("src/main/java/co/edu/uptc/persistence/Users.json",
                new TypeToken<ArrayList<User>>() {
                }.getType());

        // Encuentra el índice del usuario actual en la lista de usuarios
        int userIndex = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(this.user.getEmail())) {
                userIndex = i;
                break;
            }
        }

        // Si el usuario actual se encontró en la lista de usuarios, actualiza sus
        // listas de favoritos
        if (userIndex != -1) {
            users.set(userIndex, this.user);
        }

        // Guarda todos los usuarios de nuevo en el archivo
        try (FileWriter writer = new FileWriter("src/main/java/co/edu/uptc/persistence/Users.json")) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar los favoritos: " + e.getMessage());
        }

        Prueba.getInstance().setUser(this.user);
    }

    private Movie convertFavoriteToMovie(Favorite favorite) {
        Movie movie = new Movie();
        movie.setName(favorite.getName());
        movie.setDuration(favorite.getDuration());
        movie.setGender(favorite.getGender());
        // Aquí puedes agregar cualquier otro campo que necesites copiar de Favorite a
        // Movie
        return movie;
    }

    private Serie convertFavoriteToSerie(Favorite favorite) {
        Serie serie = new Serie();
        serie.setName(favorite.getName());
        serie.setDuration(favorite.getDuration());
        serie.setGender(favorite.getGender());
        // Aquí puedes agregar cualquier otro campo que necesites copiar de Favorite a
        // Serie
        return serie;
    }

    private void loadFavoritesFromUser() {
        for (Movie movie : user.getListMoviesFavorites()) {
            favorites.add(convertMovieToFavorite(movie));
        }
        for (Serie serie : user.getListSeriesFavorites()) {
            favorites.add(convertSerieToFavorite(serie));
        }
    }

    private void loadMoviesAndSeries() {
        ArrayList<Movie> movies = loadFromJson("src/main/java/co/edu/uptc/persistence/Movie.json",
                new TypeToken<ArrayList<Movie>>() {
                }.getType());
        ArrayList<Serie> series = loadFromJson("src/main/java/co/edu/uptc/persistence/Series.json",
                new TypeToken<ArrayList<Serie>>() {
                }.getType());

        for (Movie movie : movies) {
            Favorite favorite = convertMovieToFavorite(movie);
            moviesAndSeries.add(favorite);
        }
        for (Serie serie : series) {
            Favorite favorite = convertSerieToFavorite(serie);
            moviesAndSeries.add(favorite);
        }
        tableMovieSerie.refresh();
        tableFavorite.refresh();
    }

    private <T> ArrayList<T> loadFromJson(String filePath, Type type) {
        ArrayList<T> list = new ArrayList<>();
        try {
            list = gson.fromJson(new FileReader(filePath), type);
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return list;
    }

    private Favorite convertMovieToFavorite(Movie movie) {
        Favorite favorite = new Favorite();
        favorite.setName(movie.getName());
        favorite.setDuration(movie.getDuration());
        favorite.setGender(movie.getGender());
        favorite.setType("Película");
        favorite.setOriginalObject(movie); // Fix: Use the correct setter method name
        return favorite;
    }

    private Favorite convertSerieToFavorite(Serie serie) {
        Favorite favorite = new Favorite();
        favorite.setName(serie.getName());
        favorite.setDuration(serie.getDuration());
        favorite.setGender(serie.getGender());
        favorite.setType("Serie");
        return favorite;
    }

    private void addFavorite() {
        Favorite selectedFavorite = tableMovieSerie.getSelectionModel().getSelectedItem();
        if (selectedFavorite != null) {
            favorites.add(selectedFavorite);
            moviesAndSeries.remove(selectedFavorite);
            // Actualiza el objeto User con las nuevas películas y series favoritas
            if (selectedFavorite.getType().equals("Película")) {
                user.getListMoviesFavorites().add(convertFavoriteToMovie(selectedFavorite));
            } else if (selectedFavorite.getType().equals("Serie")) {
                user.getListSeriesFavorites().add(convertFavoriteToSerie(selectedFavorite));
            }
        }
    }

    private void deleteFavorite() {
        Favorite selectedFavorite = tableFavorite.getSelectionModel().getSelectedItem();
        if (selectedFavorite != null) {
            favorites.remove(selectedFavorite);
            moviesAndSeries.add(selectedFavorite);

            // Actualizar el objeto User con los favoritos eliminados
            if (selectedFavorite.getType().equals("Película")) {
                List<Movie> movieFavorites = user.getListMoviesFavorites();
                movieFavorites = movieFavorites.stream()
                        .filter(movie -> !movie.getName().equals(selectedFavorite.getName()))
                        .collect(Collectors.toList());
                user.setListMoviesFavorites(new ArrayList<>(movieFavorites));
            } else if (selectedFavorite.getType().equals("Serie")) {
                List<Serie> serieFavorites = user.getListSeriesFavorites();
                serieFavorites = serieFavorites.stream()
                        .filter(serie -> !serie.getName().equals(selectedFavorite.getName()))
                        .collect(Collectors.toList());
                user.setListSeriesFavorites(new ArrayList<>(serieFavorites));
            }
            saveFavorites();
        }
    }

    // private void viewTrailer() {
    // Favorite selectedFavorite =
    // tableMovieSerie.getSelectionModel().getSelectedItem();
    // if (selectedFavorite != null) {
    // // Aquí puedes agregar la lógica para ver el tráiler de la serie o película
    // // seleccionada
    // // Por ejemplo, podrías abrir un nuevo navegador con la URL del tráiler
    // }
    // }

    private void abrirVista1() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Vista1.fxml"));

            Parent vista1View = fxmlLoader.load();

            // Crear una nueva ventana para la vista 1
            Stage stage = new Stage();
            stage.setTitle("Vista 1");
            stage.setScene(new Scene(vista1View));
            stage.show();

            // Cerrar la ventana actual
            Stage currentStage = (Stage) this.btnBack.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}