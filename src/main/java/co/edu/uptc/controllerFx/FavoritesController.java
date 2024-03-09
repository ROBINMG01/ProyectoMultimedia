package co.edu.uptc.controllerFx;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import co.edu.uptc.model.Favorite;

public class FavoritesController {

    @FXML
    private ListView<Favorite> listFavorite;

    @FXML
    private TableView<Favorite> favoriteTable;

    @FXML
    private Button btnVisualice, btnDelete, BtnAdd, btnView, btnBack;

    @FXML
    private TableColumn<Favorite, String> column1, column2, column3;

    // Lista de favoritos para el ejemplo
    private ObservableList<Favorite> favorites = FXCollections.observableArrayList();

    @FXML
    private ListView<ObservableList<Favorite>> listFavoriteGroups;

    private void viewFavorite() {
        ObservableList<Favorite> selectedFavorites = listFavoriteGroups.getSelectionModel().getSelectedItem();
        if (selectedFavorites != null) {
            favoriteTable.setItems(selectedFavorites);
        }
    }

    @FXML
    public void initialize() {
        // Aquí puedes inicializar tus componentes, como llenar la ListView y la TableView con datos
        listFavorite.setItems(favorites);
        favoriteTable.setItems(favorites);

        // Aquí puedes agregar los listeners a los botones
        btnVisualice.setOnAction(event -> visualiceFavorite());
        btnDelete.setOnAction(event -> deleteFavorite());
        BtnAdd.setOnAction(event -> addFavorite());
        btnView.setOnAction(event -> viewFavorite());
        btnBack.setOnAction(event -> goBack());
    }

    private void visualiceFavorite() {
        // Implementa la lógica para visualizar un favorito
    }

    private void deleteFavorite() {
        // Implementa la lógica para eliminar un favorito
    }

    private void addFavorite() {
        // Implementa la lógica para agregar un favorito
    }



    private void goBack() {
        // Implementa la lógica para volver atrás
    }
}