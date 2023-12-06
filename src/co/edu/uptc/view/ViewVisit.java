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

    public ViewVisit() {
    }

    public void visitView() {

        int w = 0, aux = 0;
        boolean inputIsValid = false;
        String optionHome[] = { "View series", "View movies" };
        do {
            UIManager.put("OptionPane.cancelButtonText", "Back");
            UIManager.put("OptionPane.okButtonText", "select");
            // icono de la imagen
            ImageIcon icon = new ImageIcon("C:\\Users\\Camilo Andres\\Desktop\\logo.jpg");

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
                    inputIsValid = false;

                    do {
                        try {
                            // Intenta obtener la entrada del usuario como String
                            String input = JOptionPane.showInputDialog(null, scrollPaneSeries);

                            // Si el usuario hace clic en Cancelar, sal del bucle
                            if (input == null) {
                                break;
                            }

                            // Intenta convertir la entrada a un entero
                            aux = Integer.parseInt(input);

                            // Verifica si el número está en el rango válido
                            if (aux >= 1 && aux <= adminController.getListSeries().size()) {
                                inputIsValid = true;
                            } else {
                                // Muestra un mensaje de error si el número está fuera de rango
                                JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            // Captura la excepción si la entrada no es un número entero
                            JOptionPane.showMessageDialog(null, "Ingrese un número entero válido.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } while (!inputIsValid);

                    // Si el usuario hizo clic en Cancelar, la variable 'aux' seguirá siendo 0
                    if (aux > 0) {
                        Serie serieSelec = adminController.getListSeries().get(aux - 1);
                        JOptionPane.showMessageDialog(null, serieSelec.toString());
                    }

                    break;
                case "View movies":

                    DefaultListModel<String> modelMovies = new DefaultListModel<>();
                    for (int i = 0; i < adminController.getListMovies().size(); i++) {
                        modelMovies
                                .addElement("[" + (i + 1) + "]" + adminController.getListMovies().get(i).getName());
                    }

                    // Crear una instancia de JList con el modelo de lista
                    JList<String> listMovies = new JList<>(modelMovies);

                    // Agregar la lista a un JScrollPane para que tenga barras de desplazamiento
                    JScrollPane scrollPaneMovies = new JScrollPane(listMovies);

                    // Mostrar el JScrollPane en un diálogo
                    inputIsValid = false;

                    do {

                        try {
                            // Intenta obtener la entrada del usuario como String
                            String input = JOptionPane.showInputDialog(null, scrollPaneMovies);

                            // Si el usuario hace clic en Cancelar, sal del bucle
                            if (input == null) {
                                break;
                            }

                            // Intenta convertir la entrada a un entero
                            aux = Integer.parseInt(input);

                            // Verifica si el número está en el rango válido
                            if (aux >= 1 && aux <= adminController.getListMovies().size()) {
                                inputIsValid = true;
                            } else {
                                // Muestra un mensaje de error si el número está fuera de rango
                                JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            // Captura la excepción si la entrada no es un número entero
                            JOptionPane.showMessageDialog(null, "Ingrese un número entero válido.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } while (!inputIsValid);

                    if (aux > 0) {
                        Movie movieSelect = adminController.getListMovies().get(aux - 1);
                        JOptionPane.showMessageDialog(null, movieSelect.toString());
                    }
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

    public String viewGenderMovie(String gender) {
        String optionHome[] = { "Accion", "Comedia", "Aventura", "Drama", "Terror" };
        UIManager.put("OptionPane.cancelButtonText", "Back");
        UIManager.put("OptionPane.okButtonText", "select");
        // icono de la imagen
        ImageIcon icon = new ImageIcon("C:\\Users\\Camilo Andres\\Desktop\\logo.jpg");

        // Obtener la imagen del ImageIcon original
        Image originalImage = icon.getImage();

        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
        int newWidth = 250;
        int newHeight = 250;

        // Redimensionar la imagen
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione un Genero:",
                "Opciones de pelicula", JOptionPane.QUESTION_MESSAGE, resizedIcon, optionHome,
                optionHome[0]);
        // para que influya en todos
        UIManager.put("OptionPane.cancelButtonText", "Back");
        UIManager.put("OptionPane.okButtonText", "Accept");
        if (seleccion == null) {
            seleccion = "exit";
        }
        switch (seleccion) {
            case "Accion":
                gender = "Accion";

                break;
            case "Comedia":
                gender = "Comedia";

                break;
            case "Aventura":
                gender = "Aventura";
                break;
            case "Drama":
                gender = "Drama";
                break;
            case "Terror":
                gender = "Terror";

                break;

            default:
                JOptionPane.showMessageDialog(null, "Comando invalido", "Error",
                        JOptionPane.ERROR_MESSAGE);
                break;
        }
        return gender;

    }

    public String viewGenderSerie(String gender) {
        String optionHome[] = { "Accion", "Comedia", "Aventura", "Drama", "Terror" };
        UIManager.put("OptionPane.cancelButtonText", "Back");
        UIManager.put("OptionPane.okButtonText", "select");
        // icono de la imagen
        ImageIcon icon = new ImageIcon("C:\\Users\\Camilo Andres\\Desktop\\logo.jpg");

        // Obtener la imagen del ImageIcon original
        Image originalImage = icon.getImage();

        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
        int newWidth = 250;
        int newHeight = 250;

        // Redimensionar la imagen
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione un Genero:",
                "Opciones de serie", JOptionPane.QUESTION_MESSAGE, resizedIcon, optionHome,
                optionHome[0]);
        // para que influya en todos
        UIManager.put("OptionPane.cancelButtonText", "Back");
        UIManager.put("OptionPane.okButtonText", "Accept");
        if (seleccion == null) {
            seleccion = "exit";
        }
        switch (seleccion) {
            case "Accion":
                gender = "Accion";

                break;
            case "Comedia":
                gender = "Comedia";

                break;
            case "Aventura":
                gender = "Aventura";
                break;
            case "Drama":
                gender = "Drama";
                break;
            case "Terror":
                gender = "Terror";

                break;

            default:
                JOptionPane.showMessageDialog(null, "Comando invalido", "Error",
                        JOptionPane.ERROR_MESSAGE);
                break;
        }
        return gender;

    }

}