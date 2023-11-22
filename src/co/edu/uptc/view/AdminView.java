package co.edu.uptc.view;

import javax.swing.*;
import co.edu.uptc.controller.AdminController;
import java.awt.*;
import java.util.ArrayList;

public class AdminView {

    static AdminController Ac = new AdminController();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Admin View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(2, 2));

        String[] options = { "Add Movie", "Add Serie", "View Movies", "View Series", "Update Movie", "Update Serie" };
        boolean condition = false;
        do {
            String selectedAction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Opciones de Administrador", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            performSelectedAction(selectedAction);
            if (selectedAction != null) {
                condition = false;
            } else {
                condition = true;
            }
        } while (condition == false);
    }

    public static void performSelectedAction(String selectedAction) {
        switch (selectedAction) {
            case "Add Movie":
                addMovie();
                break;
            case "Add Serie":
                addSerie();
                break;
            case "View Movies":
                showMovies();
                break;
            case "View Series":
                showSeries();
                break;
            case "Update Movie":
                System.out.println("golaa");
                updateMovie();
                break;
            case "Update Serie":
                updateSerie();
                break;
        }
    }

    public static void addMovie() {
        String name = "";
        String description = "";
        String duration = "";
        String listActors = "";
        int ver = 0;
        boolean exit = false;
        int option = 0;

        do {
            JPanel panel = new JPanel(new GridLayout(4, 2));
            if (ver == 1) {
                name = "";
                description = "";
                duration = "";
                listActors = "";
            }
            JTextField nameField = new JTextField(name);
            JTextField authorField = new JTextField(listActors);
            JTextField descriptionField = new JTextField(description);
            JTextField durationField = new JTextField(duration);

            panel.add(new JLabel("Name of the movie:"));
            panel.add(nameField);
            panel.add(new JLabel("Author:"));
            panel.add(authorField);
            panel.add(new JLabel("Description:"));
            panel.add(descriptionField);
            panel.add(new JLabel("Duration:"));
            panel.add(durationField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Add Movie", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                name = nameField.getText();
                description = descriptionField.getText();
                duration = durationField.getText();
                listActors = authorField.getText();

                if (name.isEmpty() || description.isEmpty() || duration.isEmpty() || listActors.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Failed to add movieeee");
                    exit = verification();
                    ver = 0;
                } else {
                    if (Ac.addMovie(name, description, duration, listActors)) {
                        JOptionPane.showMessageDialog(null, "Movie added successfully");
                        ver = 1;
                        option = JOptionPane.showConfirmDialog(null, "Do you want to add another movie?",
                                "Continue?", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            ver = 1;
                            exit = false;
                        } else {
                            exit = true;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add movie");
                        exit = verification();
                        ver = 0;
                    }
                }
            } else {
                exit = true;
            }
        } while (!exit);

    }

    public static void addSerie() {

        String name = "";
        String description = "";
        String duration = "";
        String listActors = "";
        String chapters = "";
        int ver = 0;
        boolean exit = false;
        int option = 0;

        do {
            JPanel panel = new JPanel(new GridLayout(5, 2));
            if (ver == 1) {
                name = "";
                description = "";
                duration = "";
                listActors = "";
                chapters = "";
            }
            JTextField nameField = new JTextField(name);
            JTextField authorField = new JTextField(listActors);
            JTextField descriptionField = new JTextField(description);
            JTextField durationField = new JTextField(duration);
            JTextField chaptersField = new JTextField();

            panel.add(new JLabel("Name of the movie"));
            panel.add(nameField);
            panel.add(new JLabel("Author"));
            panel.add(authorField);
            panel.add(new JLabel("Description"));
            panel.add(descriptionField);
            panel.add(new JLabel("Duration"));
            panel.add(durationField);
            panel.add(new JLabel("Charapters"));
            panel.add(chaptersField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Add Serie", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                name = nameField.getText();
                description = descriptionField.getText();
                duration = durationField.getText();
                listActors = authorField.getText();
                chapters = chaptersField.getText();

                if (name.isEmpty() || description.isEmpty() || duration.isEmpty() || listActors.isEmpty()
                        || chapters.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Failed to add serie");
                    exit = verification();
                    ver = 0;
                } else {
                    if (Ac.addSerie(name, description, duration, listActors, chapters)) {
                        JOptionPane.showMessageDialog(null, "Serie added successfully");
                        ver = 1;
                        option = JOptionPane.showConfirmDialog(null, "Do you want to add another serie?",
                                "Continue?", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            ver = 1;
                            exit = false;
                        } else {
                            exit = true;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add serie");
                        exit = verification();
                        ver = 0;
                    }
                }
            } else {
                exit = true;
            }
        } while (!exit);
    }

    public static void showMovies() {
        JTextArea textArea = new JTextArea(Ac.showListMovies().toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "View Movies", JOptionPane.PLAIN_MESSAGE);
    }

    public static void showSeries() {
        JTextArea textArea = new JTextArea(Ac.showListSeries().toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "View Series", JOptionPane.PLAIN_MESSAGE);
    }

    public static void updateMovie() {
        String name = "";
        String description = "";
        String duration = "";
        String listActors = "";
        String selectedAction = "";
        int ver = 0;
        boolean exit = false;
        boolean exit2 = false;
        int option = 0;
        ArrayList<String> namesMovies = new ArrayList<>();
        String showNamesMovies[];

        for (int i = 0; i < Ac.showListMovies().size(); i++) {
            namesMovies.add(Ac.showListMovies().get(i).getName());
        }

        showNamesMovies = namesMovies.toArray(new String[namesMovies.size()]);
        do {
            selectedAction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Movies", JOptionPane.QUESTION_MESSAGE, null, showNamesMovies, showNamesMovies[0]);

            if (selectedAction != null) {
                do {
                    JPanel panel = new JPanel(new GridLayout(5, 2));
                    if (ver == 1) {
                        name = "";
                        description = "";
                        duration = "";
                        listActors = "";
                    }
                    JTextField nameField = new JTextField(name);
                    JTextField authorField = new JTextField(listActors);
                    JTextField descriptionField = new JTextField(description);
                    JTextField durationField = new JTextField(duration);

                    panel.add(new JLabel("Name of the movie:"));
                    panel.add(nameField);
                    panel.add(new JLabel("Author:"));
                    panel.add(authorField);
                    panel.add(new JLabel("Description:"));
                    panel.add(descriptionField);
                    panel.add(new JLabel("Duration:"));
                    panel.add(durationField);

                    int result = JOptionPane.showConfirmDialog(null, panel, "New dates Movie",
                            JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        name = nameField.getText();
                        description = descriptionField.getText();
                        duration = durationField.getText();
                        listActors = authorField.getText();
                        System.out.println("Nombre enviado "+selectedAction);
                        if (name.isEmpty() || description.isEmpty() || duration.isEmpty() || listActors.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Failed to Update movie");
                            exit = verification();
                            ver = 0;
                        } else {
                            if (Ac.updateMovie(selectedAction, name, description, duration, listActors)) {
                                JOptionPane.showMessageDialog(null, "Movie update successfully");
                                ver = 1;
                                option = JOptionPane.showConfirmDialog(null, "Do you want to update another movie?",
                                        "Continue?", JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.OK_OPTION) {
                                    ver = 1;
                                    exit = true;
                                } else {
                                    exit = true;
                                    exit2 = true;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to update movie");
                                exit = verification();
                                ver = 0;
                            }
                        }
                    } else {
                        exit = true;
                    }
                } while (!exit);
            } else {
                exit2 = true;
            }
        } while (!exit2);
    }

    public static void updateSerie() {

        String name = "";
        String description = "";
        String duration = "";
        String listActors = "";
        String chapters = "";
        String selectedAction = "";
        int ver = 0;
        boolean exit = false;
        boolean exit2 = false;
        int option = 0;
        ArrayList<String> namesSerie = new ArrayList<>();
        String showNamesSeries[];

        for (int i = 0; i < Ac.showListSeries().size(); i++) {
            namesSerie.add(Ac.showListSeries().get(i).getName());
        }

        showNamesSeries = namesSerie.toArray(new String[namesSerie.size()]);

        do {
            selectedAction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Movies", JOptionPane.QUESTION_MESSAGE, null, showNamesSeries, showNamesSeries[0]);

            if (selectedAction != null) {
                do {
                    JPanel panel = new JPanel(new GridLayout(5, 2));
                    if (ver == 1) {
                        name = "";
                        description = "";
                        duration = "";
                        listActors = "";
                        chapters = "";
                    }
                    JTextField nameField = new JTextField(name);
                    JTextField authorField = new JTextField(listActors);
                    JTextField descriptionField = new JTextField(description);
                    JTextField durationField = new JTextField(duration);
                    JTextField chaptersrsField = new JTextField(chapters);


                    panel.add(new JLabel("Name of the movie:"));
                    panel.add(nameField);
                    panel.add(new JLabel("Author:"));
                    panel.add(authorField);
                    panel.add(new JLabel("Description:"));
                    panel.add(descriptionField);
                    panel.add(new JLabel("Duration:"));
                    panel.add(durationField);
                    panel.add(new JLabel("Chapters:"));
                    panel.add(chaptersrsField);

                    int result = JOptionPane.showConfirmDialog(null, panel, "New dates Serie",
                            JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        name = nameField.getText();
                        description = descriptionField.getText();
                        duration = durationField.getText();
                        listActors = authorField.getText();
                        chapters = chaptersrsField.getText();

                        if (name.isEmpty() || description.isEmpty() || duration.isEmpty() || listActors.isEmpty() || chapters.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Failed to Update serie");
                            exit = verification();
                            ver = 0;
                        } else {
                            if (Ac.updateSeries(selectedAction, name, description, duration, listActors, chapters)) {
                                JOptionPane.showMessageDialog(null, "Serie update successfully");
                                ver = 1;
                                option = JOptionPane.showConfirmDialog(null, "Do you want to update another serie?",
                                        "Continue?", JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.OK_OPTION) {
                                    ver = 1;
                                    exit = true;
                                } else {
                                    exit = true;
                                    exit2 = true;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to update movie");
                                exit = verification();
                                ver = 0;
                            }
                        }
                    } else {
                        exit = true;
                    }
                } while (!exit);
            } else {
                exit2 = true;
            }
        } while (!exit2);
    }

    public static boolean verification() {
        int option = 0;
        option = JOptionPane.showConfirmDialog(null, "Do you want try agan update?",
                "Continue?", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            return false;
        } else {
            return true;
        }
    }
}