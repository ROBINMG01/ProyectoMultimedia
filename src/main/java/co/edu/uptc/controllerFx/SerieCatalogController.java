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
    private Button btnBack;

    @FXML
    private ListView<Serie> serieList;

    Serie serie = new Serie();

    private User user;

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

    public SerieCatalogController(User user) {
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
            Parent SerieCatalogView = fxmlLoader.load();

            // Crear una nueva ventana para la vista del catálogo de series
            Stage stage = new Stage();
            stage.setTitle("Catálogo de Series");
            stage.setScene(new Scene(SerieCatalogView));
            stage.show();
            // Vamos a colocar esta parte del codigo para que se cierre la ventana
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
            // Vamos a colocar esta parte del codigo para que se cierre la ventana
            Stage myStage = (Stage) this.btnBack.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            Logger.getLogger(Vista1Controller.class.getName()).severe(e.getMessage());
        }

    }
}