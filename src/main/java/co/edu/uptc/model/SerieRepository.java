package co.edu.uptc.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SerieRepository {

    private ObservableList<Serie> series = FXCollections.observableArrayList();

    // Instancia singleton
    private static SerieRepository instance;

    private SerieRepository() {}

    // Método para obtener la instancia
    public static SerieRepository getInstance() {
        if (instance == null) {
            instance = new SerieRepository();
        }
        return instance;
    }

    public ObservableList<Serie> getSeries() {
        return series;
    }

    public boolean addSerie(Serie serie) {
        try {
            series.add(serie);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}