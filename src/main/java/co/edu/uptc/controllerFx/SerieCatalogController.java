package co.edu.uptc.controllerFx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
// import javafx.scene.media.Media;
// import javafx.scene.media.MediaPlayer;
// import javafx.scene.media.MediaView;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Serie;

public class SerieCatalogController {

    @FXML
    protected void handleButton(ActionEvent event) {
        abrirVista1();
    }

    // @FXML
    // private MediaView videoSerie;

    private AdminController adminController;

    @FXML
    private Button btnBack;

    @FXML
    private ListView<Serie> serieList;

    Serie serie = new Serie();

    public void initialize() {
        adminController = new AdminController();
        List<Serie> series = adminController.loadSerie("Series");
        serieList.getItems().setAll(series);

        serieList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                abrirDetalleSerie(newSelection);
            }
        });
    }

    private void abrirDetalleSerie(Serie serie) {
        try {
            // Cargar la vista de detalles de la serie
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/DetalleSerie.fxml"));
            Parent detalleSerieView = fxmlLoader.load();

            // Obtener el controlador de la vista de detalles de la serie y pasarle la serie
            // seleccionada
            DetalleSerieController controller = fxmlLoader.getController();
            controller.setSerie(serie);

            // Crear una nueva ventana para la vista de detalles de la serie
            Stage stage = new Stage();
            stage.setTitle("Detalles de la serie");
            stage.setScene(new Scene(detalleSerieView));
            stage.show();
            System.out.println("URL del video: " + serie.getVideoUrl());

            // // Verificar que videoSerie no es null
            // if (videoSerie == null) {
            //     System.out.println("videoSerie es null");
            // } else {
            //     System.out.println("videoSerie no es null");
            // }

            // // Reproducir el video de la serie seleccionada
            // Media media = new Media(serie.getVideoUrl());
            // MediaPlayer mediaPlayer = new MediaPlayer(media);
            // videoSerie.setMediaPlayer(mediaPlayer);
            // mediaPlayer.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVista1() {
        try {
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
