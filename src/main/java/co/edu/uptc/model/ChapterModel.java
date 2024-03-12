package co.edu.uptc.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChapterModel {
    private static ChapterModel instance = null;
    private ObservableList<Chapter> chapters;

    private ChapterModel() {
        chapters = FXCollections.observableArrayList();
    }

    public static ChapterModel getInstance() {
        if (instance == null) {
            instance = new ChapterModel();
        }
        return instance;
    }

    public ObservableList<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(ObservableList<Chapter> chapters) {
        this.chapters = chapters;
    }
}
