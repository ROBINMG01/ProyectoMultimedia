package co.edu.uptc.view;

import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;



import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.ControlerInitialMenuView;
import co.edu.uptc.persistence.Archive;

public class AdminView {
    private AdminController ac;
    private ControlerInitialMenuView controlerInitialMenuView;
    ViewVisit viewVisit = new ViewVisit();

    public AdminView(AdminController ac, ControlerInitialMenuView controlerInitialMenuView) {
        this.ac = ac;
        this.controlerInitialMenuView=controlerInitialMenuView;
    }

    public void menuAdmin() {
        boolean condition = false;

        do {
            String[] options = { "Add Movie", "Add Serie", "View Movies", "View Series", "Update Movie", "Update Serie",
                    "deleteMovie", "deleteSerie", "userRegisters", "Exit" };
            condition = false;
            UIManager.put("OptionPane.cancelButtonText", "Cancel");
            UIManager.put("OptionPane.okButtonText", "Ok");
            String selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Opciones de Administrador", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (selectedaction == null) {
                condition = true;
                // selectedaction = "exit";
                break;
            } else if (selectedaction.equals("exit")) {
                condition = true;

            } else {
                condition = false;
                selected(selectedaction);
            }
        } while (condition == false);
    }

    public void selected(String selectedaction) {
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
                updateMovie();
                break;
            case "Update Serie":
                updateSerie();
                break;
            case "deleteMovie":
                deleteMovie();
                break;
            case "deleteSerie":
                deleteSerie();
                break;
            case "userRegisters":
              Archive userManager = new Archive();
             File directory = new File("ruta_del_directorio");
              if (!directory.exists()) {
            directory.mkdirs(); // Crea los directorios si no existen
        }
        userManager.archiveUser(controlerInitialMenuView.users(), "ProjectMultimedia\\ProyectoMultimedia\\src\\co\\edu\\uptc\\persistence");
       
                break;
            case "Exit":
                break;
        }
    }

    public void addMovie() {
        String name = "";
        String description = "";
        String duration = "";
        int dutation2 = 0;
        String gender = "";
        String author = "";
        String actor = "";
        int ver = 0;
        boolean exit = false;
        boolean exit2 = false;
        int option = 0;
        ac.showlistAuthors().clear();
        ac.showlistActors().clear();

        do {
            if (ver == 1) {
                name = "";
                description = "";
                duration = "";
                gender = "";
                ac.showlistAuthors().clear();
                ac.showlistActors().clear();
            }
            do {
                JPanel panel = new JPanel(new GridLayout(4, 2));
                JTextField nameField = new JTextField(name);
                JTextField descriptionField = new JTextField(description);
                JTextField durationField = new JTextField(duration);

                panel.add(new JLabel("Name of the movie:"));
                panel.add(nameField);
                panel.add(new JLabel("Description:"));
                panel.add(descriptionField);
                panel.add(new JLabel("Duration:"));
                panel.add(durationField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Add Movie", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    name = nameField.getText();
                    description = descriptionField.getText();
                    duration = durationField.getText();

                    gender = viewVisit.viewGenderMovie(gender);

                    if (name.isEmpty() || description.isEmpty() || duration.isEmpty() || gender.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Failed to add movie");
                        exit = verification();
                        if (exit == true) {
                            exit2 = true;
                        }
                        ver = 0;
                    } else {
                        try {
                            dutation2 = Integer.parseInt(duration);
                            do {
                                JPanel panell = new JPanel(new GridLayout(1, 2));
                                JTextField authorField = new JTextField(author);
                                panell.add(new JLabel("Author:"));
                                panell.add(authorField);

                                option = JOptionPane.showConfirmDialog(null, panell,
                                        "Continue?", JOptionPane.YES_NO_OPTION);
                                exit = addAuthors(authorField);
                            } while (!exit);
                            if (!arrayAuthors().isEmpty()) {
                                do {
                                    JPanel panell = new JPanel(new GridLayout(1, 2));
                                    JTextField actorField = new JTextField(actor);
                                    panell.add(new JLabel("Actor:"));
                                    panell.add(actorField);

                                    option = JOptionPane.showConfirmDialog(null, panell,
                                            "Continue?", JOptionPane.YES_NO_OPTION);
                                    exit = addActors(actorField);
                                } while (!exit);
                            }
                            if (!arrayActors().isEmpty()) {
                                ac.addMovie(name, description, dutation2, arrayAuthors(), gender, arrayActors());
                                JOptionPane.showMessageDialog(null, "Movie added sucessfully");
                                exit = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Movie wasn't added");
                                exit = verification();
                                ver = 0;
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "No input a number in duration");
                            exit = false;
                        }
                        if (dutation2 != 0 && exit != false) {
                            option = JOptionPane.showConfirmDialog(null, "Do you want to add another movie?",
                                    "Continue?", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.OK_OPTION) {
                                ver = 1;
                                exit = true;
                                exit2 = false;
                            } else {
                                exit = true;
                                exit2 = true;
                            }
                        }
                    }
                } else {
                    exit = true;
                    exit2 = true;
                }
            } while (!exit);
        } while (!exit2);
    }

    public void addSerie() {

        String name = "";
        String description = "";
        String duration = "";
        int duration2 = 0;
        String author = "";
        String actor = "";
        String chapter = "";
        String gender = "";
        int ver = 0;
        boolean exit = false;
        boolean exit2 = false;
        int option = 0;
        ac.showlistAuthors().clear();
        ac.showListChaptersTwo().clear();
        ac.showlistActors().clear();

        do {
            if (ver == 1) {
                name = "";
                description = "";
                duration = "";
                gender = "";
                ac.showlistAuthors().clear();
                ac.showListChaptersTwo().clear();
                ac.showlistActors().clear();
            }
            do {
                JPanel panel = new JPanel(new GridLayout(4, 2));
                JTextField nameField = new JTextField(name);
                JTextField descriptionField = new JTextField(description);
                JTextField durationField = new JTextField(duration);

                panel.add(new JLabel("Name of the serie"));
                panel.add(nameField);
                panel.add(new JLabel("Description"));
                panel.add(descriptionField);
                panel.add(new JLabel("Duration"));
                panel.add(durationField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Add Serie", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    name = nameField.getText();
                    description = descriptionField.getText();
                    duration = durationField.getText();
                    gender = viewVisit.viewGenderSerie(gender);

                    if (name.isEmpty() || description.isEmpty() || duration.isEmpty() || gender.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Failed to add serie");
                        exit = verification();
                        ver = 0;
                    } else {
                        try {
                            duration2 = Integer.parseInt(duration);
                            do {
                                JPanel panell = new JPanel(new GridLayout(1, 2));
                                JTextField authorField = new JTextField(author);
                                panell.add(new JLabel("Author:"));
                                panell.add(authorField);

                                option = JOptionPane.showConfirmDialog(null, panell,
                                        "Continue?", JOptionPane.YES_NO_OPTION);
                                exit = addAuthors(authorField);
                            } while (!exit);
                            if (!arrayAuthors().isEmpty()) {
                                do {
                                    exit = false;
                                    JPanel panel2 = new JPanel(new GridLayout(1, 2));
                                    JTextField actorField = new JTextField(actor);
                                    panel2.add(new JLabel("Actor"));
                                    panel2.add(actorField);

                                    option = JOptionPane.showConfirmDialog(null, panel2,
                                            "Continue?", JOptionPane.YES_NO_OPTION);
                                    exit = addActors(actorField);
                                } while (!exit);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "The series was not added because there are no authors");
                                exit = verification();
                                ver = 0;
                            }
                            if (!arrayActors().isEmpty()) {
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
                            } else if (!arrayActors().isEmpty()) {
                                JOptionPane.showMessageDialog(null,
                                        "The series was not added because there are no actors");
                                exit = verification();
                                ver = 0;
                            }
                            if (!arrayChapters().isEmpty()) {
                                ac.addSerie(name, description, duration2, arrayAuthors(), arrayChapters(), gender,
                                        arrayActors());
                                JOptionPane.showMessageDialog(null, "Serie added sucessfully");
                                exit = true;
                            } else if (!arrayChapters().isEmpty()) {
                                JOptionPane.showMessageDialog(null,
                                        "The series was not added because there are no chapters.");
                                exit = verification();
                                ver = 0;
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "No input a number in duration");
                            exit = false;
                        }
                        if (duration2 != 0) {
                            option = JOptionPane.showConfirmDialog(null, "Do you want to add another serie?",
                                    "Continue?", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.OK_OPTION) {
                                ver = 1;
                                exit = true;
                                exit2 = false;
                            } else {
                                exit = true;
                                exit2 = true;
                            }
                        }
                    }
                } else {
                    exit = true;
                    exit2 = true;
                }
            } while (!exit);
        } while (!exit2);
    }

    public void showMovies() {
        String selectedaction = "";
        int option = 0;
        String showNamesMovies[];
        boolean exit = false;

        do {
            showNamesMovies = ac.namesMovies().toArray(new String[tamañoArray(1)]);
            selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Movie", JOptionPane.QUESTION_MESSAGE, null, showNamesMovies, showNamesMovies[0]);

            if (selectedaction != null) {
                JOptionPane.showMessageDialog(null, ac.showListMovies().get(ac.searchMovie(
                        selectedaction)).toString(), "Movie", JOptionPane.QUESTION_MESSAGE);
                option = JOptionPane.showConfirmDialog(null,
                        "Do you want to view another Movie?",
                        "Continue?", JOptionPane.YES_NO_OPTION);

                if (option != JOptionPane.OK_OPTION) {
                    exit = true;
                }
            } else {
                exit = true;
            }
        } while (exit == false);
    }

    public void showSeries() {
        String selectedaction = "";
        int option = 0;
        String showNamesSeries[];
        boolean exit = false;

        do {
            showNamesSeries = ac.namesSeries().toArray(new String[tamañoArray(2)]);
            selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Series", JOptionPane.QUESTION_MESSAGE, null, showNamesSeries, showNamesSeries[0]);

            if (selectedaction != null) {
                JOptionPane.showMessageDialog(null, ac.showListSeries().get(ac.searchSeries(
                        selectedaction)).toString(), "Serie", JOptionPane.QUESTION_MESSAGE);
                option = JOptionPane.showConfirmDialog(null,
                        "Do you want to view another Serie?",
                        "Continue?", JOptionPane.YES_NO_OPTION);

                if (option != JOptionPane.OK_OPTION) {
                    exit = true;
                }
            } else {
                exit = true;
            }
        } while (exit == false);
    }

    public void updateMovie() {
        String name = "";
        String description = "";
        String duration = "";
        int duration2 = 0;
        String actor = "";
        String gender = "";
        String selectedaction = "";
        int ver = 0;
        boolean exit = false;
        boolean exit2 = false;
        int option = 0;
        int position = 0;
        String showNamesMovies[];
        ac.showlistAuthors().clear();
        ac.showlistActors().clear();

        do {
            showNamesMovies = ac.namesMovies().toArray(new String[tamañoArray(1)]);
            selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Movies", JOptionPane.QUESTION_MESSAGE, null, showNamesMovies, showNamesMovies[0]);
            position = ac.searchMovie(selectedaction);
            if (selectedaction != null) {
                name = ac.showListMovies().get(position).getName();
                description = ac.showListMovies().get(position).getDescription();
                duration = String.valueOf(ac.showListMovies().get(position).getDuration());
                gender = ac.showListMovies().get(position).getGender();
                if (ver == 1) {
                    name = "";
                    description = "";
                    duration = "";
                    gender = "";
                    ac.showlistAuthors().clear();
                }
                do {
                    JPanel panel = new JPanel(new GridLayout(4, 2));
                    JTextField nameField = new JTextField(name, 15);
                    JTextField descriptionField = new JTextField(description, 15);
                    JTextField durationField = new JTextField(duration,15);
                    JTextField genderField = new JTextField(gender, 15);

                    panel.add(new JLabel("Name of the serie"));
                    panel.add(nameField);
                    panel.add(new JLabel("Description"));
                    panel.add(descriptionField);
                    panel.add(new JLabel("Duration"));
                    panel.add(durationField);
                    panel.add(new JLabel("Gender"));
                    panel.add(genderField);

                    int result = JOptionPane.showConfirmDialog(null, panel, "New dates Movie",
                            JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        name = nameField.getText();
                        description = descriptionField.getText();
                        duration = durationField.getText();
                        gender = genderField.getText();

                        if (name.isEmpty() || description.isEmpty() || duration.isEmpty() || gender.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Failed to update serie");
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
                                    exit = addAuthors(authorField);
                                    if (option != JOptionPane.OK_OPTION) {
                                        exit2 = true;
                                    }
                                } while (!exit);
                                if (exit2 != true) {
                                    do {
                                        exit = false;
                                        JPanel panell = new JPanel(new GridLayout(1, 2));
                                        JTextField actorField = new JTextField(actor);
                                        panell.add(new JLabel("Actor:"));
                                        panell.add(actorField);

                                        option = JOptionPane.showConfirmDialog(null, panell,
                                                "Continue?", JOptionPane.YES_NO_OPTION);
                                        exit = addActors(actorField);
                                        if (option == JOptionPane.OK_OPTION) {
                                            ac.updateMovie(selectedaction, name, description, duration2, arrayAuthors(),
                                                    arrayActors(), gender);
                                            JOptionPane.showMessageDialog(null, "Movie update sucessfully");
                                        }
                                    } while (!exit);
                                }
                                exit = true;
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "No input a number");
                                exit = false;
                            }
                            if (duration2 != 0) {
                                option = JOptionPane.showConfirmDialog(null, "Do you want to update another Movie?",
                                        "Continue?", JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.OK_OPTION) {
                                    ver = 1;
                                    exit = true;
                                    exit2 = false;
                                } else {
                                    exit = true;

                                    exit2 = true;
                                }
                            }
                        }
                    } else {
                        exit = true;
                    }
                } while (!exit);
            } else {
                exit = true;
                exit2 = true;
            }
        } while (!exit2);
    }

    public void updateSerie() {

        String name = "";
        String description = "";
        String duration = "";
        int duration2 = 0;
        int position = 0;
        String actor = "";
        String chapter = "";
        String gender = "";
        String selectedaction = "";
        int ver = 0;
        boolean exit = false;
        boolean exit2 = false;
        int option = 0;
        String showNamesSeries[];
        ac.showlistAuthors().clear();
        ac.showListChaptersTwo().clear();
        ac.showlistAuthors().clear();

        do {
            showNamesSeries = ac.namesSeries().toArray(new String[tamañoArray(2)]);
            selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Series", JOptionPane.QUESTION_MESSAGE, null, showNamesSeries, showNamesSeries[0]);
            position = ac.searchSeries(selectedaction);
            if (selectedaction != null) {
                name = ac.showListSeries().get(position).getName();
                description = ac.showListSeries().get(position).getDescription();
                duration = String.valueOf(ac.showListSeries().get(position).getDuration());
                gender = ac.showListSeries().get(position).getGender();
                if (ver == 1) {
                    name = "";
                    description = "";
                    duration = "";
                    gender = "";
                    ac.showlistAuthors().clear();
                    ac.showListChaptersTwo().clear();
                    ac.showlistActors().clear();
                }
                do {
                    JPanel panel = new JPanel(new GridLayout(4, 2));
                    JTextField nameField = new JTextField(name, 15);
                    JTextField descriptionField = new JTextField(description, 15);
                    JTextField durationField = new JTextField(duration, 15);
                    JTextField genderField = new JTextField(gender, 15);

                    panel.add(new JLabel("Name of the serie"));
                    panel.add(nameField);
                    panel.add(new JLabel("Description"));
                    panel.add(descriptionField);
                    panel.add(new JLabel("Duration"));
                    panel.add(durationField);
                    panel.add(new JLabel("Gender"));
                    panel.add(genderField);

                    int result = JOptionPane.showConfirmDialog(null, panel, "New dates Serie",
                            JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        name = nameField.getText();
                        description = descriptionField.getText();
                        duration = durationField.getText();
                        gender = genderField.getText();

                        if (name.isEmpty() || description.isEmpty() || duration.isEmpty() || gender.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Failed to update serie");
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
                                    if (option != JOptionPane.OK_OPTION) {
                                        exit2 = true;
                                    }
                                } while (!exit);
                                if (exit2 != true) {
                                    exit2 = false;
                                    do {
                                        JPanel panell = new JPanel(new GridLayout(1, 2));
                                        JTextField actorField = new JTextField(actor);
                                        panell.add(new JLabel("Actor"));
                                        panell.add(actorField);

                                        option = JOptionPane.showConfirmDialog(null, panell,
                                                "Continue?", JOptionPane.YES_NO_OPTION);
                                        exit = addActors(actorField);
                                        if (option != JOptionPane.OK_OPTION) {
                                            exit2 = true;
                                        }
                                    } while (!exit);
                                }
                                if (exit2 != true) {
                                    do {
                                        exit = false;
                                        JPanel panel2 = new JPanel(new GridLayout(1, 2));
                                        JTextField chapterField = new JTextField(chapter);
                                        panel2.add(new JLabel("Chapter:"));
                                        panel2.add(chapterField);

                                        option = JOptionPane.showConfirmDialog(null, panel2,
                                                "Continue?", JOptionPane.YES_NO_OPTION);
                                        exit = addChapter(chapterField);
                                        if (option == JOptionPane.OK_OPTION) {
                                            ac.updateSeries(selectedaction, name, description, duration2,
                                                    arrayAuthors(),
                                                    arrayChapters(), gender, arrayActors());
                                            JOptionPane.showMessageDialog(null, "Serie update sucessfully");
                                        }
                                    } while (!exit);
                                }
                                exit = true;
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "No input a number");
                                exit = false;
                            }
                            if (duration2 != 0) {
                                option = JOptionPane.showConfirmDialog(null, "Do you want to update another serie?",
                                        "Continue?", JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.OK_OPTION) {
                                    ver = 1;
                                    exit = true;
                                    exit2 = false;
                                } else {
                                    exit = true;
                                    exit2 = true;
                                }
                            }
                        }
                    } else {
                        exit = true;
                    }
                } while (!exit);
            } else {
                exit = true;
                exit2 = true;
            }
        } while (!exit2);
    }

    public boolean verification() {
        int option = 0;
        option = JOptionPane.showConfirmDialog(null, "Do you want try agan?",
                "Continue?", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addAuthors(JTextField authorField) {
        int option = 0;
        String author = "";

        if (option == JOptionPane.OK_OPTION) {
            author = authorField.getText();
            if (author.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Failed to add author");
                return verification();
            } else {
                ac.addlistAuthors(author);
                option = JOptionPane.showConfirmDialog(null, "Do you want to add another author?",
                        "Continue?", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    author = "";
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }

    public boolean addActors(JTextField actorField) {
        int option = 0;
        String actor = "";

        if (option == JOptionPane.OK_OPTION) {
            actor = actorField.getText();
            if (actor.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Failed to add actor");
                return verification();
            } else {
                ac.addlistActors(actor);
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

    public boolean addChapter(JTextField chapterField) {
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

    public int tamañoArray(int num) {
        if (num == 1) {
            return ac.namesMovies().size();
        }
        return ac.namesSeries().size();
    }

    public ArrayList<String> arrayAuthors() {
        ArrayList<String> listAuthors = new ArrayList<>();
        for (int i = 0; i < ac.showlistAuthors().size(); i++) {
            listAuthors.add(ac.showlistAuthors().get(i));
        }

        return listAuthors;
    }

    public ArrayList<String> arrayActors() {
        ArrayList<String> listActors = new ArrayList<>();
        for (int i = 0; i < ac.showlistActors().size(); i++) {
            listActors.add(ac.showlistActors().get(i));
        }
        return listActors;
    }

    public ArrayList<String> arrayChapters() {
        ArrayList<String> listChapters = new ArrayList<>();
        for (int i = 0; i < ac.showListChaptersTwo().size(); i++) {
            listChapters.add(ac.showListChaptersTwo().get(i));
        }
        return listChapters;
    }

    public void deleteMovie() {
        boolean exit2 = false;
        int option = 0;
        String selectedaction = "";
        String showNamesMovies[];
        do {
            if (!ac.namesMovies().isEmpty()) {
                showNamesMovies = ac.namesMovies().toArray(new String[tamañoArray(1)]);
                selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                        "Movie", JOptionPane.QUESTION_MESSAGE, null, showNamesMovies, showNamesMovies[0]);
                if (selectedaction != null) {
                    if (ac.deleteMovie(selectedaction) == true) {
                        JOptionPane.showMessageDialog(null, "Movie delete succesfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Movie wasn't delete");
                    }
                    option = JOptionPane.showConfirmDialog(null, "Do you want to delete another Movie?",
                            "Continue?", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        exit2 = false;
                    } else {
                        exit2 = true;
                    }
                } else {
                    exit2 = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "No movies to eliminate");
                exit2 = true;
            }
        } while (exit2 == false);
    }

    public void deleteSerie() {
        boolean exit2 = false;
        int option = 0;
        String selectedaction = "";
        String showNamesSeries[];
        do {
            if (!ac.namesSeries().isEmpty()) {
                showNamesSeries = ac.namesSeries().toArray(new String[tamañoArray(2)]);
                selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                        "Serie", JOptionPane.QUESTION_MESSAGE, null, showNamesSeries, showNamesSeries[0]);
                if (selectedaction != null) {
                    if (ac.deleteSerie(selectedaction) == true) {
                        JOptionPane.showMessageDialog(null, "Serie delete succesfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Serie wasn't delete");
                    }
                    option = JOptionPane.showConfirmDialog(null, "Do you want to delete another Serie?",
                            "Continue?", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        exit2 = false;
                    } else {
                        exit2 = true;
                    }
                } else {
                    exit2 = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "No series to eliminate");
                exit2 = true;
            }
        } while (exit2 == false);
    }



    // metodo de archivo

    public void archiveUsers(){
        
    }
}