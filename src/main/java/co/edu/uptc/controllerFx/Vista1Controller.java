package co.edu.uptc.controllerFx;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;
import co.edu.uptc.model.User;

public class Vista1Controller {

    @FXML
    private Button option1;
    @FXML
    private Button option2;
    @FXML
    private Button option3;
    @FXML
    private Button option4;
    @FXML
    private Button option5;
    @FXML
    private Button option6;
    @FXML
    private TextField opcionSeleccionada;

    @FXML
    private Button btnBack;

    private User user;

    @FXML
    private void initialize() {
        option1.setOnAction(event -> abrirVista("/co/edu/uptc/Fxml/MovieCatalogView.fxml",  new MovieCatalogController(user)));
        option2.setOnAction(event -> abrirVista("/co/edu/uptc/Fxml/SerieCatalogView.fxml" , new SerieCatalogController(user)));
        option3.setOnAction(event -> abrirVista("/co/edu/uptc/Fxml/Search.fxml", new SearchController(user)));
        option4.setOnAction(event -> abrirVista("/co/edu/uptc/Fxml/Favorites.fxml", new FavoritesController(user) ));
        option5.setOnAction(event -> abrirVista("/co/edu/uptc/Fxml/Settings.fxml", new SettingsController(user)));
        option6.setOnAction(event -> abrirVista("/co/edu/uptc/Fxml/Suscription.fxml", new SuscriptionController(user)));
    }
    @FXML
    public void handleButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/vistaInitialMenu.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage myStage = (Stage) this.btnBack.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Vista1Controller() {
        this.user = Prueba.getInstance().getUser();
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void abrirVista(String fxmlPath, Object  controller) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("User Register");
            stage.setScene(scene);
            stage.show();
            // Cerrar la ventana actual
            Stage myStage = (Stage) this.option1.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            Logger.getLogger(Vista1Controller.class.getName()).severe(e.getMessage());
        }
    }
}