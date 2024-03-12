package co.edu.uptc.controllerFx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.User;

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
    private Label labelDescription;

    @FXML
    private ImageView imageMovie;

    private User user;

    private AdminController adminController;

    @FXML
    private TableColumn<Movie, String> descriptionMovie;
    private ObservableList<Movie> allMovies;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {

        allMovies = FXCollections.observableArrayList(); // Inicializar lista observable

        // Configura las celdas de la tabla para mostrar los datos de las películas
        nameMovie.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderMovie.setCellValueFactory(new PropertyValueFactory<>("gender"));
        durationMovie.setCellValueFactory(new PropertyValueFactory<>("duration"));

        searchName.textProperty().addListener((observable, oldValue, newValue) -> searchMovies());

        // Ajusta el tamaño del Label
        labelDescription.setPrefWidth(200); // Cambia esto al ancho que desees

        // Habilita el ajuste de texto
        labelDescription.setWrapText(true);

        // Agrega un ChangeListener a la propiedad selectedItem de la tabla
        tableMovie.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                labelDescription.setText(newValue.getDescription());
                imageMovie.setImage(new Image(newValue.getImageUrl()));
            }
        });

        adminController = new AdminController();
        List<Movie> movies = adminController.loadMovie("Movie");
        allMovies.addAll(movies); // Añade las películas a la lista observable
        tableMovie.setItems(allMovies);
    }

    public SearchMovieController(User user) {
        this.user = Prueba.getInstance().getUser();
    }

    public SearchMovieController() {
    }

    @FXML
    protected void handleButton(ActionEvent event) {
        abrirVista1();
    }

    @FXML
    protected void handleViewButton(ActionEvent event) {
        Movie selectedMovie = tableMovie.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            // Abrir la ventana del reproductor de video
            abrirVideoPlayer(selectedMovie.getVideoUrl()); // Reemplaza "url" con el atributo que almacena la URL del video
                                                      // en la clase Movie
        }
    }

    private void abrirVideoPlayer(String videoPath) {
        try {
            // Crear una nueva ventana para el reproductor de video
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Video.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Reproductor de video");
            stage.setScene(new Scene(root));

            // Obtener el controlador del video
            VideoController videoController = loader.getController();

            // Pasar la ruta del video al controlador
            videoController.playVideo(videoPath);

            // Establecer la ventana para cerrar el reproductor al cerrarla
            videoController.setStage(stage);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVista1() {
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

    @FXML
    private void searchMovies() {
        String searchText = searchName.getText().toLowerCase();

        ObservableList<Movie> filteredMovies = FXCollections.observableArrayList(
                allMovies.stream()
                        .filter(movie -> movie.getName().toLowerCase().contains(searchText))
                        .collect(Collectors.toList()));

        tableMovie.setItems(filteredMovies);
    }

    public void closeWindows() {
        try {
            // Cargar la vista del catálogo de películas
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Search.fxml"));
            Parent root = fxmlLoader.load();

            // Crear una nueva ventana para la vista del catálogo de películas
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            // Vamos a colocar esta parte del codigo para que ce cierre la ventana
            Stage currentStage = (Stage) btnBack.getScene().getWindow();
            currentStage.close();
            ;

        } catch (IOException e) {
            Logger.getLogger(Vista1Controller.class.getName()).severe(e.getMessage());
        }

    }

}
