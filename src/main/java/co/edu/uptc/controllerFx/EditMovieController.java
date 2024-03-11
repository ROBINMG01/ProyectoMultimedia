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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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

    //@FXML
    //private JCheckBox menuButton;
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

    private ArrayList<String> listMenu = new ArrayList<>();

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
        this.movieYear = new TextField();
        this.movieDuration = new TextField();
    }

    public void help(Movie movie) {
        //String name = movie.getName();

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uptc/Fxml/EditMovie.fxml"));
            Parent root = loader.load();

            movieName = (TextField) loader.getNamespace().get("movieName");
            movieYear = (TextField) loader.getNamespace().get("movieYear");
            movieGender = (TextField) loader.getNamespace().get("movieGender");
            movieDuration = (TextField) loader.getNamespace().get("movieDuration");
            movieDescription = (TextField) loader.getNamespace().get("movieDescription");

            movieName.setText(movie.getName());
            movieYear.setText(String.valueOf(movie.getYear()));
            movieGender.setText(movie.getGender());
            movieDuration.setText(String.valueOf(movie.getDuration()));
            movieDescription.setText(movie.getDescription());

            //Falta Autor y actor que son arrays, toca con menu desplegable

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Stage myStage = (Stage) this.register.getScene().getWindow();
        // myStage.close();

    }

    public void active(Movie movie) {
        if (movie != null) {
            movieName.setText(movie.getName());
            movieGender.setText(movie.getGender());
            movieDuration.setText(String.valueOf(movie.getDuration()));
            movieYear.setText(String.valueOf(movie.getYear()));
        }
        // movieDescription.setText("HOLAAAAAAAAA");
    }

    public void initialize(Movie movie) {
        ac.getListMovies().addAll(ac.loadMovie("Movie"));

        //menuButton.getItems().addAll(opcion1, opcion2, opcion3);
        // help(movie);

        // if (movie != null) {
        // movieName.setText(movie.getName());
        // movieGender.setText(movie.getGender());
        // movieDuration.setText(String.valueOf(movie.getDuration()));
        // movieYear.setText(String.valueOf(movie.getYear()));
        // }

        if (MovieRepository.getInstance().getMovies().isEmpty()) {
            MovieRepository.getInstance().getMovies().addAll(ac.getListMovies());
            tableView.setItems(MovieRepository.getInstance().getMovies());
        }

        // nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        // genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        // durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        // yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        // TableColumn<Movie, Boolean> editColumn = new TableColumn<>("Edit");
        // editColumn.setCellFactory(tc -> new ButtonCell());
        // tableView.getColumns().add(editColumn);

        // tableView.setItems(MovieRepository.getInstance().getMovies());
    }

    @FXML
    private void showFormCreateMovies() throws IOException {
        AdminViewFx.setRoot("ListMovies");
    }

    @FXML
    private boolean saveMovie() throws IOException {

        if (ac.addMovie(movieName.getText(), movieDescription.getText(), Integer.parseInt(movieDuration.getText()),
                listAuthors, movieGender.getText(), listActors, Integer.parseInt(movieYear.getText()))) {
            MovieRepository.getInstance().addMovie(ac.getListMovies().get(ac.getListMovies().size() - 1));
        }

        // Clean fields on success
        clearInputFields();

        AdminViewFx.setRoot("ListMoviesAdmin");
        return true;
    }

    @FXML
    private void showFormCreateMovie() throws IOException {
        AdminViewFx.setRoot("ListMoviesAdmin");
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
        // AdminViewFx.setRoot("listSeries");
    }

    @FXML
    private void showMovie() throws IOException {
        // avf.loadFXML("ListMoviesAdmin");
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
        // AdminViewFx.setRoot("ListMoviesAdmin");
    }

    @FXML
    private void showEditMovie() throws IOException {
        AdminViewFx.setRoot("EditMovie");
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

    private void clearInputFields() {
        movieName.clear();
        movieGender.clear();
        movieDuration.clear();
        movieYear.clear();
        movieAuthor.clear();
        movieActor.clear();
    }
}
