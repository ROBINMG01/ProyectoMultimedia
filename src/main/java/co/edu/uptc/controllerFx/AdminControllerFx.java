package co.edu.uptc.controllerFx;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MovieRepository;
import co.edu.uptc.viewFx.AdminViewFx;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AdminControllerFx {

    private AdminController ac;
    private EditMovieController emc;
    private Movie movie;
    private ArrayList<String> listAuthors = new ArrayList<>();
    private ArrayList<String> listActors = new ArrayList<>();

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
    private TextField movieAuthor;

    @FXML
    private TextField movieActor;

    @FXML
    private Button saveSerieButton;

    @FXML
    private Button movieButton;

    @FXML
    private Button serieButton;

    @FXML
    private Button authorButton;

    @FXML
    private Button newAuthorButton;

    @FXML
    private Button actorButton;

    @FXML
    private Button newActorButton;

    @FXML
    private TableView<Movie> tableView;

    @FXML
    private TableColumn<Movie, String> nameColumn;

    @FXML
    private TableColumn<Movie, String> genderColumn;

    @FXML
    private TableColumn<Movie, Integer> durationColumn;

    @FXML
    private TableColumn<Movie, String> yearColumn;

    public AdminControllerFx() {
        this.ac = new AdminController();
        this.emc = new EditMovieController();
        this.durationColumn = new TableColumn<>();
        this.genderColumn = new TableColumn<>();
        this.nameColumn = new TableColumn<>();
        this.yearColumn = new TableColumn<>();
        this.tableView = new TableView<>();
        this.movieName = new TextField();
        this.movieGender = new TextField();
        this.movieDuration = new TextField();
        this.movieYear = new TextField();
        this.movie = new Movie();
    }

    public void initialize() {

        ac.getListMovies().addAll(ac.loadMovie("Movie"));

        if (MovieRepository.getInstance().getMovies().isEmpty()) {
            MovieRepository.getInstance().getMovies().addAll(ac.getListMovies());
            tableView.setItems(MovieRepository.getInstance().getMovies());
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Movie, Boolean> editColumn = new TableColumn<>("Edit");
        editColumn.setCellFactory(tc -> new ButtonCell());
        tableView.getColumns().add(editColumn);

        tableView.setItems(MovieRepository.getInstance().getMovies());
    }

    @FXML
    private void showFormCreateMovies() throws IOException {
        AdminViewFx.setRoot("ListMovies");
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
            if (movieDurations < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Duration invalid");
                alert.showAndWait();
                return false;
            }
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

        String movieAuthorString = movieAuthor.getText();

        if (movieAuthorString == null || movieAuthorString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(
                    "El campo del autor está vacío, por favor ingresa un autor para la película.");
            alert.showAndWait();
            return false;
        } else {
            listAuthors.add(movieAuthorString);
        }

        String movieActorString = movieActor.getText();

        if (movieActorString == null || movieActorString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(
                    "El campo del actor está vacío, por favor ingresa un actor para la película.");
            alert.showAndWait();
            return false;
        } else {
            listActors.add(movieActorString);
        }

        String movieDescriptionString = movieDescription.getText();

        if (movieDescriptionString == null || movieDescriptionString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(
                    "El campo de la descripcion está vacío, por favor ingresa una descripcion para de la película.");
            alert.showAndWait();
            return false;
        }

        if (ac.addMovie(movieName.getText(), movieDescription.getText(), movieDurations, listAuthors,
                movieGender.getText(), listActors, movieYearInt)) {
            MovieRepository.getInstance().addMovie(ac.getListMovies().get(ac.getListMovies().size() - 1));
        }

        // Clean fields in case of success
        movieName.clear();
        movieGender.clear();
        movieDuration.clear();
        movieYear.clear();
        movieAuthor.clear();
        movieActor.clear();

        AdminViewFx.setRoot("ListMoviesAdmin");
        return true;
    }

    public String nameMovie(String name){
        return name;
    }

    @FXML
    private void showSerie() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uptc/Fxml/listSeries.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage myStage = (Stage) this.serieButton.getScene().getWindow();
        myStage.close();
        // AdminViewFx.setRoot("listSeries");
    }

    
    @FXML
    private void showEditMovie() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uptc/Fxml/EditMovie.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage myStage = (Stage) this.movieButton.getScene().getWindow();
        myStage.close();
        // AdminViewFx.setRoot("EditMovie");
    }

    @FXML
    public void newAuthor() {
        listAuthors.add(movieAuthor.getText());
        movieAuthor.clear();
    }

    @FXML
    public void newActor() {
        listActors.add(movieActor.getText());
        movieActor.clear();
    }

    @FXML
    public void fileEditMovie(Movie movie) throws IOException {
        //emc.help(movie);
        emc.initialize(movie);
        // AdminViewFx.setRoot("EditMovie");
    }

    @FXML
    public void deleteMovie(Movie movie) {
        ac.getListMovies().addAll(ac.loadMovie("Movie"));
        for (int index = 0; index < MovieRepository.getInstance().getMovies().size(); index++) {
            if (MovieRepository.getInstance().getMovies().get(index).getName().equals(movie.getName())) {
                MovieRepository.getInstance().getMovies().remove(index);
                ac.deleteMovie(movie.getName());
                tableView.refresh();
            }
        }
    }
}
