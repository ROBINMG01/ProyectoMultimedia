package co.edu.uptc.controllerFx;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MovieRepository;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.SerieRepository;
import co.edu.uptc.viewFx.AdminViewFx;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminControllerSerieFx {

    private AdminController ac = new AdminController();
    private Serie serie;
    private EditSerieController esc;
    ArrayList<String> listAuthors = new ArrayList<>();
    ArrayList<String> listActors = new ArrayList<>();
    private String imageUrl;
    @FXML
    private TextField serieName;

    @FXML
    private TextField serieDescription;

    @FXML
    private TextField serieGender;

    @FXML
    private TextField serieAuthor;

    @FXML
    private TextField serieActor;

    @FXML
    private TextField serieDuration;

    @FXML
    private TextField serieYear;

    @FXML
    private TextField serieNameSeason;

    @FXML
    private TextField serieDescriptionSeason;

    @FXML
    private TextField serieNameChapter;

    @FXML
    private TextField serieDurationChapter;

    @FXML
    private Button saveSerieButton;

    @FXML
    private Button movieButton;

    @FXML
    private Button authorButton;

    @FXML
    private Button newAuthorButton;

    @FXML
    private Button actorButton;

    @FXML
    private Button newActorButton;

    @FXML
    private TableView<Serie> tableView;

    @FXML
    private TableColumn<Serie, String> nameColumn;

    @FXML
    private TableColumn<Serie, String> genderColumn;

    @FXML
    private TableColumn<Serie, Integer> durationColumn;

    @FXML
    private TableColumn<Serie, Integer> descriptionColumn;

    @FXML
    private TableColumn<Serie, Integer> nameSeasonColumn;

    @FXML
    private TableColumn<Serie, Integer> descriptionSeasonColumn;

    @FXML
    private TableColumn<Serie, Integer> nameChapterColumn;

    @FXML
    private TableColumn<Serie, Integer> descriptionChapterColumn;

    @FXML
    private TableColumn<Serie, String> yearColumn;

    public AdminControllerSerieFx() {
        this.ac = new AdminController();
        this.esc = new EditSerieController();
        this.durationColumn = new TableColumn<>();
        this.genderColumn = new TableColumn<>();
        this.nameColumn = new TableColumn<>();
        this.yearColumn = new TableColumn<>();
        this.tableView = new TableView<>();
        this.serieName = new TextField();
        this.serieGender = new TextField();
        this.serieDuration = new TextField();
        this.serieYear = new TextField();
        this.serie = new Serie();
    }

    public void initialize() {
        ac = new AdminController();

        ac.getListSeries().addAll(ac.loadSerie("Series"));

        if (SerieRepository.getInstance().getSeries().isEmpty()) {

            SerieRepository.getInstance().getSeries().addAll(ac.getListSeries());
            tableView.setItems(SerieRepository.getInstance().getSeries());
        }
        // Configurar las columnas
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Serie, Boolean> editColumn = new TableColumn<>("Edit");
        editColumn.setCellFactory(tc -> new ButtonCellSerie());
        tableView.getColumns().add(editColumn);

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

        String serieYearString = serieYear.getText();
        Integer serieYearInt;
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

        String serieDescriptionString = serieDescription.getText();

        if (serieDescriptionString == null || serieDescriptionString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(
                    "El campo de la descripcion está vacío, por favor ingresa una descripcion para de la película.");
            alert.showAndWait();
            return false;
        }

        String serieAuthorString = serieAuthor.getText();

        if (serieAuthorString == null || serieAuthorString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(
                    "El campo del autor está vacío, por favor ingresa un autor para la película.");
            alert.showAndWait();
            return false;
        } else {
            listAuthors.add(serieAuthorString);
        }

        String serieActorString = serieActor.getText();

        if (serieActorString == null || serieActorString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(
                    "El campo del actor está vacío, por favor ingresa un actor para la película.");
            alert.showAndWait();
            return false;
        } else {
            listActors.add(serieActorString);
        }

        String serieNameSnString = serieNameSeason.getText();

        if (serieNameSnString == null || serieNameSnString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(
                    "El campo de la descripcion está vacío, por favor ingresa una descripcion para de la película.");
            alert.showAndWait();
            return false;
        }

        String serieDescriptionSnString = serieDescriptionSeason.getText();

        if (serieDescriptionSnString == null || serieDescriptionSnString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(
                    "El campo de la descripcion está vacío, por favor ingresa una descripcion para de la película.");
            alert.showAndWait();
            return false;
        }

        String serieNameCString = serieNameChapter.getText();

        if (serieNameCString == null || serieNameCString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(
                    "El campo de la descripcion está vacío, por favor ingresa una descripcion para de la película.");
            alert.showAndWait();
            return false;
        }

        Integer serieDurationInt;
        String serieDurationCString = serieDurationChapter.getText();
        try {
            serieDurationInt = Integer.parseInt(serieDurationCString);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato Incorrecto, digita bien el año de la película.");
            alert.showAndWait();
            return false;
        }

        if (ac.addSerie(serieName.getText(), serieDescription.getText(), serieDurations, listAuthors,
                serieGender.getText(),
                listActors, serieNameSeason.getText(), serieDescriptionSeason.getText(), serieNameChapter.getText(),
                serieDurationInt, imageUrl, serieDurationCString, serieYearInt)) {
            SerieRepository.getInstance().addSerie(ac.getListSeries().get(ac.getListSeries().size() - 1));
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
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uptc/Fxml/ListMoviesAdmin.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage myStage = (Stage) this.movieButton.getScene().getWindow();
        myStage.close();
        //AdminViewFx.setRoot("ListMoviesAdmin");
    }

    @FXML
    public void fileEditSerie(Serie serie) throws IOException {
        esc.help(serie);
    }

        @FXML
    public void deleteMovie(Serie serie) {
        ac.getListSeries().addAll(ac.loadSerie("Series"));
        for (int index = 0; index < SerieRepository.getInstance().getSeries().size(); index++) {
            if (SerieRepository.getInstance().getSeries().get(index).getName().equals(serie.getName())) {
                SerieRepository.getInstance().getSeries().remove(index);
                ac.deleteSerie(serie.getName());
                tableView.refresh();
            }
        }
    }

    @FXML
    public void newAuthor() {
        listAuthors.add(serieAuthor.getText());
        serieAuthor.clear();
    }

    @FXML
    public void newActor() {
        listActors.add(serieActor.getText());
        serieActor.clear();
    }
}
