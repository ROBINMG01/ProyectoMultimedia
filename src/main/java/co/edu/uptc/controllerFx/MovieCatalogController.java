package co.edu.uptc.controllerFx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.User;

public class MovieCatalogController {
    @FXML
    protected void handleButton(ActionEvent event) {
        abrirVista1();
    }

    private AdminController adminController;

    @FXML
    private Button btnBack;

    @FXML
    private ListView<Movie> movieList;

    @FXML
    private ImageView imageVideo;

    @FXML
    private Label labelDescription;

    private User user;

    public void initialize() {
        adminController = new AdminController();
        List<Movie> movies = adminController.loadMovie("Movie");
        movieList.getItems().setAll(movies);

        movieList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
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
                    imageVideo.setImage(image);

                }
                labelDescription.setWrapText(true);
                labelDescription.setText(newSelection.getDescription());
            }
        });
    }

    public MovieCatalogController(User user) {
        this.user = Prueba.getInstance().getUser();
    }

public void setUser(User user){
    this.user = user;
    }
    private void abrirVista1() {
        try {
            if (user != null) {
                System.out.println(user.getEmail());
            } else {
                System.out.println("User is null");
            }
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

    public void closeWindows() {
        try {
            if (user != null) {
                System.out.println(user.getEmail());
            } else {
                System.out.println("User is null");
            }
            // Cargar la vista del catálogo de películas
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Vista1.fxml"));
            Parent root = fxmlLoader.load();

            // Crear una nueva ventana para la vista del catálogo de películas
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            // Vamos a colocar esta parte del codigo para que ce cierre la ventana
            Stage myStage = (Stage) this.btnBack.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            Logger.getLogger(Vista1Controller.class.getName()).severe(e.getMessage());
        }

    }
}