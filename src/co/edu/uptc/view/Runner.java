package co.edu.uptc.view;

import java.awt.Component;
import java.awt.Image;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
                    JPanel panel = new JPanel();

                    // Crear un nuevo JList
                    JList<Movie> jList = new JList<Movie>(adminController.getListMovies().toArray(new Movie[0]));
                    // Establecer el renderizador de celdas del JList
                    jList.setCellRenderer(new DefaultListCellRenderer() {
                        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                boolean isSelected,
                                boolean cellHasFocus) {
                            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
                                    cellHasFocus);
                            Serie serie = (Serie) value;
                            label.setText(serie.getName());
                            return label;
                        }
                    });
                    adminController.viewMovies(adminController.getListMovies(), movie, jList,
                            panel);

                    // Agregar el JList a un JScrollPane
                    JScrollPane scrollPane = new JScrollPane(jList);

                    // Agregar el JScrollPane al JPanel
                    panel.add(scrollPane);

                    // Mostrar el JPanel en una ventana emergente JOptionPane
                    JOptionPane.showMessageDialog(null, panel, "Selec movie to vies", JOptionPane.PLAIN_MESSAGE);

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
