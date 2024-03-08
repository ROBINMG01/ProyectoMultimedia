package co.edu.uptc.controllerFx;

import java.io.IOException;

import co.edu.uptc.viewFx.*;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MovieRepository;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListMoviesController {
    @FXML
    private TableView<Movie> tableView;

    @FXML
    private TableColumn<Movie, String> nameColumn;

    @FXML
    private TableColumn<Movie, String> directorColumn;

    @FXML
    private TableColumn<Movie, Integer> yearColumn;
    @FXML
    private TableColumn<Movie, String> generColumn;

    public void initialize() {
        // Configurar las columnas
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        generColumn.setCellValueFactory(new PropertyValueFactory<>("gener"));

        // Obtener las películas del repositorio
        tableView.setItems(MovieRepository.getInstance().getMovies());
    }

    @FXML
    private void showFormCreateMovie() throws IOException {
        RegisterViewFx.setRoot("Register");
    }
}