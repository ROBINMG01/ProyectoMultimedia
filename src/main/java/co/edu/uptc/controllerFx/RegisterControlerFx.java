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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegisterControlerFx {
    private ControlerInitialMenuView controlerInitialMenuView;
    @FXML
    private Label error;
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private Button register;
    @FXML
    private Button login;

    @FXML
    private Button visit;

    @FXML
    private Button register2;

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
    private void handleRegisterButtonClick() {

        if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() &&
                !emailField.getText().isEmpty() && !passwordField.getText().isEmpty() &&
                !confirmPasswordField.getText().isEmpty()) {

            if (passwordField.getText().equals(confirmPasswordField.getText())) {
                int emailRevi = this.controlerInitialMenuView.isEmailUnique(emailField.getText());
                int validePassworMin = controlerInitialMenuView.validatePassword(confirmPasswordField.getText());
                int uniqueEmail = controlerInitialMenuView.uniqueEmail(emailField.getText());

                if (emailRevi == 0 && validePassworMin == 0 && uniqueEmail == 0) {
                    controlerInitialMenuView
                            .user(new User(firstNameField.getText(), lastNameField.getText(), emailField.getText(),
                                    passwordField.getText(), Role.user));
                    controlerInitialMenuView.userRegister();
                    showAlert("Registered user");
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/co/edu/uptc/Fxml/RegisterInitial.fxml"));
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                        Stage myStage = (Stage) this.register.getScene().getWindow();
                        myStage.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (uniqueEmail == 2) {
                    /// error por el email usado ya esta usado
                    controlerInitialMenuView.setfirstName(firstNameField.getText());
                    controlerInitialMenuView.setLastName(lastNameField.getText());
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/co/edu/uptc/Fxml/RegisterInitial.fxml"));
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        // mostrar el error
                        Label error = (Label) loader.getNamespace().get("error");
                        error.setText("Email used erro");

                        TextField emailField = (TextField) loader.getNamespace().get("emailField");
                        TextField firstNameField = (TextField) loader.getNamespace().get("firstNameField");
                        TextField lastNameField = (TextField) loader.getNamespace().get("lastNameField");

                        // modificar para ver los cambios
                        emailField.getStyleClass().add("password-field");
                        // envio la info cargada previamente
                        firstNameField.setText(controlerInitialMenuView.getfirstName());
                        lastNameField.setText(controlerInitialMenuView.getLastName());
                        error.setFont(new Font("Arial", 18));
                        stage.setScene(new Scene(root));
                        stage.show();

                        Stage myStage = (Stage) this.register.getScene().getWindow();
                        myStage.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (emailRevi == 1) {
                    // error por el formato del emil invalido
                    // showAlert("Invalid email format error");
                    // cargar informacion valida que el usuario ya puso
                    controlerInitialMenuView.setfirstName(firstNameField.getText());
                    controlerInitialMenuView.setLastName(lastNameField.getText());

                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/co/edu/uptc/Fxml/RegisterInitial.fxml"));
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        // mostrar el error
                        String emailFormat = "Email format:\n" +
                                "- Must have the format 'example@gmail.com'.";
                        Label error = (Label) loader.getNamespace().get("error");
                        error.setText(emailFormat);
                        TextField emailField = (TextField) loader.getNamespace().get("emailField");
                        TextField firstNameField = (TextField) loader.getNamespace().get("firstNameField");
                        TextField lastNameField = (TextField) loader.getNamespace().get("lastNameField");

                        // modificar para ver los cambios
                        emailField.getStyleClass().add("password-field");
                        // envio la info cargada previamente
                        firstNameField.setText(controlerInitialMenuView.getfirstName());
                        lastNameField.setText(controlerInitialMenuView.getLastName());
                        error.setFont(new Font("Arial", 18));
                        stage.setScene(new Scene(root));
                        stage.show();

                        Stage myStage = (Stage) this.register.getScene().getWindow();
                        myStage.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (validePassworMin == 3) {
                    // vista porque la contrasenia no cumple con lo minimo 12 caracteres una letre
                    // mayuscula y una minuscula un numero y un caracter especial
                    // showAlert("The password does not match what was expected");

                    controlerInitialMenuView.setemaillll(emailField.getText());
                    controlerInitialMenuView.setfirstName(firstNameField.getText());
                    controlerInitialMenuView.setLastName(lastNameField.getText());

                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/co/edu/uptc/Fxml/RegisterInitial.fxml"));
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        // mostrar el error
                        String minPassword = "The password must meet the following minimum criteria:\n" +
                                "- It must contain at least one digit (0-9).\n" +
                                "- It must contain at least one lowercase letter (a-z).\n" +
                                "- It must contain at least one uppercase letter (A-Z).\n" +
                                "- It must contain at least one special character (@, #,..\n" +
                                "- It must not contain any whitespace.\n" +
                                "- It must be at least 8 characters long.";

                        Label error = (Label) loader.getNamespace().get("error");
                        error.setText(minPassword);
                        error.setFont(new Font("Arial", 18));
                        TextField emailField = (TextField) loader.getNamespace().get("emailField");
                        TextField firstNameField = (TextField) loader.getNamespace().get("firstNameField");
                        TextField lastNameField = (TextField) loader.getNamespace().get("lastNameField");
                        TextField passwordField = (TextField) loader.getNamespace().get("passwordField");
                        // modificar para ver los cambios
                        emailField.getStyleClass().add("passwordInitial-field");
                        passwordField.getStyleClass().add("password-field");
                        // envio la info cargada previamente
                        emailField.setText(controlerInitialMenuView.getemaillll());
                        firstNameField.setText(controlerInitialMenuView.getfirstName());
                        lastNameField.setText(controlerInitialMenuView.getLastName());

                        stage.setScene(new Scene(root));
                        stage.show();

                        Stage myStage = (Stage) this.register.getScene().getWindow();
                        myStage.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            } else {
                // vitas porque las contrasenias no coinciden
                // showAlert("Passwords do not match");

                controlerInitialMenuView.setemaillll(emailField.getText());
                controlerInitialMenuView.setfirstName(firstNameField.getText());
                controlerInitialMenuView.setLastName(lastNameField.getText());

                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/co/edu/uptc/Fxml/RegisterInitial.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    // mostrar el error
                    String minPassword = "Passwords do not match";

                    Label error = (Label) loader.getNamespace().get("error");
                    error.setText(minPassword);
                    error.setFont(new Font("Arial", 10));
                    TextField emailField = (TextField) loader.getNamespace().get("emailField");
                    TextField firstNameField = (TextField) loader.getNamespace().get("firstNameField");
                    TextField lastNameField = (TextField) loader.getNamespace().get("lastNameField");
                    TextField passwordField = (TextField) loader.getNamespace().get("passwordField");
                    TextField cpasswordField = (TextField) loader.getNamespace().get("confirmPasswordField");
                    // modificar para ver los cambios
                    emailField.getStyleClass().add("passwordInitial-field");
                    passwordField.getStyleClass().add("password-field");
                    cpasswordField.getStyleClass().add("password-field");
                    // envio la info cargada previamente
                    emailField.setText(controlerInitialMenuView.getemaillll());
                    firstNameField.setText(controlerInitialMenuView.getfirstName());
                    lastNameField.setText(controlerInitialMenuView.getLastName());
                    error.setFont(new Font("Arial", 18));
                    stage.setScene(new Scene(root));
                    stage.show();

                    Stage myStage = (Stage) this.register.getScene().getWindow();
                    myStage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            // vitas porque las contrasenias no coinciden
            // showAlert("Passwords do not match");

            controlerInitialMenuView.setemaillll(emailField.getText());
            controlerInitialMenuView.setfirstName(firstNameField.getText());
            controlerInitialMenuView.setLastName(lastNameField.getText());

            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/co/edu/uptc/Fxml/RegisterInitial.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                // mostrar el error
                String minPassword = "complete all fields";

                Label error = (Label) loader.getNamespace().get("error");
                error.setText(minPassword);
                error.setFont(new Font("Arial", 10));
                TextField emailField = (TextField) loader.getNamespace().get("emailField");
                TextField firstNameField = (TextField) loader.getNamespace().get("firstNameField");
                TextField lastNameField = (TextField) loader.getNamespace().get("lastNameField");
                TextField passwordField = (TextField) loader.getNamespace().get("passwordField");
                TextField cpasswordField = (TextField) loader.getNamespace().get("confirmPasswordField");
                // modificar para ver los cambios
                emailField.getStyleClass().add("passwordInitial-field");
                passwordField.getStyleClass().add("passwordInitial-field");
                cpasswordField.getStyleClass().add("passwordInitial-field");
                // envio la info cargada previamente
                emailField.setText(controlerInitialMenuView.getemaillll());
                firstNameField.setText(controlerInitialMenuView.getfirstName());
                lastNameField.setText(controlerInitialMenuView.getLastName());
                error.setFont(new Font("Arial", 18));
                stage.setScene(new Scene(root));
                stage.show();

                Stage myStage = (Stage) this.register.getScene().getWindow();
                myStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void showAlert(String message) {
        // Implementación del método
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public RegisterControlerFx() {
        // Constructor sin argumentos requerido por FXMLLoader
    }

    public RegisterControlerFx(ControlerInitialMenuView controlerInitialMenuView) {
        this.controlerInitialMenuView = controlerInitialMenuView;

    }

    @FXML
    private void handleRegistroButton() {

    }

    @FXML
    private void handleLoginButton() {
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
        System.out.println("poner lo de visitantyeeeeeeeej");
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
}