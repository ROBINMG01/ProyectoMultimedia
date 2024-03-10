package co.edu.uptc.controllerFx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class AdminControllerFx {

    private AdminController ac;
    private Movie movie = new Movie();
    ArrayList<String> listAuthors = new ArrayList<>();
    ArrayList<String> listActors = new ArrayList<>();
    List<Movie> list = new ArrayList<>();

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
    private Button saveMovieButton;

    @FXML
    private Button serieButton;

    @FXML
    private Button authorButton;

    @FXML
    private Button actorButton;

    @FXML
    private Button newAuthorButton;

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

    public void initialize() {
        ac = new AdminController();
        this.movie = new Movie();
        if (movie != null) {

            System.out.println("------------DATES----------");
            System.out.println(movie.getName());
            System.out.println(movie.getGender());
            System.out.println(movie.getDuration());
            System.out.println(movie.getYear());

            movieName.setText(movie.getName());
            movieGender.setText(movie.getGender());
            movieDuration.setText(String.valueOf(movie.getDuration()));
            movieYear.setText(String.valueOf(movie.getYear()));
        }

        ac.getListMovies().addAll(ac.loadMovie("Movie"));

        if (MovieRepository.getInstance().getMovies().isEmpty()) {

            MovieRepository.getInstance().getMovies().addAll(ac.getListMovies());
            tableView.setItems(MovieRepository.getInstance().getMovies());
        }
        // Asignar las películas a la tabla
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Movie, Boolean> column = new TableColumn<>("Edit");
        column.setCellFactory(tc -> new ButtonCell());
        tableView.getColumns().add(column);

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

    @FXML
    private void showFormCreateMovie() throws IOException {
        AdminViewFx.setRoot("ListMoviesAdmin");
    }

    @FXML
    private void showSerie() throws IOException {
        AdminViewFx.setRoot("listSeries");
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

    @FXML
    public void FileEditMovie(Movie movie) throws IOException {
        this.movie = movie;

        System.out.println("------------DATES111111111----------");
        System.out.println(movie.getName());
        System.out.println(movie.getGender());
        System.out.println(movie.getDuration());
        System.out.println(movie.getYear());

        AdminViewFx.setRoot("EditMovie");
    }
}
