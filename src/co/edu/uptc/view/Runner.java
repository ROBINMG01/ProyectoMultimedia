package co.edu.uptc.view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class Runner {
    public static void main(String[] args) {
        Serie serie = new Serie();
        Movie movie = new Movie();
        String name = "", description = "";
        int w = 0, duration = 0, au = 0, aux = 0;
        AdminController adminController = new AdminController();
        String optionHome[] = { "View series", "View movies" };
        do {
            UIManager.put("OptionPane.cancelButtonText", "Exit the application");
            UIManager.put("OptionPane.okButtonText", "select");
            // icono de la imagen
            ImageIcon icon = new ImageIcon("img\\recetario.jpg");

            // Obtener la imagen del ImageIcon original
            Image originalImage = icon.getImage();

            // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
            int newWidth = 250;
            int newHeight = 250;

            // Redimensionar la imagen
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // Crear un nuevo ImageIcon a partir de la imagen redimensionada
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Opciones de Inicio", JOptionPane.QUESTION_MESSAGE, resizedIcon, optionHome,
                    optionHome[0]);
            // para que influya en todos
            UIManager.put("OptionPane.cancelButtonText", "Back");
            UIManager.put("OptionPane.okButtonText", "Accept");
            if (seleccion == null) {
                seleccion = "exit";
            }
            switch (seleccion) {
                case "View series":
                    adminController.viewSeries(adminController.getListSeries(), serie, "Selec serie to view");

                    break;
                case "View movies":
                    adminController.viewMovies(adminController.getListMovies(), movie, "Selec movie to view");
                    
                case "exit":
                    JOptionPane.showMessageDialog(null, "salida con éxito");
                    w = 10;
                    break;

                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (w != 10);

    }

}
