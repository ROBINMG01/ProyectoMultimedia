package co.edu.uptc.viewFx;

import java.io.IOException;

import co.edu.uptc.controller.ControlerInitialMenuView;
import co.edu.uptc.controller.RegisterControllerFx;
import co.edu.uptc.controllerFx.RegisterControlerFx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InitialMenuViewFx extends Application {
    private static ControlerInitialMenuView controlerInitialMenuView;

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("vistaInitialMenu"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterViewFx.class.getResource("/co/edu/uptc/Fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
       // registerViewFx.handleRegisterButtonClick(controlerInitialMenuView);
        launch();
    }
}
