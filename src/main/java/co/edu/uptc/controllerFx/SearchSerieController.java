package co.edu.uptc.controllerFx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
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

    @FXML
    private TableView<Serie> tableSerie;

    @FXML
    private TableColumn<Serie, String> nameSerie;

    @FXML
    private TableColumn<Serie, String> genderSerie;

    @FXML
    private TableColumn<Serie, String> durationSerie;

    @FXML
    private TableColumn<Serie, String> ListSerie;

    private ObservableList<Serie> allSeries;

    @FXML
    private Label labelDescription;

    @FXML
    private ImageView imageSearch;

    @FXML
    public void initialize() {

        tableSerie.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Image image = null;
                if (newSelection.getImageUrl() != null) {
                    if (newSelection.getImageUrl().startsWith("http")) {
                        image = new Image(newSelection.getImageUrl());
                    } else {
                        image = new Image(getClass().getResourceAsStream(newSelection.getImageUrl()));
                    }
                }
                if (image != null) {
                    imageSearch.setImage(image);
                    imageSearch.setFitWidth(324); // Ajusta el ancho a 324
                    imageSearch.setFitHeight(267); // Ajusta la altura a 267
                    imageSearch.setPreserveRatio(true); // Mantiene la relación de aspecto
                }
            }
        });

        nameSerie.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderSerie.setCellValueFactory(new PropertyValueFactory<>("gender"));
        durationSerie.setCellValueFactory(new PropertyValueFactory<>("duration"));
        ListSerie.setCellValueFactory(new PropertyValueFactory<>("ListSeason"));

        searchName.textProperty().addListener((observable, oldValue, newValue) -> searchSeries());
        loadSeries();

        tableSerie.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                imageSearch.setImage(new Image(newSelection.getImageUrl()));
                labelDescription.setWrapText(true);
                labelDescription.setText(newSelection.getDescription());
            }
        });
    }

    @FXML
    protected void handleButton(ActionEvent event) {
        abrirVista1();
    }

    private void abrirVista1() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Search.fxml"));
            Parent movieCatalogView = fxmlLoader.load();

            // Crear una nueva ventana para la vista del catálogo de películas
            Stage stage = new Stage();
            stage.setTitle("Catálogo de películas");
            stage.setScene(new Scene(movieCatalogView));
            stage.show();

            Stage myStage = (Stage) this.btnBack.getScene().getWindow();

            myStage.close();
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
            tableSerie.setItems(allSeries);
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

        tableSerie.setItems(filteredSeries);
    }
}