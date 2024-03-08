package co.edu.uptc.controllerFx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import co.edu.uptc.model.Movie;
import java.lang.reflect.Type;

public class SearchMovieController {

    @FXML
    private TextField searchName;

    @FXML
    private Button btnView;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView<Movie> tableMovie;

    @FXML
    private TableColumn<Movie, String> nameMovie;

    @FXML
    private TableColumn<Movie, String> genderMovie;

    @FXML
    private TableColumn<Movie, String> durationMovie;

    @FXML
    private TableColumn<Movie, String> descriptionMovie;
    private ObservableList<Movie> allMovies;

    @FXML
    public void initialize() {
        // Configura las celdas de la tabla para mostrar los datos de las películas
        nameMovie.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderMovie.setCellValueFactory(new PropertyValueFactory<>("gender"));
        durationMovie.setCellValueFactory(new PropertyValueFactory<>("duration"));
        descriptionMovie.setCellValueFactory(new PropertyValueFactory<>("description"));

        searchName.textProperty().addListener((observable, oldValue, newValue) -> searchMovies());
        loadMovies();
    }

    @FXML
    protected void handleButton(ActionEvent event) {
        abrirVista1();
    }

    private void abrirVista1() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Search.fxml"));
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

    private void loadMovies() {
        Gson gson = new Gson();
        Type movieListType = new TypeToken<ArrayList<Movie>>() {
        }.getType();

        try (FileReader reader = new FileReader("src\\main\\java\\co\\edu\\uptc\\persistence\\Movie.json")) {
            // Convert JSON File to Java Object
            ArrayList<Movie> movieList = gson.fromJson(reader, movieListType);
            if (allMovies == null) {
                allMovies = FXCollections.observableArrayList();
            } else {
                allMovies.clear();
            }
            allMovies.addAll(movieList);
            tableMovie.setItems(allMovies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchMovies() {
        String searchText = searchName.getText().toLowerCase();

        ObservableList<Movie> filteredMovies = FXCollections.observableArrayList(
                allMovies.stream()
                        .filter(movie -> movie.getName().toLowerCase().contains(searchText))
                        .collect(Collectors.toList()));

        tableMovie.setItems(filteredMovies);
    }
}
