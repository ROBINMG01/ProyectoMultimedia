package co.edu.uptc.persistence;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uptc.model.User;

public class Archive {
    



//metodo que genera un archivo de los usuarios por el admin

public void archiveUser(ArrayList<User> users, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Escribir encabezados (si es necesario)
            writer.write("FirstName,LastName,Email,Password,Role,ListMovies,ListSeries");
            writer.newLine();

            // Escribir cada usuario en una nueva línea
            for (User user : users) {
                String userLine = String.format("%s,%s,%s,%s,%s,%s,%s",
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole(),
                        user.getListMovies(),
                        user.getListSeries());

                writer.write(userLine);
                writer.newLine();
            }

            System.out.println("Archivo creado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
