package co.edu.uptc.controllerFx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.print.attribute.standard.Media;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class DetallePeliculaController {
    @FXML
    private Label nameSerie;

    @FXML
    private TableView<?> tableView;

    @FXML
    private Media repVideo;

    @FXML
    private Button btnView;

    @FXML
    private Button btnAddF;

    @FXML
    private Button btnBack;

    private Serie serie;
    private Movie movie;

    public void setMovie(Movie movie) {
        this.movie = movie;
        nameSerie.setText(movie.getName());
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
        nameSerie.setText(serie.getName());
        // Aquí debes agregar el código para configurar el tableView y el repVideo con los detalles de la serie
    }

    @FXML
    public void initialize() {
        // Aquí puedes agregar código que se ejecutará cuando se inicialice la vista

        btnBack.setOnAction(event -> {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.close();
        });

        btnView.setOnAction(event -> {
            // Aquí puedes agregar el código para manejar cuando se presiona el botón "ViewSerie"
        });

        btnAddF.setOnAction(event -> {
            // Aquí puedes agregar el código para manejar cuando se presiona el botón "Add favorite"
        });
    }
}