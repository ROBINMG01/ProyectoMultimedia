package co.edu.uptc.controllerFx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

import co.edu.uptc.model.User;
public class SuscriptionController {

    @FXML
    private Label labelUserName;
    @FXML
    private Button btnBack, btnPay;
    @FXML
    private TextField txtEmail, txtCardNumber, txtExpire, txtPassword, txtCVV;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public SuscriptionController(User user){

    }

    @FXML
    public void initialize() {
        // Aquí puedes inicializar tus controles si es necesario
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Vista1.fxml"));
            Parent previousView = fxmlLoader.load();

            // Obtén el controlador de la vista y establece el usuario
            SuscriptionController controller = fxmlLoader.getController();
            controller.setUser(user);

            // Crear una nueva ventana para la vista anterior
            Stage stage = new Stage();
            stage.setTitle("Vista 1");
            stage.setScene(new Scene(previousView));
            stage.show();

            // Cerrar la vista actual
            Stage currentStage = (Stage) btnBack.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePayButtonAction(ActionEvent event) {
    }
}