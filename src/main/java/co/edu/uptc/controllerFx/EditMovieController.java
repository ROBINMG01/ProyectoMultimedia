package co.edu.uptc.controllerFx;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uptc.model.Movie;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class EditMovieController implements Initializable {
    
    @FXML
    private TextField movieNameField;

    @FXML
    private TextField movieGenderField;

    @FXML
    private TextField movieDurationField;

    @FXML
    private TextField movieYearField;

    private Movie movie;

    public void initData(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (movie != null) {
            movieNameField.setText(movie.getName());
            movieGenderField.setText(movie.getGender());
            movieDurationField.setText(String.valueOf(movie.getDuration()));
            movieYearField.setText(String.valueOf(movie.getYear()));
        }
    }

    
}

