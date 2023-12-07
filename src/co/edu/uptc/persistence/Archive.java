package co.edu.uptc.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.controller.ControlerInitialMenuView;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Role;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

public class Archive {
    private ControlerInitialMenuView controlerInitialMenuView;

    public Archive(ControlerInitialMenuView controlerInitialMenuView) {
        this.controlerInitialMenuView = controlerInitialMenuView;

    }

    public Archive() {

    }

    // metodo que genera un archivo de los usuarios por el admin

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
            writer.write(
                    "--------------------------------------------------------------------------------------------------------------------");
            writer.newLine();

            // Itera sobre los usuarios y escribe sus detalles
            for (User user : users) {
                // encriptadooooo password
                String passwordEncritada = controlerInitialMenuView.encriptar(user.getPassword(), 123);

                writer.write(String.format("%-20s %-20s %-20s %-20s %-10s",
                        user.getFirstName() + ",", user.getLastName() + ",", user.getEmail() + ",",
                        passwordEncritada + ",", user.getRole()));

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

    // Método para guardar información en un archivo de texto
    public void saveUserInfoToFile(ArrayList<User> users, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (User user : users) {
                writer.println(user.getFirstName() + "," + user.getLastName() + "," + user.getEmail() + ","
                        + user.getPassword() + "," + user.getRole());
            }
            System.out.println("Información guardada en el archivo " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer información desde un archivo de texto
    public ArrayList<User> readUserInfoFromFile(String filename) {
        ArrayList<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String firstName = parts[0];
                    String lastName = parts[1];
                    String email = parts[2];
                    String password = parts[3];
                    String rol = parts[4];
                    if (rol.equals("user")) {
                        userList.add(new User(firstName, lastName, email, password, Role.user));
                    } else {
                        userList.add(new User(firstName, lastName, email, password, Role.admin));
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }

}
