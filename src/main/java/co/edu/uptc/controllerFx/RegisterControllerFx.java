package co.edu.uptc.controllerFx;

import java.io.IOException;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MovieRepository;
import co.edu.uptc.viewFx.RegisterViewFx;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterControllerFx {
    @FXML
    private TextField movieName;

    @FXML
    private TextField movieDirector;

    @FXML
    private TextField movieYear;

    @FXML
    private TextField movieGener;

    @FXML
    private Button saveMovieButton;

    @FXML
    private boolean saveMovie() throws IOException {
        String movieNameString = movieName.getText();

        if (movieNameString == null || movieNameString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El campo del nombre está vacío, por favor ingresa el nombre de la película.");
            alert.showAndWait();
            return false;
        }

        String movieDirectorString = movieDirector.getText();

        if (movieDirectorString == null || movieDirectorString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El campo del director está vacío, por favor ingresa el nombre del director.");
            alert.showAndWait();
            return false;
        }

        String movieYearString = movieYear.getText();

        if (movieYearString == null || movieYearString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato Vacio, digita el año de la película.");
            alert.showAndWait();
            return false;
        }

        int movieYearInt;
        try {
            movieYearInt = Integer.parseInt(movieYearString);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato Incorrecto, digita bien el año de la película.");
            alert.showAndWait();
            return false;
        }

        Movie movie = new Movie(movieName.getText(), movieDirector.getText(), movieYearInt, movieGener.getText());
        MovieRepository.getInstance().addMovie(movie);

        // Clean fields in case of success
        movieName.clear();
        movieDirector.clear();
        movieYear.clear();

        RegisterViewFx.setRoot("listMovies");
        return true;
    }

    @FXML
    private void showFormCreateMovie() throws IOException {
        RegisterViewFx.setRoot("createMovie");
    }
}