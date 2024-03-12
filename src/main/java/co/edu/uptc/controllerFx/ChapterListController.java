package co.edu.uptc.controllerFx;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Serie;

public class ChapterListController {

    @FXML
    private ListView<Chapter> chapterList;

    @FXML
    private Button btnBack;

    @FXML
    public void handleViewButton(ActionEvent event) {
        Chapter selectedChapter = chapterList.getSelectionModel().getSelectedItem();
        if (selectedChapter != null) {
            openChapter(selectedChapter);
        }
    }

    private void openChapter(Chapter chapter) {
        // Aquí debes implementar la lógica para abrir el capítulo seleccionado.
        // Esto podría implicar abrir una nueva vista o reproducir un video, dependiendo de tu aplicación.
    }
    
    private Serie currentSerie;

    public void setSerie(Serie serie) {
        this.currentSerie = serie;
        updateChapterList();
    }

    private void updateChapterList() {
        // Obtén la lista de capítulos de la serie actual
        List<Chapter> chapters = (currentSerie != null) ? currentSerie.getChapters() : new ArrayList<>();

        // Comprueba si chapters es null antes de llamar a setAll()
        if (chapters != null) {
            // Actualiza la ListView con los capítulos
            chapterList.getItems().setAll(chapters);
        } else {
            // Si chapters es null, limpia la ListView
            chapterList.getItems().clear();
        }
    }

    public List<Chapter> getChapters() {
        if (currentSerie != null) {
            return currentSerie.getChapters();
        } else {
            return new ArrayList<>();
        }
    }

    @FXML
    protected void handleButtonBack(ActionEvent event) {
        // Aquí debes volver a la vista del catálogo de series
    }

    @FXML
    protected void handleChapterSelection(MouseEvent event) {
        // Aquí debes abrir la vista del reproductor de video para el capítulo seleccionado
    }

    public void loadSeries() {
        String filePath = "src/main/java/co/edu/uptc/persistence/Series.json"; // Reemplaza esto con la ruta a tu archivo JSON

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Gson gson = new Gson();

            JsonObject series = gson.fromJson(reader, JsonObject.class);

            // Ahora puedes acceder a los datos en el objeto JSON
            String name = series.get("name").getAsString();
            JsonArray listSeason = series.getAsJsonArray("listSeason");

            // Suponiendo que solo hay una temporada
            JsonObject season1 = listSeason.get(0).getAsJsonObject();
            JsonArray listChapters = season1.getAsJsonArray("listChapters");

            // Ahora puedes crear tus objetos Chapter y agregarlos a chapterList
            for (int i = 0; i < listChapters.size(); i++) {
                JsonObject chapter = listChapters.get(i).getAsJsonObject();
                String chapterName = chapter.get("name").getAsString();
                int duration = chapter.get("duration").getAsInt();

                Chapter newChapter = new Chapter(chapterName, duration);
                chapterList.getItems().add(newChapter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}