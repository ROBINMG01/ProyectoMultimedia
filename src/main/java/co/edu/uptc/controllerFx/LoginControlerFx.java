package co.edu.uptc.controllerFx;

import java.io.IOException;

import co.edu.uptc.controller.ControlerInitialMenuView;
import co.edu.uptc.model.Role;
import co.edu.uptc.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginControlerFx {
    private ControlerInitialMenuView controlerInitialMenuView;
    @FXML
    private Label error;
    @FXML
    private Button register;

    @FXML
    private Button initial;
    @FXML
    private Button login;

    @FXML
    private Button visit;

    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;

    private Vista1Controller vista1Controller;

    @FXML
    public void initialize() {

        this.controlerInitialMenuView = new ControlerInitialMenuView();

        try {
            controlerInitialMenuView.getUsers().addAll(controlerInitialMenuView.loadUsers("Users"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void login() {
        if (!userField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            User userr = controlerInitialMenuView.findUserByEmail(userField.getText());
            if (userr != null) {
                if (userr.getPassword().equals(passwordField.getText())) {

                    if (userr.getRole() == Role.user) {
                        Prueba.getInstance().setUser(userr);
                        try {
                            // Vista de Login
                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("/co/edu/uptc/Fxml/Vista1.fxml"));
                            Parent root1 = loader.load();

                            Vista1Controller controller = loader.getController();
                            controller.setUser(userr);

                            // Mostrar la vista que desees, por ejemplo Vista1
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root1));
                            stage.show();

                            Stage myStage = (Stage) this.visit.getScene().getWindow();
                            myStage.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (userr.getRole() == Role.admin) {
                        // vistaaaaaaaaaaaaaaa de adminnnnnnnnnnnnnnnnnnn
                        // av.menuAdmin();

                        // Cargar la vista de login.fxml
                        try {
                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("/co/edu/uptc/Fxml/ListMoviesAdmin.fxml"));
                            Parent root = loader.load();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();

                            Stage myStage = (Stage) this.register.getScene().getWindow();
                            myStage.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                } else {
                    controlerInitialMenuView.setUserEmail(userField.getText());
                    // Cargar la vista de login modificadaa para la contrasenia equivocada
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/co/edu/uptc/Fxml/LoginInitialFx.fxml"));
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        TextField userField = (TextField) loader.getNamespace().get("userField");
                        PasswordField passwordField = (PasswordField) loader.getNamespace().get("passwordField");
                        // Agregar la clase de estilo
                        passwordField.getStyleClass().add("password-field");
                        String userCargado = controlerInitialMenuView.getUserLogin();
                        Label error = (Label) loader.getNamespace().get("error");
                        error.setText("Incorrect password");
                        userField.setText(userCargado);
                        stage.setScene(new Scene(root));
                        stage.show();

                        Stage myStage = (Stage) this.register.getScene().getWindow();
                        myStage.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            } else {

                // JOptionPane.showMessageDialog(null,
                // "there is no registered user");

                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/co/edu/uptc/Fxml/LoginInitialFx.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    // se tienen los objetos a modificar
                    TextField userField = (TextField) loader.getNamespace().get("userField");
                    PasswordField passwordField = (PasswordField) loader.getNamespace().get("passwordField");
                    Label error = (Label) loader.getNamespace().get("error");
                    error.setText("there is no registered user");
                    // Agregar la clase de estilo
                    passwordField.getStyleClass().add("passwordInitial-field");
                    userField.getStyleClass().add("password-field");

                    stage.setScene(new Scene(root));
                    stage.show();

                    Stage myStage = (Stage) this.register.getScene().getWindow();
                    myStage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else

        {

            // JOptionPane.showMessageDialog(null,
            // "I don't fill in the spaces correctly ");

            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/co/edu/uptc/Fxml/LoginInitialFx.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                // se tienen los objetos a modificar
                TextField userField = (TextField) loader.getNamespace().get("userField");
                PasswordField passwordField = (PasswordField) loader.getNamespace().get("passwordField");
                Label error = (Label) loader.getNamespace().get("error");
                error.setText("I don't fill in the spaces correctly ");
                // Agregar la clase de estilo
                passwordField.getStyleClass().add("password-field");
                userField.getStyleClass().add("password-field");

                stage.setScene(new Scene(root));
                stage.show();

                Stage myStage = (Stage) this.register.getScene().getWindow();
                myStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

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

    @FXML
    private void handleInitialButton() {
        // Cargar la vista de login.fxml
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/vistaInitialMenu.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage myStage = (Stage) this.register.getScene().getWindow();
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
