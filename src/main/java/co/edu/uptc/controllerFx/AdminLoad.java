package co.edu.uptc.controllerFx;

import java.io.IOException;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MovieRepository;
import co.edu.uptc.viewFx.AdminViewFx;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminLoad {

    AdminController ac = new AdminController();
    AdminControllerFx acf = new AdminControllerFx();

    @FXML
    private TableView<Movie> tableView;

    @FXML
    private TableColumn<Movie, String> nameColumn;

    @FXML
    private TableColumn<Movie, String> genderColumn;

    @FXML
    private TableColumn<Movie, Integer> durationColumn;

    @FXML
    private TableColumn<Movie, Integer> descriptionColumn;
    
    @FXML
    private TableColumn<Movie, String> yearColumn;

    public void initialize() {
        // Configurar las columnas
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Obtener las películas del repositorio
        tableView.setItems(MovieRepository.getInstance().getMovies());
    }

    @FXML
    private void showFormCreateMovie() throws IOException {
        AdminViewFx.setRoot("ListMoviess");
    }

    public void loadMovies(){
        for (int i = 0; i < ac.getListMovies().size(); i++) {
            acf.initialize();
        }
    }
}
