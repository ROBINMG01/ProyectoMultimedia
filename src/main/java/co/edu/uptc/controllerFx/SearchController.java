package co.edu.uptc.controllerFx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.*;
import java.io.IOException;

import co.edu.uptc.model.User;

public class SearchController {

    @FXML
    private ImageView imageMovie;

    @FXML
    private Button getMovie;

    @FXML
    private Button btnBack;

    @FXML
    private ImageView imageSerie;

    @FXML
    private Button getSerie;

    private User user;

    public SearchController(User user) {
        this.user = user;

    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    protected void handleButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/SearchMovie.fxml"));
            SearchMovieController searchMovieController = new SearchMovieController();
            fxmlLoader.setController(searchMovieController);
            Parent root = fxmlLoader.load();

            // Cargar las imágenes
            Image imageMovie = new Image("co/edu/uptc/image/PosterMovie.png");
            this.imageMovie.setImage(imageMovie);
            Image imageSerie = new Image("co/edu/uptc/image/PosterSerie.png");
            this.imageSerie.setImage(imageSerie);

            Stage stage = new Stage();
            Parent view;
            Object controller;

            if (event.getSource() == getMovie) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/SearchMovie.fxml"));
                searchMovieController = new SearchMovieController();
                fxmlLoader.setController(searchMovieController);
                view = fxmlLoader.load();
                controller = fxmlLoader.getController();
                if (controller instanceof SearchController) {
                    ((SearchController) controller).setUser(user);
                }
                stage.setTitle("Search Movie");
            } else if (event.getSource() == getSerie) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/SearchSerie.fxml"));
                view = fxmlLoader.load();
                controller = fxmlLoader.getController();
                if (controller instanceof SearchController) {
                    ((SearchController) controller).setUser(user);
                }
                stage.setTitle("Search Serie");
            } else if (event.getSource() == btnBack) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Vista1.fxml"));
                view = fxmlLoader.load();
                controller = fxmlLoader.getController();
                if (controller instanceof SearchController) {
                    ((SearchController) controller).setUser(user);
                }
                stage.setTitle("Vista 1");
            } else {
                return;
            }

            stage.setScene(new Scene(view));
            stage.show();

            // Cerrar la vista actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}