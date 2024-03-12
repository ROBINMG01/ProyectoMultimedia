package co.edu.uptc.controllerFx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;
import javafx.scene.Node;

public class SerieCatalogController {

    @FXML
    protected void handleButton(ActionEvent event) {
        abrirVista1();
    }

    @FXML
    private ImageView serieImageView;

    @FXML
    private Label labelDescription;

    private AdminController adminController;

    @FXML
    private Button btnBack, btnViewButton;

    @FXML
    private ListView<Serie> serieList;

    Serie serie = new Serie();

    private User user;

    public SerieCatalogController() {
        this.btnViewButton = new Button();
    }

    public SerieCatalogController(User user) {
    }

    public void initialize() {

        serieList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Image image = null;
                if (newSelection.getImageUrl() != null) {
                    if (newSelection.getImageUrl().startsWith("http")) {
                        image = new Image(newSelection.getImageUrl());
                    } else {
                        image = new Image(getClass().getResourceAsStream(newSelection.getImageUrl()));
                    }
                }
                if (image != null) {
                    serieImageView.setImage(image);
                    serieImageView.setFitWidth(324); // Ajusta el ancho a 324
                    serieImageView.setFitHeight(267); // Ajusta la altura a 267
                    serieImageView.setPreserveRatio(true); // Mantiene la relación de aspecto
                }
            }
        });

        adminController = new AdminController();
        List<Serie> series = adminController.loadSerie("Series");
        serieList.getItems().setAll(series);

        serieList.setCellFactory(param -> new ListCell<Serie>() {
            @Override
            protected void updateItem(Serie serie, boolean empty) {
                super.updateItem(serie, empty);

                if (empty || serie == null) {
                    setText(null);
                } else {
                    setText(serie.getName() + ", " + serie.getDuration() + ", " + serie.getDescription());
                }
            }
        });

        serieList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                serieImageView.setImage(new Image(newSelection.getImageUrl()));
                labelDescription.setWrapText(true);
                labelDescription.setText(newSelection.getDescription());
            }
        });
    }

    @FXML
    private void handleViewButton(ActionEvent event) {
        Serie selectedSerie = serieList.getSelectionModel().getSelectedItem();
        if (selectedSerie != null) {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            openChapterListView(selectedSerie, currentStage);
        }
    }

    public void openChapterListView(Serie serie, Stage currentStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/ChapterListView.fxml"));
            ChapterListController controller = new ChapterListController();
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load(); // Esto carga el archivo FXML y inicializa los campos anotados con @FXML

            controller.setSerie(serie); // Ahora puedes llamar a setSerie()

            currentStage.close();

            // Aquí creas una nueva escena y la muestras en una nueva ventana
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleVerPeliculaButton() {
        Serie selectedMovie = serieList.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            String selectedMovieUrl = selectedMovie.getVideoUrl();
            if (selectedMovieUrl != null) {
                openMovieWindow(selectedMovieUrl);
            } else {
            }
        }
    }

    private void openMovieWindow(String selectedMovieName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/SerieSerie.fxml"));
            Parent root = loader.load();

            // Puedes pasar información adicional al controlador de la nueva ventana si es
            // necesario
            VideoController videoController = loader.getController();
            videoController.playVideo(selectedMovieName); // Suponiendo que tengas un método setMovieName en el
                                                          // controlador de la película

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Catalog Movie");
            stage.setScene(scene);
            stage.show();

            // Cerrar la ventana actual
            Stage myStage = (Stage) this.btnViewButton.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Agrega este método setter
    public void setUser(User user) {
        this.user = user;
    }

    private void abrirVista1() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Vista1.fxml"));
            Parent root = fxmlLoader.load();

            Vista1Controller controller = fxmlLoader.getController();
            controller.setUser(this.user); // Ahora puedes llamar a setUser()

            Stage stage = new Stage();
            stage.setTitle("Catálogo de Series");
            stage.setScene(new Scene(root));
            stage.show();

            Stage myStage = (Stage) this.btnBack.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeWindows() {
        try {
            // Cargar la vista del catálogo de películas
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Vista1.fxml"));
            Parent root = fxmlLoader.load();

            // Crear una nueva ventana para la vista del catálogo de películas
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            // Vamos a colocar esta parte del codigo para que se cierre la ventana
            Stage myStage = (Stage) this.btnBack.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            Logger.getLogger(Vista1Controller.class.getName()).severe(e.getMessage());
        }

    }
}