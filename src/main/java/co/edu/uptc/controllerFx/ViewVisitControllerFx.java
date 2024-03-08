package co.edu.uptc.controllerFx;

import java.util.List;

import javax.swing.JOptionPane;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;

public class ViewVisitControllerFx {

    @FXML
    private ComboBox<String> comboBoxOptions;

    @FXML
    private ListView<String> listViewResults;

    @FXML
    private Label detailsLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Button buttonAcept;
    
    @FXML
    private Button buttonMovie;

    @FXML
    private Button buttonSerie;

    @FXML
    private Button buttonSearch;

    @FXML
    private TextField inputSearch;

    private AdminController adminController;
    private ObservableList<String> observableListResults;

    public ViewVisitControllerFx() {
        this.adminController = new AdminController();
        this.observableListResults = FXCollections.observableArrayList();
        try {
            adminController.getListMovies().addAll(adminController.loadMovie("Movie"));
            adminController.getListSeries().addAll(adminController.loadSerie("Series"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        // Inicializar el ComboBox con las opciones de búsqueda
        List<String> options = List.of("View movies", "View series", "Search");
        comboBoxOptions.getItems().addAll(options);

        // Limpiar la lista de resultados al inicio
        listViewResults.getItems().clear();
    }

    @FXML
    public void handleSearchAction() {
        String selectedOption = comboBoxOptions.getSelectionModel().getSelectedItem();

        if (selectedOption == null) {
            showAlert("Error", "Please select a search option.");
            return;
        }

        switch (selectedOption) {
            case "View movies":
                displayMovies();
                break;
            case "View series":
                displaySeries();
                break;
            case "Search":
                handleSearch();
                break;
        }
    }

    private void displayMovies() {
        observableListResults.clear();
        List<Movie> movies = adminController.getListMovies();
        for (Movie movie : movies) {
            observableListResults.add(movie.getName());
        }
        listViewResults.setItems(observableListResults);

    }

    private void displaySeries() {
        observableListResults.clear();
        List<Serie> series = adminController.getListSeries();
        for (Serie serie : series) {
            observableListResults.add(serie.getName());
        }
        listViewResults.setItems(observableListResults);
    }

    private void handleSearch() {
        List<String> choices = List.of("Search by name", "Search by gender");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Search by name", choices);
        dialog.setTitle("Search Option");
        dialog.setHeaderText("Select a search option:");
        dialog.setContentText("Choose search option:");

        String searchOption = dialog.showAndWait().orElse(null);

        if (searchOption == null) {
            return;
        }

        switch (searchOption) {
            case "Search by name":
                searchByName();
                break;
            case "Search by gender":
                searchByGender();
                break;
        }
    }

    private void searchByName() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search by Name");
        dialog.setHeaderText("Enter the name of the item you want to search:");
        dialog.setContentText("Name:");

        String searchedItem = dialog.showAndWait().orElse(null);

        if (searchedItem == null) {
            return;
        }

        observableListResults.clear();
        List<Movie> movies = adminController.getListMovies();
        for (Movie movie : movies) {
            if (movie.getName().toLowerCase().contains(searchedItem.toLowerCase())) {
                observableListResults.add(movie.getName());
                viewMovie(movie.getName());
            }
        }

        List<Serie> series = adminController.getListSeries();
        for (Serie serie : series) {
            if (serie.getName().toLowerCase().contains(searchedItem.toLowerCase())) {
                observableListResults.add(serie.getName());
            }
        }

        listViewResults.setItems(observableListResults);

        if (observableListResults.isEmpty()) {
            showAlert("Search Result", "No results found for the search.");
        }
    }

    private void searchByGender() {
        List<String> choices = List.of("Action", "Comedy", "Drama", "Horror", "Sci-Fi");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Action", choices);
        dialog.setTitle("Search by Gender");
        dialog.setHeaderText("Select a gender:");
        dialog.setContentText("Choose gender:");

        String selectedGender = dialog.showAndWait().orElse(null);

        if (selectedGender == null) {
            return;
        }

        observableListResults.clear();
        List<Movie> movies = adminController.getListMovies();
        for (Movie movie : movies) {
            if (movie.getGender().equalsIgnoreCase(selectedGender)) {
                observableListResults.add(movie.getName());
            }
        }

        List<Serie> series = adminController.getListSeries();
        for (Serie serie : series) {
            if (serie.getGender().equalsIgnoreCase(selectedGender)) {
                observableListResults.add(serie.getName());
            }
        }

        listViewResults.setItems(observableListResults);

        if (observableListResults.isEmpty()) {
            showAlert("Search Result", "No results found for the search.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void viewMovie(String selectedMovie) {
        // Ver descripción de la película o serie
        for (Movie movie : adminController.getListMovies()) {
            if (movie.getName().equals(selectedMovie)) {
                String name = movie.getName();
                String gender = movie.getGender();
                int duration = movie.getDuration();
                String description = movie.getDescription();
                List<String> listAuthors = movie.getlistAuthors();
                List<String> listActors = movie.getListActors();
                JOptionPane.showMessageDialog(null, "\nName: " + name
                        + "\nGender: " + gender + "\nDuration: " + duration + "\nDescription: "
                        + description + "\nListaAuthors: " + listAuthors + "\nListActors: "
                        + listActors, "Movie Description: ", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }

    }
}
