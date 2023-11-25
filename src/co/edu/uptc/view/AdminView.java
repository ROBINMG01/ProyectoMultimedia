package co.edu.uptc.view;

import javax.swing.*;
import co.edu.uptc.controller.AdminController;
import java.awt.*;
import java.util.ArrayList;

public class AdminView {

    static AdminController ac = new AdminController();

    public static void main(String[] args) {
        System.out.println("Hola");
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
            String selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Opciones de Administrador", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            performSelectedaction(selectedaction);
            if (selectedaction != null) {
                condition = false;
            } else {
                condition = true;
            }
        } while (condition == false);
    }

    public static void performSelectedaction(String selectedaction) {
        switch (selectedaction) {
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
        int dutation2 = 0;
        String gender = "";
        String actor = "";
        int ver = 0;
        boolean exit = false;
        int option = 0;
        ac.showListActorsTwo().clear();

        do {
            if (ver == 1) {
                name = "";
                description = "";
                duration = "";
                gender = "";
                ac.showListActorsTwo().clear();
            }
            do {
                JPanel panel = new JPanel(new GridLayout(4, 2));
                JTextField nameField = new JTextField(name);
                JTextField descriptionField = new JTextField(description);
                JTextField durationField = new JTextField(duration);
                JTextField genderField = new JTextField(gender);

                panel.add(new JLabel("Name of the movie:"));
                panel.add(nameField);
                panel.add(new JLabel("Description:"));
                panel.add(descriptionField);
                panel.add(new JLabel("Duration:"));
                panel.add(durationField);
                panel.add(new JLabel("Gender:"));
                panel.add(genderField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Add Movie", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    name = nameField.getText();
                    description = descriptionField.getText();
                    duration = durationField.getText();
                    gender = genderField.getText();

                    if (name.isEmpty() || description.isEmpty() || duration.isEmpty() || gender.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Failed to add movieeee");
                        exit = verification();
                        ver = 0;
                    } else {
                        try {
                            dutation2 = Integer.parseInt(duration);
                            do {
                                JPanel panell = new JPanel(new GridLayout(1, 2));
                                JTextField authorField = new JTextField(actor);
                                panell.add(new JLabel("Author:"));
                                panell.add(authorField);

                                option = JOptionPane.showConfirmDialog(null, panell,
                                        "Continue?", JOptionPane.YES_NO_OPTION);
                                exit = addActors(authorField);
                            } while (!exit);
                            ac.addMovie(name, description, dutation2, ac.showListActorsTwo(), gender);
                            exit = true;
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "la duracion debe ser un numero");
                            exit = false;
                        }
                        if (dutation2 != 0) {
                            option = JOptionPane.showConfirmDialog(null, "Do you want to add another movie?",
                                    "Continue?", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.OK_OPTION) {
                                ver = 1;
                                exit = false;
                            } else {
                                exit = true;
                            }
                        }
                    }
                } else {
                    exit = true;
                }
            } while (!exit);
        } while (!exit);
    }

    public static void addSerie() {

        String name = "";
        String description = "";
        String duration = "";
        int duration2 = 0;
        String actor = "";
        String chapter = "";
        String gender = "";
        int ver = 0;
        boolean exit = false;
        int option = 0;
        ac.showListActorsTwo().clear();
        ac.showListChaptersTwo();

        do {
            if (ver == 1) {
                name = "";
                description = "";
                duration = "";
                gender = "";
                ac.showListActorsTwo();
                ac.showListChaptersTwo();
            }
            do {
                JPanel panel = new JPanel(new GridLayout(4, 2));
                JTextField nameField = new JTextField(name);
                JTextField descriptionField = new JTextField(description);
                JTextField durationField = new JTextField(duration);
                JTextField genderField = new JTextField(gender);
                
                panel.add(new JLabel("Name of the serie"));
                panel.add(nameField);
                panel.add(new JLabel("Description"));
                panel.add(descriptionField);
                panel.add(new JLabel("Duration"));
                panel.add(durationField);
                panel.add(new JLabel("Gender"));
                panel.add(genderField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Add Serie", JOptionPane.OK_CANCEL_OPTION);
            
                if (result == JOptionPane.OK_OPTION) {
                    name = nameField.getText();
                    description = descriptionField.getText();
                    duration = durationField.getText();
                    gender = genderField.getText();

                    if (name.isEmpty() || description.isEmpty() || duration.isEmpty() || gender.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Failed to add serie");
                        exit = verification();
                        ver = 0;
                    } else {
                        try {
                            duration2 = Integer.parseInt(duration);
                            do {
                                JPanel panell = new JPanel(new GridLayout(1, 2));
                                JTextField authorField = new JTextField(actor);
                                panell.add(new JLabel("Author:"));
                                panell.add(authorField);

                                option = JOptionPane.showConfirmDialog(null, panell,
                                        "Continue?", JOptionPane.YES_NO_OPTION);
                                exit = addActors(authorField);
                            } while (!exit);
                            do {
                                exit = false;
                                JPanel panel2 = new JPanel(new GridLayout(1, 2));
                                JTextField chapterField = new JTextField(chapter);
                                panel2.add(new JLabel("Chapter:"));
                                panel2.add(chapterField);

                                option = JOptionPane.showConfirmDialog(null, panel2,
                                        "Continue?", JOptionPane.YES_NO_OPTION);
                                exit = addChapter(chapterField);
                            } while (!exit);
                            ac.addSerie(name, description, duration2, ac.showListActorsTwo(), ac.showListChaptersTwo(), gender);
                            exit = true;
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "la duracion debe ser un numero");
                            exit = false;
                        }
                        if (duration2 != 0) {
                            option = JOptionPane.showConfirmDialog(null, "Do you want to add another serie?",
                                    "Continue?", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.OK_OPTION) {
                                ver = 1;
                                exit = false;
                            } else {
                                exit = true;
                            }
                        }
                    }
                }else{
                    exit = true;
                }
            } while (!exit);
        }while (!exit);
    }

    public static void showMovies() {
        JTextArea textArea = new JTextArea(ac.showListMovies().toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "View Movies", JOptionPane.PLAIN_MESSAGE);
    }

    public static void showSeries() {
        JTextArea textArea = new JTextArea(ac.showListSeries().toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "View Series", JOptionPane.PLAIN_MESSAGE);
    }

    public static void updateMovie() {
        String name = "";
        String description = "";
        String duration = "";
        String listactors = "";
        String selectedaction = "";
        String gender = "";
        int ver = 0;
        boolean exit = false;
        boolean exit2 = false;
        String showNamesMovies[];

        do {
            //pasa los nombres de las peliculas de un ArrayList a un arreglo
            showNamesMovies = ac.namesMovies().toArray(new String[ac.namesMovies().size()]);
            selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Movies", JOptionPane.QUESTION_MESSAGE, null, showNamesMovies, showNamesMovies[0]);
            if (selectedaction != null) {
                do {
                    JPanel panel = new JPanel(new GridLayout(4, 2));
                    if (ver == 1) {
                        name = "";
                        description = "";
                        duration = "";
                        gender = "";
                    }
                    JTextField nameField = new JTextField(name);
                    JTextField descriptionField = new JTextField(description);
                    JTextField durationField = new JTextField(duration);
                    JTextField genderField = new JTextField(gender);

                    panel.add(new JLabel("Name of the movie:"));
                    panel.add(nameField);
                    panel.add(new JLabel("Description:"));
                    panel.add(descriptionField);
                    panel.add(new JLabel("Duration:"));
                    panel.add(durationField);
                    panel.add(new JLabel("Gender:"));
                    panel.add(genderField);

                    int result = JOptionPane.showConfirmDialog(null, panel, "New dates Movie",
                            JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        name = nameField.getText();
                        description = descriptionField.getText();
                        duration = durationField.getText();
                        gender = genderField.getText();
                        if (name.isEmpty() || description.isEmpty() || duration.isEmpty() || listactors.isEmpty()
                                || gender.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Failed to Update movie");
                            exit = verification();
                            ver = 0;
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
        String listactors = "";
        String chapters = "";
        String selectedaction = "";
        String gender = "";
        int ver = 0;
        boolean exit = false;
        boolean exit2 = false;
        String showNamesSeries[];

        do {
            showNamesSeries = ac.namesSeries().toArray(new String[ac.namesSeries().size()]);
            selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Movies", JOptionPane.QUESTION_MESSAGE, null, showNamesSeries, showNamesSeries[0]);

            if (selectedaction != null) {
                do {
                    JPanel panel = new JPanel(new GridLayout(6, 2));
                    if (ver == 1) {
                        name = "";
                        description = "";
                        duration = "";
                        listactors = "";
                        chapters = "";
                        gender = "";
                    }
                    JTextField nameField = new JTextField(name);
                    JTextField authorField = new JTextField(listactors);
                    JTextField descriptionField = new JTextField(description);
                    JTextField durationField = new JTextField(duration);
                    JTextField chaptersrsField = new JTextField(chapters);
                    JTextField genderField = new JTextField(gender);

                    panel.add(new JLabel("Name of the movie:"));
                    panel.add(nameField);
                    panel.add(new JLabel("Author:"));
                    panel.add(authorField);
                    panel.add(new JLabel("Description:"));
                    panel.add(descriptionField);
                    panel.add(new JLabel("Duration:"));
                    panel.add(durationField);
                    panel.add(new JLabel("Gender:"));
                    panel.add(genderField);
                    panel.add(new JLabel("Chapters:"));
                    panel.add(chaptersrsField);

                    int result = JOptionPane.showConfirmDialog(null, panel, "New dates Serie",
                            JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        name = nameField.getText();
                        description = descriptionField.getText();
                        duration = durationField.getText();
                        listactors = authorField.getText();
                        chapters = chaptersrsField.getText();
                        gender = genderField.getText();

                        if (name.isEmpty() || description.isEmpty() || duration.isEmpty() || listactors.isEmpty()
                                || chapters.isEmpty() || gender.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Failed to Update serie");
                            exit = verification();
                            ver = 0;
                        } else {
                            /*
                             * if (ac.updateSeries(selectedaction, name, description, duration, listactors,
                             * chapters,
                             * gender)) {
                             * JOptionPane.showMessageDialog(null, "Serie update successfully");
                             * ver = 1;
                             * option = JOptionPane.showConfirmDialog(null,
                             * "Do you want to update another serie?",
                             * "Continue?", JOptionPane.YES_NO_OPTION);
                             * if (option == JOptionPane.OK_OPTION) {
                             * ver = 1;
                             * exit = true;
                             * } else {
                             * exit = true;
                             * exit2 = true;
                             * }
                             * } else {
                             * JOptionPane.showMessageDialog(null, "Failed to update movie");
                             * exit = verification();
                             * ver = 0;
                             * }
                             */
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
        option = JOptionPane.showConfirmDialog(null, "Do you want try agan?",
                "Continue?", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean addActors(JTextField authorField) {
        int option = 0;
        String actor = "";

        if (option == JOptionPane.OK_OPTION) {
            actor = authorField.getText();
            if (actor.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Failed to add actor");
                return verification();
            } else {
                ac.addListActors(actor);
                option = JOptionPane.showConfirmDialog(null, "Do you want to add another actor?",
                        "Continue?", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    actor = "";
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }

    public static boolean addChapter(JTextField chapterField) {
        int option = 0;
        String chapter = "";

        if (option == JOptionPane.OK_OPTION) {
            chapter = chapterField.getText();
            if (chapter.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Failed to add chapter");
                return verification();
            } else {
                ac.addListChapters(chapter);
                option = JOptionPane.showConfirmDialog(null, "Do you want to add another chapter?",
                        "Continue?", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    chapter = "";
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }
}