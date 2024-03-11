package co.edu.uptc.controllerFx;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Serie;

public class ChapterListController {

    @FXML
    private ListView<Chapter> chapterList;

    @FXML
    private Button btnBack;

    private Serie currentSerie;

    public void setSerie(Serie serie) {
        this.currentSerie = serie;
        updateChapterList();
    }

    private void updateChapterList() {
        // Aquí debes actualizar la lista de capítulos basada en la serie actual
    }

    @FXML
    protected void handleButtonBack(ActionEvent event) {
        // Aquí debes volver a la vista del catálogo de series
    }

    @FXML
    protected void handleChapterSelection(MouseEvent event) {
        // Aquí debes abrir la vista del reproductor de video para el capítulo seleccionado
    }
}