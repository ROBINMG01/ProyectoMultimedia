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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.User;
import javafx.scene.Node;

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
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        abrirVista1(currentStage);
    }

    @FXML
    protected void handleViewButton(ActionEvent event) {
        Movie selectedMovie = tableMovie.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Video.fxml"));
                VideoController videoController = new VideoController();
                loader.setController(videoController);
                Parent videoView = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Reproductor de video");
                stage.setScene(new Scene(videoView));

                videoController = loader.getController();
                videoController.playVideo(selectedMovie.getVideoUrl());
                videoController.setOriginalView("SearchMovieController"); // Aquí

                ((Node)(event.getSource())).getScene().getWindow().hide();

                stage.show();            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void abrirVideoPlayer(String videoPath) {
        try {
            // Crear una nueva ventana para el reproductor de video
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Video.fxml"));
            VideoController videoController = new VideoController();
            loader.setController(videoController);
            Parent videoView = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Reproductor de video");
            stage.setScene(new Scene(videoView));

            // Obtener el controlador del video
            VideoController videoController1 = loader.getController();

            // Pasar la ruta del video al controlador
            videoController.playVideo(videoPath);

            // Establecer la ventana para cerrar el reproductor al cerrarla
            videoController.setStage(stage);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Stage vista1Stage = null; // Guarda una referencia a la ventana de Vista1

    private void abrirVista1(Stage currentStage) {
        try {
            // Comprueba si la ventana ya existe
            if (vista1Stage == null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Vista1.fxml"));
                Parent movieCatalogView = fxmlLoader.load();

                // Crear una nueva ventana para la vista del catálogo de películas
                vista1Stage = new Stage();
                vista1Stage.setTitle("Catálogo de películas");
                vista1Stage.setScene(new Scene(movieCatalogView));
            }

            currentStage.close(); // Cierra la ventana actual

            vista1Stage.show();

            vista1Stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void searchMovies() {
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
            SearchController searchController = new SearchController(user);
            fxmlLoader.setController(searchController);
            Parent searchView = fxmlLoader.load();

            // Crear una nueva ventana para la vista del catálogo de películas
            Scene scene = new Scene(searchView);
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
