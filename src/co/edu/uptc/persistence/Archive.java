package co.edu.uptc.persistence;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

public class Archive {
    



//metodo que genera un archivo de los usuarios por el admin

  public void archiveUsers(String fileName, ArrayList<User> users) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        // Encabezado
        writer.write("=======================================");
        writer.newLine();
        writer.write("           Lista de Usuarios");
        writer.newLine();
        writer.write("=======================================");
        writer.newLine();
        writer.newLine(); // Línea en blanco
    
        // Encabezados de columna
        writer.write(String.format("%-20s %-20s %-20s %-20s %-10s %-30s %-30s",
                "FirstName", "LastName", "Email", "Password", "Role", "ListMovies", "ListSeries"));
        writer.newLine();
        writer.write("--------------------------------------------------------------------------------------------------------------------");
        writer.newLine();
    
        // Itera sobre los usuarios y escribe sus detalles
        for (User user : users) {
            writer.write(String.format("%-20s %-20s %-20s %-20s %-10s",
                    user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole()));
    
            // Agrega los nombres de las películas si la lista no es nula
            ArrayList<Movie> movies = user.getListMovies();
            if (movies != null) {
                StringBuilder moviesTitles = new StringBuilder();
                for (Movie movie : movies) {
                    moviesTitles.append(movie.getName()).append(", ");
                }
                if (moviesTitles.length() > 0) {
                    moviesTitles.delete(moviesTitles.length() - 2, moviesTitles.length());
                }
                writer.write(String.format("%-30s", moviesTitles.toString()));
            } else {
                writer.write(String.format("%-30s", "N/A")); // Indica que no hay películas
            }
    
            // Agrega los nombres de las series si la lista no es nula
            ArrayList<Serie> series = user.getListSeries();
            if (series != null) {
                StringBuilder seriesTitles = new StringBuilder();
                for (Serie serie : series) {
                    seriesTitles.append(serie.getName()).append(", ");
                }
                if (seriesTitles.length() > 0) {
                    seriesTitles.delete(seriesTitles.length() - 2, seriesTitles.length());
                }
                writer.write(String.format("%-30s", seriesTitles.toString()));
            } else {
                writer.write(String.format("%-30s", "N/A")); // Indica que no hay series
            }
    
            writer.newLine();
            // Agrega más información según tus necesidades
            writer.newLine(); // Línea en blanco entre usuarios
        }
    } catch (IOException e) {
        e.printStackTrace();
        // Manejo de errores en caso de problemas al escribir en el archivo
    }
    }





}
