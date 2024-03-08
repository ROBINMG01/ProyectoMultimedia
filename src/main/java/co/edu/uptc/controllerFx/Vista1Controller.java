package co.edu.uptc.controllerFx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class Vista1Controller {

    public Vista1Controller() {
        this.opcionSeleccionada = new TextField();
    }

    @FXML
    private RadioButton opcion1;
    @FXML
    private RadioButton opcion2;
    @FXML
    private RadioButton opcion3;
    @FXML
    private RadioButton opcion4;
    @FXML
    private RadioButton opcion5;
    @FXML
    private RadioButton opcion6;
    @FXML
    private TextField opcionSeleccionada;
    @FXML
    private Button btnMultimedia;

    private ToggleGroup opcionesGroup = new ToggleGroup();

    public void initialize() {
        opcion1.setToggleGroup(opcionesGroup);
        opcion2.setToggleGroup(opcionesGroup);
        opcion3.setToggleGroup(opcionesGroup);
        opcion4.setToggleGroup(opcionesGroup);
        opcion5.setToggleGroup(opcionesGroup);
        opcion6.setToggleGroup(opcionesGroup);
    }

    @FXML
    private void aceptar() throws IOException {
        if (opcion1.isSelected()) {
            abrirVista("/co/edu/uptc/Fxml/MovieCatalogView.fxml");
        } else if (opcion2.isSelected()) {
            abrirVista("/co/edu/uptc/Fxml/SerieCatalogView.fxml");
        } else if (opcion3.isSelected()) {
            abrirVista("/co/edu/uptc/Fxml/Search.fxml");
            opcionSeleccionada.setText("Opción 3 seleccionada");
        } else if (opcion4.isSelected()) {
            opcionSeleccionada.setText("Opción 4 seleccionada");
        } else if (opcion5.isSelected()) {
            opcionSeleccionada.setText("Opción 5 seleccionada");
        } else if (opcion6.isSelected()) {
            opcionSeleccionada.setText("Opción 6 seleccionada");
        }
    }

    private void abrirVista(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = fxmlLoader.load();

            // Crear una nueva ventana para la vista
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Project Multimedia");
            stage.setScene(scene);
            stage.show();

            // Cerrar la ventana actual
            Stage myStage = (Stage) this.btnMultimedia.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            Logger.getLogger(Vista1Controller.class.getName()).severe(e.getMessage());
        }
    }
}