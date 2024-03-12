package co.edu.uptc.controllerFx;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MovieRepository;
import co.edu.uptc.viewFx.AdminViewFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditMovieController {

    private AdminController ac;
    private AdminViewFx avf;
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
    private TextField selectedAuthor;

    @FXML
    private TextField selectedActor;

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
    private MenuButton menuButton;

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

    @FXML
    private ChoiceBox<String> movieAuthor;

    @FXML
    private ChoiceBox<Integer> movieYear;

    @FXML
    private ChoiceBox<String> movieActor;

    private int year;
    private String nameMovieFirst;

    public EditMovieController() {
        this.ac = new AdminController();
        this.avf = new AdminViewFx();
        this.durationColumn = new TableColumn<>();
        this.genderColumn = new TableColumn<>();
        this.nameColumn = new TableColumn<>();
        this.yearColumn = new TableColumn<>();
        this.tableView = new TableView<>();
        this.movieDescription = new TextField();
        this.movieName = new TextField();
        this.movieGender = new TextField();
        this.movieDuration = new TextField();
        this.selectedAuthor = new TextField();
        this.selectedActor = new TextField();
        this.movie = new Movie();
        this.nameMovieFirst = "";
    }

    public void initialize(Movie movie) { 
        ac.getListMovies().addAll(ac.loadMovie("Movie"));

        if (MovieRepository.getInstance().getMovies().isEmpty()) {
            MovieRepository.getInstance().getMovies().addAll(ac.getListMovies());
            tableView.setItems(MovieRepository.getInstance().getMovies());
        }

        ArrayList<Integer> years = new ArrayList<>();
        listAuthors.addAll(movie.getlistAuthors());
        listActors.addAll(movie.getListActors());

        Prueba1.getInstance().setListAuthors(listAuthors);
        Prueba1.getInstance().setListActors(listActors);

        
        nameMovieFirst = movie.getName();
        Prueba1.getInstance().setNameMovie(nameMovieFirst);

        years.add(2010);
        years.add(2011);
        years.add(2012);
        years.add(2013);
        years.add(2014);
        years.add(2015);
        years.add(2016);
        years.add(2017);

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uptc/Fxml/EditMovie.fxml"));
            Parent root = loader.load();

            movieName = (TextField) loader.getNamespace().get("movieName");
            movieGender = (TextField) loader.getNamespace().get("movieGender");
            movieDuration = (TextField) loader.getNamespace().get("movieDuration");
            movieDescription = (TextField) loader.getNamespace().get("movieDescription");
            selectedAuthor = (TextField) loader.getNamespace().get("selectedAuthor");
            selectedActor = (TextField) loader.getNamespace().get("selectedActor");
            movieAuthor = (ChoiceBox<String>) loader.getNamespace().get("movieAuthor");
            movieYear = (ChoiceBox<Integer>) loader.getNamespace().get("movieYear");
            movieActor = (ChoiceBox<String>) loader.getNamespace().get("movieActor");

            movieAuthor.getItems().addAll(listAuthors);
            movieAuthor.getSelectionModel().selectFirst();
            selectedAuthor.setText(movieAuthor.getValue());

            movieYear.getItems().addAll(years);
            movieYear.getSelectionModel().selectFirst();

            movieActor.getItems().addAll(listActors);
            movieActor.getSelectionModel().selectFirst();
            selectedActor.setText(movieActor.getValue());

            movieAuthor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    selectedAuthor.setText(newValue.toString()); // Mostrar la opción seleccionada en el TextField
                }
            });

            movieActor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    selectedActor.setText(newValue.toString()); // Mostrar la opción seleccionada en el TextField
                }
            });

            movieYear.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    year = Integer.parseInt(newValue.toString()); // Mostrar la opción seleccionada en el TextField
                    Prueba1.getInstance().setYear(year);
                }
            });

            movieName.setText(movie.getName());
            movieGender.setText(movie.getGender());
            movieDuration.setText(String.valueOf(movie.getDuration()));
            movieDescription.setText(movie.getDescription());

            // Falta Autor y actor que son arrays, toca con menu desplegable

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void help(Movie movie) {
    }

    @FXML
    public boolean saveMovie() throws IOException {

        ac.getListMovies().addAll(ac.loadMovie("Movie"));

        nameMovieFirst = Prueba1.getInstance().getNameMovie();
        listAuthors = Prueba1.getInstance().getListAuthors();
        listActors = Prueba1.getInstance().getListActors();
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

        String movieAuthorString = selectedAuthor.getText();

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

        String movieActorString = selectedActor.getText();

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

        year = Prueba1.getInstance().getYear();
        if (ac.updateMovie(nameMovieFirst, movieName.getText(), movieDescription.getText(), movieDurations, listAuthors,
                listActors, movieGenderString, year)) {
            MovieRepository.getInstance().updateMovie(nameMovieFirst, movieName.getText(), movieDescription.getText(), movieDurations, listAuthors,
            listActors, movieGenderString, year);
            tableView.refresh();
            showMovie();
        }
        return true;
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
        myStage.close();
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
    }

    @FXML
    public void newAuthor() {
        if (listAuthors.contains(selectedAuthor.getText())) {
            selectedAuthor.clear();
        } else {
            listAuthors.add(selectedAuthor.getText());
            System.out.println("SELECT-->"+selectedAuthor.getText());
            //selectedAuthor.clear();
        }
    }

    @FXML
    public void newActor() {
        if (listActors.contains(selectedActor.getText())) {
            selectedActor.clear();
        } else {
            listActors.add(selectedActor.getText());
            System.out.println("SELECT-->"+selectedActor.getText());
        }
    }

    @FXML
    public void deleteAuthor() {

        for (int index = 0; index < listAuthors.size(); index++) {
            if (listAuthors.get(index).equals(selectedAuthor.toString())) {
                listAuthors.remove(index);
            }
        }
    }

    @FXML
    public void deleteActor() {
        for (int index = 0; index < listActors.size(); index++) {
            if (listActors.get(index).equals(selectedActor.toString())) {
                listActors.remove(index);
            }
        }
    }

    public void choiceBox(){
        
    }

    @FXML
    public void saveAuthor() {
        if (listAuthors.contains(selectedAuthor.getText())) {
            selectedAuthor.clear();
        } else {
            listAuthors.add(selectedAuthor.getText());
            //selectedAuthor.clear();
        }
    }

    @FXML
    public void saveActor() {
        if (listActors.contains(selectedActor.getText())) {
            selectedActor.clear();
        } else {
            listActors.add(selectedActor.getText());
            //selectedActor.clear();
        }
    }
}
