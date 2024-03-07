package co.edu.uptc.controllerFx;

import java.io.IOException;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MovieRepository;
import co.edu.uptc.viewFx.AdminViewFx;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminControllerSerieFx {

    private AdminController ac = new AdminController();

    @FXML
    private TextField movieName;

    @FXML
    private TextField movieDescription;

    @FXML
    private TextField movieGender;

    @FXML
    private TextField movieDuration;

    @FXML
    private TextField movieYear;

    @FXML
    private Button saveMovieButton;

    @FXML
    private Button movieButton;

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

    /*
     * String name, String description, int duration, ArrayList<String> listAuthors, String gender,
            ArrayList<String> listActors, String nameSeason, String descriptionSeason, String nameChapter,
            int durationChapter, String file
     */
    public void initialize() {
        // Configurar las columnas
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        //descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Obtener las películas del repositorio
        tableView.setItems(MovieRepository.getInstance().getMovies());
    }

    @FXML
    private void showFormCreateMovies() throws IOException {
        AdminViewFx.setRoot("ListMoviess");
    }

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

        String movieDescriptionString = movieDescription.getText();

        if (movieDescriptionString == null || movieDescriptionString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El campo de la descripcion está vacío, por favor ingresa una descripcion para de la película.");
            alert.showAndWait();
            return false;
        }

        String movieGenderString = movieGender.getText();

        if (movieGenderString == null || movieGenderString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El campo del genero está vacío, por favor ingresa el genero.");
            alert.showAndWait();
            return false;
        }

        String movieDurationString = movieDuration.getText();

        Integer movieDurations;

        try {
            movieDurations = Integer.parseInt(movieDurationString);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La duracion no es un número, digita bien la duracion de la película.");
                alert.showAndWait();
            return false;
        }


        Integer movieYearInt;
        String movieYearString = movieYear.getText();
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

        //String name, String description, int duration, ArrayList<String> listAuthors,
         //   String gender, ArrayList<String> listActors
        if (ac.addMovie(movieName.getText(), movieDescription.getText(),movieDurations,null, movieGender.getText(), null,movieYearInt)) {
            MovieRepository.getInstance().addMovie(ac.getListMovies().get(ac.getListMovies().size()-1));
        }

        // Clean fields in case of success
        movieName.clear();
        movieGender.clear();
        movieDuration.clear();
        movieYear.clear();

        AdminViewFx.setRoot("listSeries");
        return true;
    }

    @FXML
    private void showMovie() throws IOException {
        AdminViewFx.setRoot("ListMoviess");
    }
}
