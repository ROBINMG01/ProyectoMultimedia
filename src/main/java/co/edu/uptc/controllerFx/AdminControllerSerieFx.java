package co.edu.uptc.controllerFx;

import java.io.IOException;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MovieRepository;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.SerieRepository;
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
    private TextField serieName;

    @FXML
    private TextField serieDescription;

    @FXML
    private TextField serieGender;

    @FXML
    private TextField serieDuration;

    @FXML
    private TextField serieYear;

    @FXML
    private TextField SerieNameSeason;

    @FXML
    private TextField serieDescriptionSeason;

    @FXML
    private TextField serieNameChapter;

    @FXML
    private TextField serieDescriptionChampter;

    @FXML
    private Button saveMovieButton;

    @FXML
    private Button movieButton;

    @FXML
    private TableView<Serie> tableView;

    @FXML
    private TableColumn<Movie, String> nameColumn;

    @FXML
    private TableColumn<Movie, String> genderColumn;

    @FXML
    private TableColumn<Movie, Integer> durationColumn;

    @FXML
    private TableColumn<Movie, Integer> descriptionColumn;

    @FXML
    private TableColumn<Movie, Integer> nameSeasonColumn;

    @FXML
    private TableColumn<Movie, Integer> descriptionSeasonColumn;

    @FXML
    private TableColumn<Movie, Integer> nameChapterColumn;

    @FXML
    private TableColumn<Movie, Integer> descriptionChapterColumn;

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
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        //descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Obtener las películas del repositorio
        tableView.setItems(SerieRepository.getInstance().getSeries());
    }

    @FXML
    private void showFormCreateMovies() throws IOException {
        AdminViewFx.setRoot("ListMoviess");
    }

    @FXML
    private boolean saveSerie() throws IOException {
        String serieNameString = serieName.getText();

        if (serieNameString == null || serieNameString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El campo del nombre está vacío, por favor ingresa el nombre de la película.");
            alert.showAndWait();
            return false;
        }

        String serieDescriptionString =serieDescription.getText();

        if (serieDescriptionString == null || serieDescriptionString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El campo de la descripcion está vacío, por favor ingresa una descripcion para de la película.");
            alert.showAndWait();
            return false;
        }

        String serieGenderString = serieGender.getText();

        if (serieGenderString == null || serieGenderString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El campo del genero está vacío, por favor ingresa el genero.");
            alert.showAndWait();
            return false;
        }

        String serieDurationString = serieDuration.getText();

        Integer serieDurations;

        try {
            serieDurations = Integer.parseInt(serieDurationString);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La duracion no es un número, digita bien la duracion de la película.");
                alert.showAndWait();
            return false;
        }


        Integer serieYearInt;
        String serieYearString = serieYear.getText();
        try {
            serieYearInt = Integer.parseInt(serieYearString);
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
        if (ac.addSerie(serieNameString, serieDescriptionString, serieDurations, null, serieGenderString, null, serieGenderString, serieDescriptionString, serieDurationString, 0, serieYearString)) {
            SerieRepository.getInstance().addSerie(ac.getListSeries().get(ac.getListSeries().size()-1));
        }

        // Clean fields in case of success
        serieName.clear();
        serieGender.clear();
        serieDuration.clear();
        serieYear.clear();

        AdminViewFx.setRoot("listSeries");
        return true;
    }

    @FXML
    private void showMovie() throws IOException {
        AdminViewFx.setRoot("ListMoviesAdmin");
    }
}
