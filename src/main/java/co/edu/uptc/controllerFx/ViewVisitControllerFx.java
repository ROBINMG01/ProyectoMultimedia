package co.edu.uptc.controllerFx;

import java.util.List;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class ViewVisitControllerFx {

    @FXML
    private ListView<String> listViewResults;

    @FXML
    private ListView<String> listView2;

    @FXML
    private Label detailsLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonMovie;

    @FXML
    private Button buttonSerie;

    @FXML
    private TextField inputSearch;

    private AdminController adminController;
    private ObservableList<String> observableListResults;

    public ViewVisitControllerFx() {
        this.imageView = new ImageView();
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
        // Llamar a tu método displayMovies para inicializar la lista de películas
        displayMovies();
        displaySeries();
        settingListView();
        // Limpiar la lista de resultados al inicio
        listViewResults.getItems().clear();

    }

    @FXML
    public void handleMovieButton() { // Handle movie button click
        displayMovies();
    }

    @FXML
    public void handleSerieButton() { // Handle series button click
        displaySeries();
    }

    @FXML
    public void handleSearchOnType(KeyEvent event) {
        String searchText = inputSearch.getText().toLowerCase();
        observableListResults.clear();
        settingListView();

        // Filtrar y mostrar coincidencias en la lista
        List<Movie> movies = adminController.getListMovies();
        for (Movie movie : movies) {
            if (movie.getName().toLowerCase().contains(searchText)) {
                observableListResults.add(movie.getName());
            }
        }

        List<Serie> series = adminController.getListSeries();
        for (Serie serie : series) {
            if (serie.getName().toLowerCase().contains(searchText)) {
                observableListResults.add(serie.getName());
            }
        }

        listViewResults.setItems(observableListResults);
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

    public void viewMovie(String selectedMovie) {
        ObservableList<String> detailsList = FXCollections.observableArrayList();

        for (Movie movie : adminController.getListMovies()) {
            if (movie.getName().equals(selectedMovie)) {
                String name = movie.getName();
                String gender = movie.getGender();
                int duration = movie.getDuration();
                String description = movie.getDescription();
                String director = movie.getDirector();
                int year = movie.getYear();
                List<String> listAuthors = movie.getlistAuthors();
                List<String> listActors = movie.getListActors();

                // Realizar null checks
                if (name != null && gender != null && description != null 
                        && listAuthors != null
                        && listActors != null) {
                    // Formatear las listas antes de agregarlas al string
                    String formattedAuthors = String.join(", ", listAuthors);
                    String formattedActors = String.join(", ", listActors);

                    detailsList.addAll(
                            "Name: " + name,
                            "Gender: " + gender,
                            "Duration: " + duration,
                            "Description: " + description,
                            "Director:" + director,
                            "Year:" + year,
                            "Authors: " + formattedAuthors,
                            "Actors: " + formattedActors);

                    // Mostrar la información en el componente gráfico apropiado
                    listView2.setItems(detailsList);

                    // Establecer la imagen correspondiente
                    setImage(movie.getImageUrl());
                    return;
                }
            }
        }
        int conSeason = 0;
        int conChapter = 0;

        for (Serie serie : adminController.getListSeries()) {
            if (serie.getName().equals(selectedMovie)) {
                String name = serie.getName();
                String gender = serie.getGender();
                int duration = serie.getDuration();
                String description = serie.getDescription();
                String imageUrl = serie.getImageUrl();
                List<String> listAuthors = serie.getlistAuthors();
                List<String> listActors = serie.getListActors();
                List<Season> listSeasons = serie.getListSeason();

                // Realizar null checks
                if (name != null && gender != null && description != null && listAuthors != null
                        && listActors != null && listSeasons != null) {
                    // Formatear las listas antes de agregarlas al string
                    String formattedAuthors = String.join(", ", listAuthors);
                    String formattedActors = String.join(", ", listActors);

                    detailsList.addAll(
                            "Name: " + name,
                            "Gender: " + gender,
                            "Duration: " + duration,
                            "Description: " + description,
                            "Authors: " + formattedAuthors,
                            "Actors: " + formattedActors);

                    // Formatear información de las temporadas
                    detailsList.add("Seasons:");
                    for (Season season : listSeasons) {
                        conSeason += 1;
                        detailsList.add("Season:" + " [" + (conSeason) + "] " + season.getName() + "\n");
                        for (Chapter chapter : season.getListChapters()) {
                            conChapter += 1;
                            detailsList
                                    .add("--Chapter:" + " [" + (conChapter) + "] " + chapter.getName() + ": "
                                            + chapter.getDuration() + " min\n");
                        }
                    }

                    // Mostrar la información en el componente gráfico apropiado
                    listView2.setItems(detailsList);

                    // Establecer la imagen correspondiente
                    setImage(imageUrl);
                    return;
                }
            }
        }
    }

    private void setImage(String imagePath) {
        // Configurar la imagen en el ImageView
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                imageView.setImage(image);
            } catch (NullPointerException e) {
                // Si hay un error al cargar la imagen, imprimir la traza de la excepción
                e.printStackTrace();
                // Establecer una imagen predeterminada
                setDefaultImage();
            }
        } else {
            // Puedes establecer una imagen predeterminada si no hay una imagen específica
            setDefaultImage();
        }
    }

    private void setDefaultImage() {
        try {
            Image defaultImage = new Image(
                    getClass().getResourceAsStream("/co/edu/uptc/image/descarga.jpeg"));
            imageView.setImage(defaultImage);
        } catch (NullPointerException e) {
            e.printStackTrace();

        }
    }

    private void settingListView() {

        // Configurar el ListView y la ObservableList
        listViewResults.setItems(observableListResults);

        // Agregar el ChangeListener para detectar cambios en la selección del ListView
        listViewResults.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    // Aquí obtienes el nombre de la película seleccionada
                    String selectedMovieName = newValue.toString();
                    viewMovie(selectedMovieName);
                }
            }
        });
    }

}