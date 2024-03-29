package co.edu.uptc.controllerFx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InitialMenuControllerFx {

    @FXML
    private Button register;
    @FXML
    private Button login;

    @FXML
    private Button visit;;

    @FXML
    private void handleRegistroButton() {
        // Cargar la vista de login.fxml
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/RegisterInitial.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            // para que la contraseña sea blanca
            PasswordField passwordField = (PasswordField) loader.getNamespace().get("passwordField");
            // modificar para ver los cambios
            passwordField.getStyleClass().add("passwordInitial-field");
            PasswordField confirmPasswordField = (PasswordField) loader.getNamespace().get("confirmPasswordField");
            // modificar para ver los cambios
            confirmPasswordField.getStyleClass().add("passwordInitial-field");
            stage.setScene(new Scene(root));
            stage.show();

            Stage myStage = (Stage) this.register.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoginButton() {
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

            Stage myStage = (Stage) this.login.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleVisitanteButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/VisitView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
           
            stage.setScene(new Scene(root));
            stage.show();
          

            Stage myStage = (Stage) this.visit.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Button Clicked");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
