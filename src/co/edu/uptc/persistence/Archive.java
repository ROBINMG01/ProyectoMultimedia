package co.edu.uptc.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

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
            writer.write(String.format("%-20s %-20s %-20s %-20s %-10s", "FirstName", "LastName",
                    "Email", "Password", "Role"));
            writer.newLine();
            writer.write(
                    "--------------------------------------------------------------------------------------------------------------------");
            writer.newLine();

            // Itera sobre los usuarios y escribe sus detalles
            for (User user : users) {
                // encriptadooooo password
                String passwordEncritada =
                        controlerInitialMenuView.encriptar(user.getPassword(), 123);

                writer.write(String.format("%-20s %-20s %-20s %-20s %-10s",
                        user.getFirstName() + ",", user.getLastName() + ",", user.getEmail() + ",",
                        passwordEncritada + ",", user.getRole()));

                // Agrega los nombres de las películas en una columna si la lista no es nula
                ArrayList<Movie> movies = user.getListMovies();
                if (movies != null && !movies.isEmpty()) {
                    writer.newLine(); // Nueva línea antes de las películas
                    writer.write("Movies:");
                    for (Movie movie : movies) {
                        writer.newLine();
                        writer.write(String.format("  %-30s", movie.getName()));
                    }
                }

                // Agrega los nombres de las series en una columna si la lista no es nula
                ArrayList<Serie> series = user.getListSeries();
                if (series != null && !series.isEmpty()) {
                    writer.newLine(); // Nueva línea antes de las series
                    writer.write("Series:");
                    for (Serie serie : series) {
                        writer.newLine();
                        writer.write(String.format("  %-30s", serie.getName()));
                    }
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
                writer.println(user.getFirstName() + "," + user.getLastName() + ","
                        + user.getEmail() + "," + user.getPassword() + "," + user.getRole());
            }

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

    // Método para guardar información de series en un archivo de texto
    public void saveSeriesInfoToFile(ArrayList<User> userList, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (User user : userList) {
                for (Serie serie : user.getListSeries()) {
                    writer.println(user.getFirstName() + "," + serie.getName() + ","
                            + serie.getGender() + "," + serie.getDescription() + ","
                            + serie.getDuration() + "," + serie.getListActors() + ","
                            + serie.getListChapters() + "," + serie.getlistAuthors());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer información de series desde un archivo de texto
    public ArrayList<User> readSeriesInfoFromFile(String filename) {
        ArrayList<User> userList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String userName = parts[0];
                    String seriesName = parts[1];
                    String gender = parts[2];
                    String description = parts[3];
                    int duration = Integer.parseInt(parts[4]);
                    ArrayList<String> listActors =
                            new ArrayList<>(Arrays.asList(parts[5].split(",")));
                    ArrayList<String> listChapters =
                            new ArrayList<>(Arrays.asList(parts[6].split(",")));
                    ArrayList<String> listAuthors =
                            new ArrayList<>(Arrays.asList(parts[7].split(",")));

                    // Buscar el usuario en la lista o crear uno nuevo si no existe
                    User user = null;
                    for (User existingUser : userList) {
                        if (existingUser.getFirstName().equals(userName)) {
                            user = existingUser;
                            break;
                        }
                    }

                    if (user == null) {
                        user = new User(userName, "", "", "", Role.user);
                        userList.add(user);
                    }

                    // Agregar la serie al usuario
                    Serie serie = new Serie(seriesName, description, duration, listAuthors,
                            listChapters, gender, listActors);
                    user.getListSeries().add(serie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }



}
