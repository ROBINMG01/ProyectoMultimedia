package co.edu.uptc.viewFx;

import java.beans.EventHandler;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewVisit extends Application {

    private AdminController adminController;
    private ComboBox<String> viewOptionsComboBox;
    private TextArea resultsTextArea;

    public ViewVisit(AdminController adminController) {
        this.adminController = adminController;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Inicializar elementos de la interfaz de usuario
        viewOptionsComboBox = new ComboBox<>(FXCollections.observableArrayList(
                "Ver películas", "Ver series", "Buscar"));
        resultsTextArea = new TextArea();
        resultsTextArea.setEditable(false);

        // Botón para activar la búsqueda o las opciones de visualización
        Button actionButton = new Button("Ver/Buscar");
        actionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedOption = viewOptionsComboBox.getValue();
                if (selectedOption.equals("Ver películas")) {
                    // Obtener la lista de películas
                    List<Movie> movies = adminController.getListMovies();

                    // Mostrar la lista de películas en un ListView
                    ListView<Movie> moviesListView = new ListView<>(FXCollections.observableArrayList(movies));
                    moviesListView.setCellFactory(new Callback<ListView<Movie>, ListCell<Movie>>() {
                        @Override
                        public ListCell<Movie> call(ListView<Movie> listView) {
                            return new ListCell<Movie>() {
                                @Override
                                protected void updateItem(Movie movie, boolean empty) {
                                    super.updateItem(movie, empty);
                                    if (movie != null) {
                                        setText(movie.getName());
                                    }
                                }
                            };
                        }
                    });

                    resultsTextArea.getChildrenUnmodifiable().clear();
                    resultsTextArea.getChildrenUnmodifiable().add(moviesListView);
                } else if (selectedOption.equals("Ver series")) {
                    // Obtener la lista de series
                    List<Serie> series = adminController.getListSeries();

                    // Mostrar la lista de series en un ListView
                    ListView<Serie> seriesListView = new ListView<>(FXCollections.observableArrayList(series));
                    seriesListView.setCellFactory(new Callback<ListView<Serie>, ListCell<Serie>>() {
                        @Override
                        public ListCell<Serie> call(ListView<Serie> listView) {
                            return new ListCell<Serie>() {
                                @Override
                                protected void updateItem(Serie serie, boolean empty) {
                                    super.updateItem(serie, empty);
                                    if (serie != null) {
                                        setText(serie.getName());
                                    }
                                }
                            };
                        }
                    });

                    resultsTextArea.getChildrenUnmodifiable().clear();
                    resultsTextArea.getChildrenUnmodifiable().add(seriesListView);
                } else {
                    // Implementar la lógica para la búsqueda
                    resultsTextArea.setText("Función de búsqueda no implementada aún");
                }
            }
        });

        // Diseño de elementos (reemplazar con el diseño deseado)
        VBox layout = new VBox(10);
        layout.getChildren().addAll(viewOptionsComboBox, actionButton, resultsTextArea);
        layout.setAlignment(Pos.CENTER);

        // Crear y mostrar escena
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Visita a la vista");
        primaryStage.show();
    }
}
