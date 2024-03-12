package co.edu.uptc.controllerFx;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.User;
import java.lang.reflect.Type;

public class SettingsController {

    @FXML
    private Button btnBack;
    @FXML
    private Button btnSubmit;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField password1;
    @FXML
    private TextField password2;
    @FXML
    private ImageView imageSetting;
    @FXML
    private Label welcomeLabel;

    private User user;
    private int userIndex;

    public void setUser(User user) {
        this.user = user;
        updateViewWithUserDetails();
        welcomeLabel.setText("Welcome " + user.getFirstName());

        // Busca el índice del usuario en la lista de usuarios
        try {
            Gson gson = new Gson();
            File file = new File("src/main/java/co/edu/uptc/persistence/Users.json");
            Type userListType = new TypeToken<List<User>>() {
            }.getType();
            List<User> users = gson.fromJson(new FileReader(file), userListType);
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getEmail().equals(user.getEmail())) {
                    userIndex = i;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        // Asegúrate de que el correo electrónico no esté vacío y tenga un formato
        // válido
        BooleanBinding emailValid = Bindings.createBooleanBinding(() -> {
            String emailText = email.getText();
            return emailText != null && emailText.matches("\\S+@\\S+\\.\\S+");
        }, email.textProperty());

        // Deshabilita el botón de enviar si el correo electrónico no es válido
        btnSubmit.disableProperty().bind(emailValid.not());

        welcomeLabel.setText(user.getFirstName());
    }

    private void updateViewWithUserDetails() {
        // Actualiza la vista con los detalles del usuario.
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
    }

    @FXML
    private void handleSubmitButton() {
        user.setFirstName(firstName.getText());
        user.setLastName(lastName.getText());

        String newEmail = email.getText();
        // Verificar si el nuevo email ya existe antes de actualizar
        if (existeEmail(newEmail, user)) {
            // Mostrar mensaje de error: el email ya existe
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("El correo electrónico ingresado ya está en uso.");
            alert.showAndWait();
        } else if (!password1.getText().equals(password2.getText())) {
            // Mostrar mensaje de error: las contraseñas no coinciden
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Las contraseñas ingresadas no coinciden.");
            alert.showAndWait();
        } else if (!(newEmail.endsWith("gmail.com") || newEmail.endsWith("uptc.edu.co")
                || newEmail.endsWith("hotmail.com"))) {
            // Mostrar mensaje de error: el email no tiene una terminación válida
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(
                    "El correo electrónico ingresado no tiene una terminación válida. Debe terminar en 'gmail.com', 'uptc.edu.co' o 'hotmail.com'.");
            alert.showAndWait();
        } else {
            // Si el email no existe y las contraseñas coinciden, actualizar y mostrar
            // mensaje de éxito
            user.setEmail(newEmail);
            if (!password1.getText().isEmpty()) {
                user.setPassword(password1.getText());
            }

            try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                File file = new File("src/main/java/co/edu/uptc/persistence/Users.json");

                // Lee el archivo JSON y conviértelo en una lista de objetos User
                Type userListType = new TypeToken<List<User>>() {
                }.getType();
                List<User> users = gson.fromJson(new FileReader(file), userListType);

                // Actualiza el usuario en la lista de usuarios
                users.set(userIndex, user);
                // Ordena la lista de usuarios
                users.sort(Comparator.comparing(User::getEmail));

                // Escribe la lista actualizada de usuarios de nuevo al archivo JSON
                try (FileWriter writer = new FileWriter(file)) {
                    String json = gson.toJson(users);
                    writer.write(json);
                }

                // Mostrar los datos actualizados del usuario en la alerta
                String mensajeAlerta = "Los detalles del usuario se han actualizado con éxito:\n\n";
                mensajeAlerta += "Nombre: " + user.getFirstName() + "\n";
                mensajeAlerta += "Apellido: " + user.getLastName() + "\n";
                mensajeAlerta += "Correo electrónico: " + user.getEmail() + "\n";
                if (!password1.getText().isEmpty()) {
                    mensajeAlerta += "Contraseña: Actualizada\n";
                }

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText(mensajeAlerta);
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para verificar si el email ya existe (excepto para el usuario actual)
    private boolean existeEmail(String email, User currentUser) {
        try {
            Gson gson = new Gson();
            File file = new File("src/main/java/co/edu/uptc/persistence/Users.json");
            Type userListType = new TypeToken<List<User>>() {
            }.getType();
            List<User> users = gson.fromJson(new FileReader(file), userListType);
            for (User user : users) {
                // Comprueba si el correo electrónico ya existe y si el usuario actual no es el
                // propietario
                if (user.getEmail().equals(email) && !user.getEmail().equals(currentUser.getEmail())) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    private void handleButton() {
        abrirVista1();
    }

    public SettingsController(User user) {
        this.user = user;
    }

    private void abrirVista1() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Vista1.fxml"));
            Parent SettingsController = fxmlLoader.load();

            // Crear una nueva ventana para la vista del catálogo de películas
            Stage stage = new Stage();
            stage.setTitle("Catálogo de películas");
            stage.setScene(new Scene(SettingsController));
            stage.show();

            Stage myStage = (Stage) this.btnBack.getScene().getWindow();

            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}