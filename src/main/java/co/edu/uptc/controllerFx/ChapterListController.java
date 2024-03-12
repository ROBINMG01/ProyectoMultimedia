package co.edu.uptc.controllerFx;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.ChapterModel;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;

public class ChapterListController {

    @FXML
    private ListView<Chapter> chapterList;

    private ChapterModel chapterModel;

    private SerieCatalogController serieCatalogController;

    @FXML
    private Button btnBack;

    @FXML
    public void initialize() {
        chapterModel = ChapterModel.getInstance();
        chapterList.setItems(chapterModel.getChapters());
    }

    private void openChapter(Chapter chapter) {
        Pane contentContainer = (Pane) chapterList.getScene().lookup("#contenedor_contenido");
        contentContainer.setVisible(true);
    }

    private Serie currentSerie;

    public void setSerie(Serie serie) {
        this.currentSerie = serie;
        updateChapterList();
    }

    private void updateChapterList() {
        List<Chapter> chapters = new ArrayList<>();
        if (currentSerie != null && currentSerie.getListSeason() != null) {
            for (Season season : currentSerie.getListSeason()) {
                if (season.getListChapters() != null) {
                    chapters.addAll(season.getListChapters());
                }
            }
        }
        chapterList.getItems().setAll(chapters);
    }

    public List<Chapter> getChapters() {
        if (currentSerie != null) {
            return currentSerie.getChapters();
        } else {
            return new ArrayList<>();
        }
    }

    @FXML
    private void handleButtonBack(ActionEvent event) throws IOException {
        // Get the reference to the previous view
        Stage stage = (Stage) chapterList.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/SerieCatalogView.fxml"));
        if (serieCatalogController == null) {
            serieCatalogController = new SerieCatalogController();
        }
        fxmlLoader.setController(serieCatalogController);
        Parent root = fxmlLoader.load();

        stage.getScene().setRoot(root);

        stage.setTitle("Catálogo de Series");
    }

    @FXML
    private void handleViewButton(ActionEvent event) {
        Chapter selectedChapter = chapterList.getSelectionModel().getSelectedItem();
        if (selectedChapter != null) {
            // Abre la ventana del reproductor de video y reproduce el tráiler del capítulo
            // seleccionado
            openVideoWindow(selectedChapter.getTrailerUrl());
        }
    }

    private void openVideoWindow(String videoUrl) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/Video.fxml"));
            VideoController videoController = new VideoController();
            loader.setController(videoController);
            Parent root = loader.load();

            videoController.playVideo(videoUrl);
            videoController.setOriginalView("ChapterListController");

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Reproductor de video");
            stage.setScene(scene);
            stage.show();

            // Cerrar la ventana actual
            Stage myStage = (Stage) this.btnBack.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadChapters(List<Chapter> chapters) {
        chapterModel.setChapters(FXCollections.observableArrayList(chapters));
    }

    @FXML
    private void handleChapterSelection(MouseEvent event) {
        Chapter selectedChapter = chapterList.getSelectionModel().getSelectedItem();

        // Highlight the selected item
        chapterList.getSelectionModel().select(selectedChapter);

        // Load additional information of the chapter
        if (selectedChapter != null) {
            // Show basic information of the chapter
            Label chapterNameLabel = (Label) chapterList.getScene().lookup("#nombre_capitulo");
            chapterNameLabel.setText(selectedChapter.getName());

            Label chapterDurationLabel = (Label) chapterList.getScene().lookup("#duracion_capitulo");
            chapterDurationLabel.setText(String.format("%d minutes", selectedChapter.getDuration()));

            // Enable the "View chapter" button
            Button viewChapterButton = (Button) chapterList.getScene().lookup("#ver_capitulo");
            viewChapterButton.setDisable(false);
        } else {
            // Disable the "View chapter" button
            Button viewChapterButton = (Button) chapterList.getScene().lookup("#ver_capitulo");
            viewChapterButton.setDisable(true);
        }
    }
}
