package co.edu.uptc.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Movie;

public class JsonFileManager {

    public void savePersonsToJson(List<Movie> movieList, String file) {
        try (FileWriter writer = new FileWriter(file)) {
            // Ruta relativa a la carpeta src
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(movieList, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar personas en JSON: " + e.getMessage(), e);
        }
    }

    public ArrayList<Movie> loadPersonsFromJson(String file) {
        ArrayList<Movie> movieAux = new ArrayList<>();

        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Movie>>() {
            }.getType();
            movieAux = new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar personas desde JSON: " + e.getMessage(), e);
        }

        return movieAux;
    }
}
