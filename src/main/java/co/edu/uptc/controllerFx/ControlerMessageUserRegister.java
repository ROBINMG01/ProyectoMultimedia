package co.edu.uptc.controllerFx;

import java.io.IOException;

import co.edu.uptc.controller.ControlerInitialMenuView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ControlerMessageUserRegister {

    @FXML
   private  Button play;


    @FXML
    public void register() {
           // Cargar la vista de login.fxml
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/LoginInitialFx.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            PasswordField passwordField = (PasswordField) loader.getNamespace().get("passwordField");
            // Agregar la clase de estilo
            passwordField.getStyleClass().add("passwordInitial-field");
            stage.setScene(new Scene(root));
            stage.show();

            Stage myStage = (Stage) this.play.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
   

    }
    
}
