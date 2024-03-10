package co.edu.uptc.controllerFx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
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

    public void setUser(User user) {
        this.user = user;
        updateViewWithUserDetails();
    }

    @FXML
    private void initialize() {

        user = new User();
    }

    private void updateViewWithUserDetails() {
        // Actualiza la vista con los detalles del usuario.
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        // No debes mostrar la contraseña en la vista por razones de seguridad.
        welcomeLabel.setText("Welcome " + user.getFirstName());
    }

    @FXML
    private void handleSubmitButton() {
        // Actualiza el objeto User con los nuevos valores de los campos de texto
        user.setFirstName(firstName.getText());
        user.setLastName(lastName.getText());
        user.setEmail(email.getText());
        // Aquí también deberías actualizar la contraseña si el usuario la cambió

        try {
            Gson gson = new Gson();
            File file = new File("/co/edu/uptc/persistence/Users.json");

            // Lee el archivo JSON y conviértelo en una lista de objetos User
            Type userListType = new TypeToken<List<User>>() {
            }.getType();
            List<User> users = gson.fromJson(new FileReader(file), userListType);

            // Busca el usuario que estás actualizando en la lista y reemplázalo con el
            // objeto User actualizado
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getEmail().equals(user.getEmail())) {
                    users.set(i, user);
                    break;
                }
            }

            // Escribe la lista actualizada de usuarios de nuevo al archivo JSON
            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(users, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButton() {
        abrirVista1();
    }

    private void abrirVista1() {
        try {
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
}