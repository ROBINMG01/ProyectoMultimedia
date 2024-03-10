package co.edu.uptc.controllerFx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import co.edu.uptc.model.Favorite;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

import java.lang.reflect.Type;

public class FavoritesController {

    @FXML
    private ListView<Favorite> listFavorite;

    @FXML
    private TableView<Favorite> favoriteTable;

    @FXML
    private Button btnVisualice, btnDelete, BtnAdd, btnView, btnBack;

    @FXML
    private TableColumn<Favorite, String> nameFavorite, durationFavorite, genderFavorite, typeFavorite;

    private ObservableList<Favorite> favorites = FXCollections.observableArrayList();

    @FXML
    private ListView<ObservableList<Favorite>> listFavoriteGroups;

    private Gson gson;

    @FXML
    public void initialize() {
        loadUsers();
        setupButtonListeners();
    }

    private void loadUsers() {
        Type userListType = new TypeToken<ArrayList<User>>() {
        }.getType();
        ArrayList<User> userList = null;
        try {
            userList = gson.fromJson(new FileReader("src/main/java/co/edu/uptc/persistence/Users.json"), userListType);
        } catch (FileNotFoundException e) {
            // Maneja la excepción de una manera más significativa
            System.err.println("No se pudo cargar el archivo de usuarios: " + e.getMessage());
            return;
        }
        convertUsersToFavorites(userList);
        listFavorite.setItems(favorites);
        favoriteTable.setItems(favorites);
    }

    private void convertUsersToFavorites(ArrayList<User> userList) {
        for (User user : userList) {
            ArrayList<Movie> favoriteMovies = user.getListMoviesFavorites();
            ArrayList<Serie> favoriteSeries = user.getListSeriesFavorites();
            for (Movie movie : favoriteMovies) {
                Favorite favorite = convertMovieToFavorite(movie);
                favorites.add(favorite);
            }
            for (Serie serie : favoriteSeries) {
                Favorite favorite = convertSerieToFavorite(serie);
                favorites.add(favorite);
            }
        }
    }

    private void setupButtonListeners() {
        btnVisualice.setOnAction(event -> visualiceFavorite());
        btnDelete.setOnAction(event -> deleteFavorite());
        BtnAdd.setOnAction(event -> addFavorite());
        btnView.setOnAction(event -> viewFavorite());
        btnBack.setOnAction(event -> goBack());
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

    private void visualiceFavorite() {
        // Obtén el favorito seleccionado
        Favorite selectedFavorite = favoriteTable.getSelectionModel().getSelectedItem();
        if (selectedFavorite != null) {
            // Visualiza el favorito seleccionado
            System.out.println("Visualizando favorito: " + selectedFavorite);
        }
    }

    private void deleteFavorite() {
        // Obtén el favorito seleccionado
        Favorite selectedFavorite = favoriteTable.getSelectionModel().getSelectedItem();
        if (selectedFavorite != null) {
            // Elimina el favorito seleccionado
            favorites.remove(selectedFavorite);
        }
    }

    private void addFavorite() {
        // Crea un nuevo favorito
        Favorite newFavorite = new Favorite();
        // Añade el nuevo favorito a la lista
        favorites.add(newFavorite);
    }

    private void viewFavorite() {
        // Obtén el favorito seleccionado
        Favorite selectedFavorite = favoriteTable.getSelectionModel().getSelectedItem();
        if (selectedFavorite != null) {
            // Visualiza el favorito seleccionado
            System.out.println("Visualizando favorito: " + selectedFavorite);
        }
    }

    private void goBack() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Vista1.fxml"));
            Parent movieCatalogView = fxmlLoader.load();

            // Crear una nueva ventana para la vista del catálogo de películas
            Stage stage = new Stage();
            stage.setTitle("Catálogo de películas");
            stage.setScene(new Scene(movieCatalogView));
            stage.show();

            Stage myStage = (Stage) this.btnBack.getScene().getWindow();

            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}