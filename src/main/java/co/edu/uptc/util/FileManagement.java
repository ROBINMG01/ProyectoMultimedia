package co.edu.uptc.util;

import java.io.File;
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
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

public class FileManagement {
    private File file;
    public static final String filePath = "src\\main\\java\\co\\edu\\uptc\\persistence\\";
    public static final String fileExtension = ".json";
    // Formato de fecha para la deserialización (ejemplo para ISO 8601)

    public boolean writeJsonToFile(String fileName, Object obj) {
        try {
            // Crea un Gson para la serialización a JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Crea un FileWriter sobreescribiendo el archivo existente
            FileWriter writer = new FileWriter(filePath + fileName + ".json");

            // Escribir el objeto JSON al archivo
            gson.toJson(obj, writer);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Movie> loadMoviesFromJson(String fileName) {
        file = new File(filePath + fileName + fileExtension);
        List<Movie> movieAux = new ArrayList<>();
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Movie>>() {
            }.getType();
            movieAux = new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return movieAux;
    }

    public List<Serie> loadSeriesFromJson(String fileName) {
        file = new File(filePath + fileName + fileExtension);
        List<Serie> serieAux = new ArrayList<>();
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Movie>>() {
            }.getType();
            serieAux = new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return serieAux;
    }

    public List<User> loadUsersFromJson(String fileName) {
        file = new File(filePath + fileName + fileExtension);
        List<User> userAux = new ArrayList<>();
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<User>>() {
            }.getType();
            userAux = new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return userAux;
    }

}
