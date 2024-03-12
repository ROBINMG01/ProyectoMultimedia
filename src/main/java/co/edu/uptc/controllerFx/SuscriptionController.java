package co.edu.uptc.controllerFx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import co.edu.uptc.controller.ControlerInitialMenuView;

import co.edu.uptc.model.User;
import co.edu.uptc.util.DocumentManagement;

public class SuscriptionController {

    @FXML
    private Label labelUserName;
    @FXML
    private Button btnBack, btnPay;
    @FXML
    private TextField email, cardNumber, password, DateFin, txtCVV, valor;
    private ControlerInitialMenuView controlerInitialMenuView;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public SuscriptionController(User user) {
        this.setUser(user);
    }

    @FXML
    public void initialize() {
        btnPay.setOnAction(
                event -> abrirVistaJuan("/co/edu/uptc/Fxml/Suscription.fxml", new SuscriptionController(user)));

        this.controlerInitialMenuView = new ControlerInitialMenuView();

        try {
            controlerInitialMenuView.getUsers().addAll(controlerInitialMenuView.loadUsers("Users"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        try {
            // Vista de Login
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uptc/Fxml/Vista1.fxml"));
            Parent root1 = loader.load();

            Vista1Controller controller = loader.getController();
            controller.setUser(user);

            // Mostrar la vista que desees, por ejemplo Vista1
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

            Stage myStage = (Stage) this.btnBack.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void abrirVistaJuan(String fxmlPath, Object controller) {
        
      
            if (!email.getText().isEmpty() && !password.getText().isEmpty() && !cardNumber.getText().isEmpty()
                    && !DateFin.getText().isEmpty()
                    && !txtCVV.getText().isEmpty()) {
                // validar que las contrasenias sean igualees
                if (password.getText().equals(user.getPassword())) {
                 

                    // enviar la activacion
                    user.setActieSusciption("active");
                  

                    controlerInitialMenuView.rempazoUser(user);

                    DocumentManagement documentManagement = new DocumentManagement();
                    try {
                        documentManagement.generatePdf("Factura_de_" + user.getFirstName(),
                                controlerInitialMenuView.getUsers(), user, "\nValue: $20.000",
                                "\nCardNumber :" +
                                        cardNumber);
                        JOptionPane.showInputDialog("PDF generated successfully!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    // no es igual
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
                        fxmlLoader.setController(controller);
                        if (controller instanceof FavoritesController) {
                            ((FavoritesController) controller).setUser(user);
                        }
                        // el objeto user que necesitamos
                        Parent root = fxmlLoader.load();

                        TextField email = (TextField) fxmlLoader.getNamespace().get("email");
                        TextField DateFin = (TextField) fxmlLoader.getNamespace().get("DateFin");

                        email.setText(user.getEmail());
                        email.setEditable(false);
                        // creo la clase de controlador de la logica
                        ControlerInitialMenuView controlerInitialMenuView = new ControlerInitialMenuView();
                        try {
                            // leo los archivos user
                            controlerInitialMenuView.getUsers().addAll(controlerInitialMenuView.loadUsers("Users"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // asigno la fecha la feccha
                        user.setFinDateDeSubscription(controlerInitialMenuView.dateFinSuscripcion(user));
                        // darle formato
                        String fechaFinSus = controlerInitialMenuView.formatearFecha(user.getFinDateDeSubscription());
                        DateFin.setText(fechaFinSus);
                        // mostrar el error
                        String minPassword = "Incorrect password";

                        Label error = (Label) fxmlLoader.getNamespace().get("error");
                        error.setText(minPassword);
                        error.setFont(new Font("Arial", 15));
                        DateFin.setEditable(false);
                        // mostrar el error en la contrasenia
                        password.getStyleClass().add("password-field");
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setTitle("User Register");
                        stage.setScene(scene);
                        stage.show();
                        // Cerrar la ventana actual
                        Stage myStage = (Stage) this.btnPay.getScene().getWindow();
                        myStage.close();
                    } catch (IOException e) {
                        Logger.getLogger(Vista1Controller.class.getName()).severe(e.getMessage());
                    }

                }

            } else {

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
                    fxmlLoader.setController(controller);
                    if (controller instanceof FavoritesController) {
                        ((FavoritesController) controller).setUser(user);
                    }
                    // el objeto user que necesitamos
                    Parent root = fxmlLoader.load();

                    TextField email = (TextField) fxmlLoader.getNamespace().get("email");
                    TextField DateFin = (TextField) fxmlLoader.getNamespace().get("DateFin");

                    email.setText(user.getEmail());
                    email.setEditable(false);
                    // creo la clase de controlador de la logica
                    ControlerInitialMenuView controlerInitialMenuView = new ControlerInitialMenuView();
                    try {
                        // leo los archivos user
                        controlerInitialMenuView.getUsers().addAll(controlerInitialMenuView.loadUsers("Users"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // asigno la fecha la feccha
                    user.setFinDateDeSubscription(controlerInitialMenuView.dateFinSuscripcion(user));
                    // darle formato
                    String fechaFinSus = controlerInitialMenuView.formatearFecha(user.getFinDateDeSubscription());
                    DateFin.setText(fechaFinSus);
                    // mostrar el error
                    String minPassword = "complete all fields";

                    Label error = (Label) fxmlLoader.getNamespace().get("error");
                    error.setText(minPassword);
                    error.setFont(new Font("Arial", 15));
                    DateFin.setEditable(false);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("User Register");
                    stage.setScene(scene);
                    stage.show();
                    // Cerrar la ventana actual
                    Stage myStage = (Stage) this.btnPay.getScene().getWindow();
                    myStage.close();
                } catch (IOException e) {
                    Logger.getLogger(Vista1Controller.class.getName()).severe(e.getMessage());
                }
            }

        
    }
}