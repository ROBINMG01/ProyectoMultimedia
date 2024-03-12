package co.edu.uptc.controllerFx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import co.edu.uptc.model.Serie;
import java.lang.reflect.Type;
import javafx.scene.text.Text;
import javafx.scene.Node;

public class SearchSerieController {

    @FXML
    private TextField searchName;

    @FXML
    private Button btnView;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSearch;

    private ObservableList<Serie> allSeries;

    @FXML
    private Label labelDescription;

    @FXML
    private ImageView imageSearch;

    @FXML
    private ListView<Serie> listViewSeries;

    @FXML
    public void initialize() {
        
        searchName.textProperty().addListener((observable, oldValue, newValue) -> searchSeries());
        listViewSeries.setCellFactory(param -> new ListCell<>() {
            private ImageView imageView = new ImageView();
            private Text text = new Text();

            @Override
            protected void updateItem(Serie serie, boolean empty) {
                super.updateItem(serie, empty);
                if (empty || serie == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(new Image(serie.getImageUrl()));
                    imageView.setFitWidth(100); // Establece el ancho de la imagen a 100
                    imageView.setFitHeight(100); // Establece la altura de la imagen a 100
                    imageView.setPreserveRatio(true); // Mantiene la relación de aspecto de la imagen
                    text.setText(serie.getName());
                    VBox vbox = new VBox(imageView, text);
                    setGraphic(vbox);
                }
            }
        });
        btnSearch.setOnAction(event -> searchSeries());

        loadSeries();
    }

    private Stage searchStage = null; // Guarda una referencia a la ventana de Vista1

    @FXML
protected void handleButton(ActionEvent event) {
    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    abrirSearch(currentStage);
}

private void abrirSearch(Stage currentStage) {
    try {
        // Comprueba si la ventana ya existe
        if (searchStage == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Vista1.fxml"));
            Parent searchView = fxmlLoader.load();

            // Crear una nueva ventana para la vista de Search
            searchStage = new Stage();
            searchStage.setTitle("Búsqueda");
            searchStage.setScene(new Scene(searchView));
        }

        currentStage.close(); // Cierra la ventana actual

        searchStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private void loadSeries() {
        Gson gson = new Gson();
        Type serieListType = new TypeToken<ArrayList<Serie>>() {
        }.getType();

        try (FileReader reader = new FileReader("src\\main\\java\\co\\edu\\uptc\\persistence\\Series.json")) {
            ArrayList<Serie> serieList = gson.fromJson(reader, serieListType);
            if (allSeries == null) {
                allSeries = FXCollections.observableArrayList();
            } else {
                allSeries.clear();
            }
            allSeries.addAll(serieList);
            listViewSeries.setItems(allSeries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchSeries() {
        String searchText = searchName.getText().toLowerCase();

        ObservableList<Serie> filteredSeries = FXCollections.observableArrayList(
                allSeries.stream()
                        .filter(serie -> serie.getName().toLowerCase().contains(searchText))
                        .collect(Collectors.toList()));

        listViewSeries.setItems(filteredSeries);
    }
}