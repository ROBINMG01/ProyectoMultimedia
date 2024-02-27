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
import co.edu.uptc.model.User;

public class FileManagement {
    private File file; // Variable de instancia para el archivo
    public static final String filePath = "src\\co\\edu\\uptc\\persistence\\"; // Ruta del archivo
    public static final String fileExtension = ".json"; // Extensión del archivo

    // Método para escribir un objeto en un archivo JSON
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

    // Método para cargar una lista de películas desde un archivo JSON
    public List<Movie> loadMoviesFromJson(String fileName) {
        file = new File(filePath + fileName + fileExtension); // Crea un nuevo archivo
        List<Movie> movieAux = new ArrayList<>(); // Lista para almacenar las películas
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Movie>>() {}.getType(); // Obtiene el tipo de la lista
            movieAux = new Gson().fromJson(reader, listType); // Convierte el JSON a una lista de películas
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return movieAux; // Devuelve la lista de películas
    }

    // Método para cargar una lista de usuarios desde un archivo JSON
    public List<User> loadUsersFromJson(String fileName) {
        file = new File(filePath + fileName + fileExtension); // Crea un nuevo archivo
        List<User> userAux = new ArrayList<>(); // Lista para almacenar los usuarios
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<User>>() {}.getType(); // Obtiene el tipo de la lista
            userAux = new Gson().fromJson(reader, listType); // Convierte el JSON a una lista de usuarios
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return userAux; // Devuelve la lista de usuarios
    }

}