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
import co.edu.uptc.model.Serie;

public class Runner {
    public static void main(String[] args) {
        Serie serie = new Serie();
        String name = "", description = "";
        int w = 0, duration = 0, au = 0, aux = 0;
        AdminController adminController = new AdminController();
        String optionHome[] = { "View series", "View movies", "add series", "add movies" };
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
                    adminController.viewSeries(adminController.getListSeries(), serie, "Selec movie to view");

                    break;
                case "add series":
                    w = 0;
                    while (w != 2) {
                        JPanel panel2 = new JPanel();
                        JTextField nameField = new JTextField(10);
                        JTextField descriptionField = new JTextField(10);
                        JTextField durationField = new JTextField(10);

                        // Agregar los componentes al panel
                        panel2.add(new JLabel("Nombre:"));
                        panel2.add(nameField);
                        panel2.add(new JLabel("Descripción:"));
                        panel2.add(descriptionField);
                        panel2.add(new JLabel("Duración:"));
                        panel2.add(durationField);
                        // Para que aparezca en vertical
                        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
                        // Color del panel
                        panel2.setBackground(Color.CYAN);

                        // Icono de la imagen
                        ImageIcon iconChef = new ImageIcon("img\\chef.png");

                        // Obtener la imagen del ImageIcon original
                        Image chefImg = iconChef.getImage();

                        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
                        newWidth = 150;
                        newHeight = 150;

                        // Redimensionar la imagen
                        Image chefImgs = chefImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
                        ImageIcon imgchef = new ImageIcon(chefImgs);

                        int resultado = JOptionPane.showConfirmDialog(null, panel2, "Ingrese sus datos",
                                JOptionPane.OK_CANCEL_OPTION, 1, imgchef);

                        if (resultado == JOptionPane.OK_OPTION) {
                            name = nameField.getText();
                            description = descriptionField.getText();
                            duration = 0;

                            try {
                                if (!durationField.getText().isEmpty()) {
                                    duration = Integer.parseInt(durationField.getText());
                                } else {
                                    throw new NumberFormatException(); // Forzar la excepción si el campo está vacío
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Ingrese una duración válida (número entero).");
                                // Puedes decidir si quieres limpiar el campo o dejar el valor anterior.
                                // En este ejemplo, estoy limpiando el campo.
                                durationField.setText("" + durationField);
                            }

                            if (!name.isEmpty() && !description.isEmpty() && duration != 0) {
                                // Agregar la serie
                                adminController.addSerie(name, description, duration, null, null);
                                au = 1; // No sé qué hace au, pero aquí lo mantengo
                            } else {
                                JOptionPane.showMessageDialog(null, "Complete todos los campos correctamente.");
                            }

                            int option = JOptionPane.showConfirmDialog(null, "¿Quiere registrar otra serie?",
                                    "¿Continuar?", JOptionPane.YES_NO_OPTION);

                            if (option == JOptionPane.NO_OPTION) {
                                // Salir del programa o realizar otras acciones necesarias

                                w = 2; // No sé qué hace w, pero aquí lo mantengo
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Operación cancelada");

                        }
                    }

                    break;
                case "add movies":
                    JPanel panel2 = new JPanel();
                    JTextField nameField = new JTextField(10);
                    JTextField descriptionField = new JTextField(10);
                    JTextField durationField = new JTextField(10);

                    // Agregar los componentes al panel
                    panel2.add(new JLabel("Nombre:"));
                    panel2.add(nameField);
                    panel2.add(new JLabel("Descripción:"));
                    panel2.add(descriptionField);
                    panel2.add(new JLabel("Duración:"));
                    panel2.add(durationField);
                    // Para que aparezca en vertical
                    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
                    // Color del panel
                    panel2.setBackground(Color.CYAN);

                    // Icono de la imagen
                    ImageIcon iconChef = new ImageIcon("img\\chef.png");

                    // Obtener la imagen del ImageIcon original
                    Image chefImg = iconChef.getImage();

                    // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
                    newWidth = 150;
                    newHeight = 150;

                    // Redimensionar la imagen
                    Image chefImgs = chefImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                    // Crear un nuevo ImageIcon a partir de la imagen redimensionada
                    ImageIcon imgchef = new ImageIcon(chefImgs);

                    int resultado = JOptionPane.showConfirmDialog(null, panel2, "Ingrese sus datos",
                            JOptionPane.OK_CANCEL_OPTION, 1, imgchef);

                    if (resultado == JOptionPane.OK_OPTION) {
                        name = nameField.getText();
                        description = descriptionField.getText();
                        duration = 0;

                        try {
                            if (!durationField.getText().isEmpty()) {
                                duration = Integer.parseInt(durationField.getText());
                            } else {
                                throw new NumberFormatException(); // Forzar la excepción si el campo está vacío
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Ingrese una duración válida (número entero).");
                            // Puedes decidir si quieres limpiar el campo o dejar el valor anterior.
                            // En este ejemplo, estoy limpiando el campo.
                            durationField.setText("" + durationField);
                        }

                        if (!name.isEmpty() && !description.isEmpty() && duration != 0) {
                            // Agregar la serie
                            adminController.addMovie(name, description, duration, null);
                            au = 1; // No sé qué hace au, pero aquí lo mantengo
                        } else {
                            JOptionPane.showMessageDialog(null, "Complete todos los campos correctamente.");
                        }

                        int option = JOptionPane.showConfirmDialog(null, "¿Quiere registrar otra serie?",
                                "¿Continuar?", JOptionPane.YES_NO_OPTION);

                        if (option == JOptionPane.NO_OPTION) {
                            // Salir del programa o realizar otras acciones necesarias

                            w = 2; // No sé qué hace w, pero aquí lo mantengo
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Operación cancelada");

                    }
                    break;
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
