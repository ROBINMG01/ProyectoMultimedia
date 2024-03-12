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
    private Button btnTrailer;
    @FXML
    private Button btnBack;

    private ObservableList<Favorite> moviesAndSeries = FXCollections.observableArrayList();
    private ObservableList<Favorite> favorites = FXCollections.observableArrayList();

    private Gson gson;
    private User user;

    public FavoritesController() {
        gson = new Gson();
    }

    @FXML
    public void handleButtonBack(ActionEvent event) {
        abrirVista1();
    }

    public void setUser(User user) {
        this.user = user;
        loadMoviesAndSeries();
    }

    @FXML
    public void initialize() {
        // Configurar los CellValueFactory
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

        BtnAdd.setOnAction(event -> addFavorite());
        btnDelete.setOnAction(event -> deleteFavorite());
        btnTrailer.setOnAction(event -> viewTrailer());
    }

    private void saveFavorites() {
        ArrayList<Movie> favoriteMovies = favorites.stream()
                .filter(favorite -> favorite.getType().equals("Película"))
                .map(this::convertFavoriteToMovie)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Serie> favoriteSeries = favorites.stream()
                .filter(favorite -> favorite.getType().equals("Serie"))
                .map(this::convertFavoriteToSerie)
                .collect(Collectors.toCollection(ArrayList::new));

        user.setListMoviesFavorites(favoriteMovies);
        user.setListSeriesFavorites(favoriteSeries);

        try (FileWriter writer = new FileWriter("co/edu/uptc/persistence/Users.json")) {
            gson.toJson(user, writer);
        } catch (IOException e) {
            e.getMessage();
        }
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
    }

    private <T> ArrayList<T> loadFromJson(String filePath, Type type) {
        ArrayList<T> list = new ArrayList<>();
        try {
            list = gson.fromJson(new FileReader(filePath), type);
        } catch (FileNotFoundException e) {
            System.err.println("No se pudo cargar el archivo: " + e.getMessage());
        }
        return list;
    }

    private Favorite convertMovieToFavorite(Movie movie) {
        Favorite favorite = new Favorite();
        favorite.setName(movie.getName());
        favorite.setDuration(movie.getDuration());
        favorite.setGender(movie.getGender());
        favorite.setType("Película");
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
        }
    }

    private void deleteFavorite() {
        Favorite selectedFavorite = tableFavorite.getSelectionModel().getSelectedItem();
        if (selectedFavorite != null) {
            favorites.remove(selectedFavorite);
            moviesAndSeries.add(selectedFavorite);
        }
    }

    private void viewTrailer() {
        Favorite selectedFavorite = tableMovieSerie.getSelectionModel().getSelectedItem();
        if (selectedFavorite != null) {
            // Aquí puedes agregar la lógica para ver el tráiler de la serie o película
            // seleccionada
            // Por ejemplo, podrías abrir un nuevo navegador con la URL del tráiler
        }
    }

    private void abrirVista1() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Vista1.fxml"));

            Vista1Controller vista1Controller = new Vista1Controller();
            fxmlLoader.setController(vista1Controller);

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