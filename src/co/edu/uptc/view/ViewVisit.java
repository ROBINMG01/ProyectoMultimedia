package co.edu.uptc.view;

import java.awt.Image;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class ViewVisit {
    private AdminController adminController;

    public ViewVisit(AdminController ad) {
        this.adminController = ad;
    }

    public void visitView() {
        int w = 0, aux = 0;
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
                    DefaultListModel<String> mmodelSeries = new DefaultListModel<>();
                    for (int i = 0; i < adminController.getListSeries().size(); i++) {
                        mmodelSeries.addElement("[" + (i + 1) + "]" + adminController.getListSeries().get(i).getName());
                    }

                    // Crear una instancia de JList con el modelo de lista
                    JList<String> listSeries = new JList<>(mmodelSeries);

                    // Agregar la lista a un JScrollPane para que tenga barras de desplazamiento
                    JScrollPane scrollPaneSeries = new JScrollPane(listSeries);

                    // Mostrar el JScrollPane en un diálogo
                    aux = Integer.parseInt(JOptionPane.showInputDialog(null, scrollPaneSeries));

                    Serie serieSelec = adminController.getListSeries().get(aux - 1);
                    JOptionPane.showMessageDialog(null, serieSelec.toString());

                    break;
                case "View movies":
                    DefaultListModel<String> modelMovies = new DefaultListModel<>();
                    for (int i = 0; i < adminController.getListMovies().size(); i++) {
                        modelMovies.addElement("[" + (i + 1) + "]" + adminController.getListMovies().get(i).getName());
                    }

                    // Crear una instancia de JList con el modelo de lista
                    JList<String> listMovies = new JList<>(modelMovies);

                    // Agregar la lista a un JScrollPane para que tenga barras de desplazamiento
                    JScrollPane scrollPaneMovies = new JScrollPane(listMovies);

                    // Mostrar el JScrollPane en un diálogo
                    aux = Integer.parseInt(JOptionPane.showInputDialog(null, scrollPaneMovies));

                    Movie movieSelect = adminController.getListMovies().get(aux - 1);
                    JOptionPane.showMessageDialog(null, movieSelect.toString());

                    break;

                case "exit":
                    JOptionPane.showMessageDialog(null, "successful exit");
                    w = 10;
                    break;

                default:
                    System.out.println("invalid option");
                    break;
            }
        } while (w != 10);
    }

}
