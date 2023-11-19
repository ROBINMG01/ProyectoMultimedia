package co.edu.uptc.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class AdminView {

    /*static String menu = "1. Add movie\n" + "2. Add series\n" + "3. Show movie\n" + "4. Show series\n"
            + "5. Update movie\n" + "6. Update series\n" + "7. Delete movie\n" + "8.Delete serie\n" + "9. Exit\n";*/
    static int option = 0, option2 = 0;
    static String name = "", duration = "", nameUpdate = "", description = "", actor = "", chapters = "",
            listActors = "";
    static String selection = "";
    static boolean verefication = false;
    static String[] menu = { "Add Movie", "Add Serie", "Show Movies", "Show Series", "Update Movies",
            "Update Series" };
    static AdminController Ac = new AdminController();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menuAdmin();
    }

    public static void menuAdmin() {

        do {
            do {
                try {
                    selection = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                            "Opciones de Administrador", JOptionPane.QUESTION_MESSAGE, null, menu, menu[0]);
                    /*
                     * System.out.println(menu);
                     * System.out.println("Input option");
                     * option = sc.nextInt();
                     * sc.nextLine();
                     */
                    verefication = true;
                } catch (java.util.InputMismatchException e) {
                    // System.out.println("Invalid option");
                    sc.nextLine();
                    verefication = false;
                }
            } while (verefication == false);
            switch (selection) {
                case "Add Movie":
                    if (addMovie()) {
                        JOptionPane.showMessageDialog(null, "Movie add sucesfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Movie add sucesfully");
                    }
                    // System.out.println("------------------------");
                    break;
                case "Add Serie":
                    if (addSerie()) {
                        System.out.println("Movie add sucesfully");
                    } else {
                        System.out.println("Movie wasn't add sucesfully");
                    }
                    System.out.println("------------------------");
                    break;
                case "Show Movies":
                    ///for (Movie movies : Ac.showListMovies()) {

                        JOptionPane.showMessageDialog(null, Ac.showListMovies());
                        //System.out.println(movies);
                    //}
                    //System.out.println("------------------------");
                    break;
                case "Show Series":
                    for (Serie series : Ac.showListSeries()) {
                        System.out.println(series);
                    }
                    System.out.println("------------------------");
                    break;
                case "Update Movies":
                    if (updateMovie()) {
                        System.out.println("Movie update sacesfully");
                    } else {
                        System.out.println("Movie wasn't update sucesfully");
                    }
                    break;
                case "Update Series":
                    if (updateSeries()) {
                        System.out.println("Serie update sacesfully");
                    } else {
                        System.out.println("Serie wasn't update sucesfully");
                    }
                    break;
                case "8":
                    System.out.println("Title");
                    name = sc.nextLine();
                    System.out.println("Description");
                    description = sc.nextLine();
                    do {
                        try {
                            System.out.println("Duration in minutes");
                            duration = sc.nextLine();
                            verefication = true;
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Invalid option");
                            sc.nextLine();
                            verefication = false;
                        }

                    } while (verefication == false);
                    System.out.println("actors");
                    listActors = sc.nextLine();
                    //Ac.addMoviee(name, description, duration, listActors);
                    //ySystem.out.println(Ac.showMovie());
                default:
                    break;
            }
        } while (option != 9);
        sc.close();
    }

    public static boolean addMovie() {
        JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));

        JTextField nameField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField durationField = new JTextField();

        panel.add(new JLabel("Name of the movie:"));
        panel.add(nameField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Duration:"));
        panel.add(durationField);
        int result = JOptionPane.showConfirmDialog(null, panel, "Add Movie", JOptionPane.OK_CANCEL_OPTION);
        
        String name = nameField.getText();
        String description = descriptionField.getText();
        String duration = durationField.getText();
        String listActors = authorField.getText();

        return Ac.addMovie(name, description, duration, listActors);
    }

    public static boolean addSerie() {
        JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));

        JTextField nameField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField durationField = new JTextField();
        JTextField chaptersField = new JTextField();

        panel.add(new JLabel("Serie name"));
        panel.add(nameField);
        panel.add(new JLabel("Description"));
        panel.add(descriptionField);
        panel.add(new JLabel("Authors"));
        panel.add(authorField);
        panel.add(new JLabel("Duration"));
        panel.add(durationField);
        int result = JOptionPane.showConfirmDialog(null, panel, "Add Serie", JOptionPane.OK_CANCEL_OPTION);
        
        String name = nameField.getText();
        String description = descriptionField.getText();
        String duration = durationField.getText();
        String listActors = authorField.getText();
        String chapters = chaptersField.getText();

        return Ac.addSerie(name, description, duration, listActors, chapters);
    }

    public static boolean updateMovie() {
        JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));
        String nameUpdate = JOptionPane.showInputDialog("Input Movie Update");


        if (Ac.searchMovie(nameUpdate) != -1) {
            JTextField nameField = new JTextField();
            JTextField authorField = new JTextField();
            JTextField descriptionField = new JTextField();
            JTextField durationField = new JTextField();
    
            panel.add(new JLabel("Name of the movie:"));
            panel.add(nameField);
            panel.add(new JLabel("Author:"));
            panel.add(authorField);
            panel.add(new JLabel("Description:"));
            panel.add(descriptionField);
            panel.add(new JLabel("Duration:"));
            panel.add(durationField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Update Movie", JOptionPane.OK_CANCEL_OPTION);
            
            String name = nameField.getText();
            String description = descriptionField.getText();
            String duration = durationField.getText();
            String listActors = authorField.getText();
    
            return Ac.updateMovie(nameUpdate, name, description, duration, listActors);
        }
        return false;
    }

    public static boolean updateSeries() {


        JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));
        if (Ac.searchSeries(nameUpdate) != -1) {
            JTextField nameField = new JTextField();
            JTextField authorField = new JTextField();
            JTextField descriptionField = new JTextField();
            JTextField durationField = new JTextField();
    
            panel.add(new JLabel("Name of the movie:"));
            panel.add(nameField);
            panel.add(new JLabel("Author:"));
            panel.add(authorField);
            panel.add(new JLabel("Description:"));
            panel.add(descriptionField);
            panel.add(new JLabel("Duration:"));
            panel.add(durationField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Update Movie", JOptionPane.OK_CANCEL_OPTION);
            
            String name = nameField.getText();
            String description = descriptionField.getText();
            String duration = durationField.getText();
            String listActors = authorField.getText();
    
            return Ac.updateMovie(nameUpdate, name, description, duration, listActors);
        }
        return false;
    }

    public static void ShowGrafic() {
        JPanel panel = new JPanel();
        JTextField nameField = new JTextField(10);
        JTextField descriptionField = new JTextField(10);
        JTextField durationField = new JPasswordField(10);
        JTextField actorsField = new JPasswordField(10);

        /*
         * String seleccion = (String) JOptionPane.showInputDialog(null,
         * "Seleccione una opción:",
         * "Opciones de Inicio", JOptionPane.QUESTION_MESSAGE, resizedIcon, optionsHome,
         * optionsHome[0]);
         */

        // Agregar los componentes al panel
        panel.add(new JLabel("Name Movie:"));
        panel.add(nameField);
        panel.add(new JLabel("Description"));
        panel.add(descriptionField);
        panel.add(new JLabel("Duration"));
        panel.add(durationField);
        panel.add(new JLabel("Actors"));
        panel.add(actorsField);

        String name = nameField.getText();
        String destription = descriptionField.getText();
        String duration = durationField.getText();
        String actors = actorsField.getText();
    }
}
