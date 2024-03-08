package co.edu.uptc.controllerFx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MovieRepository;
import co.edu.uptc.viewFx.AdminViewFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminControllerFx {

    private AdminController ac;
    private Movie movie;
    ArrayList<String> listAuthors = new ArrayList<>();
    ArrayList<String> listActors = new ArrayList<>();
    ObservableList<Movie> listMovies;

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
        // Configurar las columnas
       /*for (int i = 0; i < ac.getListMovies().size(); i++) {
            movie.setName(ac.getListMovies().get(i).getName());
            movie.setGender(ac.getListMovies().get(i).getGender());
            movie.setDuration(ac.getListMovies().get(i).getDuration());
            movie.setYear(ac.getListMovies().get(i).getYear());*/

            ac = new AdminController();
            movie = new Movie();

           nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
           genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
           durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
           yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
           //descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

           // Obtener las películas del repositorio
           tableView.setItems(MovieRepository.getInstance().getMovies());
       //}
        
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
            alert.setContentText("El campo de la descripcion está vacío, por favor ingresa una descripcion para de la película.");
            alert.showAndWait();
            return false;
        }

        String movieActorString = movieActor.getText();

        if (movieActorString == null || movieActorString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El campo de la descripcion está vacío, por favor ingresa una descripcion para de la película.");
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

        //String, String, int, ObservableList<String>, ObservableList<String>, String, int
        if (ac.addMovie(movieName.getText(), movieDescription.getText(),movieDurations,listAuthors, movieGender.getText(), listActors,movieYearInt)) {
            //movie = new Movie(movieNameString, movieDescriptionString, movieDurations, listAuthors, listActors, movieGenderString, movieYearInt);
            MovieRepository.getInstance().addMovie(ac.getListMovies().get(ac.getListMovies().size()-1));
        }

        // Clean fields in case of success
        movieName.clear();
        movieGender.clear();
        movieDuration.clear();
        movieYear.clear();

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
    public boolean addAuthor() throws IOException{
        String movieAuthorString = movieAuthor.getText();

        if (movieAuthorString == null || movieAuthorString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El campo del autor está vacío, por favor ingresa el nombre del autor.");
            alert.showAndWait();
            return false;
        }else{
            listAuthors.add(movieAuthorString);
            return true;
        }
    }

    @FXML
    public boolean addActor() throws IOException{
        String movieActorString = movieActor.getText();
        listActors.add(movieActorString);

        if (movieActorString == null || movieActorString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El campo del actor está vacío, por favor ingresa el nombre del actor.");
            alert.showAndWait();
            return false;
        }else{
            listActors.add(movieActorString);
            return true;
        }
    }

    @FXML
    public void newAuthor(){
        movieAuthor.clear();
    }

    @FXML
    public void newActor(){
        movieActor.clear();
    }
}
