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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Vista1  .fxml"));
            Parent root = fxmlLoader.load();
        
            SuscriptionController controller = fxmlLoader.getController();
            controller.setUser(user); // Aquí pasas el usuario a SuscriptionController
        
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePayButtonAction(ActionEvent event) {
    }
}