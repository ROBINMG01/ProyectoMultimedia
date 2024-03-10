package co.edu.uptc.viewFx;

import java.awt.GridLayout;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.ControlerInitialMenuView;
import co.edu.uptc.view.ViewVisit;

public class AdminView {
    private AdminController ac;
    private Date dateApp;
    ViewVisit viewVisit = new ViewVisit();
    int newWidth = 0;
    int newHeight = 0;

    public AdminView(AdminController ac, ControlerInitialMenuView controlerInitialMenuView) {
        this.ac = ac;
        this.dateApp = new Date();
    }

    // retorna la fecha
    public Date dateApp() {
        return dateApp;
    }

    public void menuAdmin() {
        boolean condition = false;

        ImageIcon iconSelection = new ImageIcon(
                "src\\main\\java\\co\\edu\\uptc\\image\\Selección pelicula o serie.jpeg");

        // Obtener la imagen del ImageIcon original
        Image selection = iconSelection.getImage();

        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
        newWidth = 100;
        newHeight = 100;

        // Redimensionar la imagen
        Image iSelection = selection.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
        ImageIcon a = new ImageIcon(iSelection);

        do {
            String[] options = { "Movie", "Serie", "userRegisters", "Exit" };
            condition = false;
            UIManager.put("OptionPane.cancelButtonText", "Cancel");
            UIManager.put("OptionPane.okButtonText", "Ok");
            String selectedaction = (String) JOptionPane.showInputDialog(null,
                    "Seleccione una opción:", "Opciones de Administrador",
                    JOptionPane.QUESTION_MESSAGE, a, options, options[0]);

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
        boolean condition = false;
        ImageIcon iconMovie = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Movie.jpeg");
        ImageIcon iconSerie = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Serie.jpeg");
        // Obtener la imagen del ImageIcon original
        Image movie = iconMovie.getImage();
        Image serie = iconSerie.getImage();
        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
        newWidth = 100;
        newHeight = 100;
        // Redimensionar la imagen
        Image iMovie = movie.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        Image iSerie = serie.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
        ImageIcon a = new ImageIcon(iMovie);
        ImageIcon b = new ImageIcon(iSerie);
        switch (selectedaction) {
            case "Movie":
                do {
                    String[] options = { "Add Movie", "View Movies", "Update Movie", "deleteMovie", "Exit" };
                    String options2 = (String) JOptionPane.showInputDialog(null,
                            "Seleccione una opción:", "Opciones de Administrador",
                            JOptionPane.QUESTION_MESSAGE, a, options, options[0]);
                    if (options2 == null) {
                        condition = true;
                        break;
                    } else if (options2.equals("Exit")) {
                        condition = true;
                    } else {
                        condition = false;
                        menuMovie(options2);
                    }
                } while (condition == false);
                break;
            case "Serie":
                condition = false;
                do {
                    String[] options3 = { "Add Serie", "Add Season", "Add Chapter", "View Series", "Update Series",
                            "deleteSeries", "Exit" };
                    String options4 = (String) JOptionPane.showInputDialog(null,
                            "Seleccione una opción:", "Opciones de Administrador",
                            JOptionPane.QUESTION_MESSAGE, b, options3, options3[0]);
                    if (options4 == null) {
                        condition = true;
                        break;
                    } else if (options4.equals("Exit")) {
                        condition = true;
                    } else {
                        condition = false;
                        menuSerie(options4);
                    }
                } while (condition == false);
                break;
            case "userRegisters":
                break;
            case "Set the date of the day":
                // Pedir la fecha al usuario utilizando JOptionPane
                String dateString = JOptionPane.showInputDialog(null, "Introduce una fecha (dd/mm/yy):");
                // Crear un objeto SimpleDateFormat para parsear la fecha
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                try {
                    // Parsear la cadena de fecha a un objeto Date
                    this.dateApp = dateFormat.parse(dateString);

                    // Mostrar la fecha en la consola
                    System.out.println("Fecha introducida: " + this.dateApp);
                    System.out.println("Fecha introducida: " + this.dateApp);
                } catch (ParseException e) {
                    // Manejar errores de formato de fecha
                    System.err.println("Formato de fecha incorrecto: " + e.getMessage());
                }
                break;
            case "Exit":
                break;
        }
    }

    public void menuMovie(String selectedaction) {
        switch (selectedaction) {
            case "Add Movie":
                addMovie();
                break;
            case "View Movies":
                showMovies();
                break;
            case "Update Movie":
                updateMovie();
                break;
            case "deleteMovie":
                deleteMovie();
                break;
            case "Exit":
                break;
        }
    }

    public void menuSerie(String selectedaction) {
        switch (selectedaction) {
            case "Add Serie":
                addSerie();
                break;
            case "Add Season":
                addSeason();
                break;
            case "Add Chapter":
                addChapter();
                break;
            case "View Series":
                showSeries();
                break;
            case "Update Series":
                updateSerie();
                break;
            case "deleteSeries":
                deleteSerie();
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
        String imageUrl = "";
        int ver = 0;
        boolean exit = false;
        boolean exit2 = false;
        int option = 0;
        ac.showlistAuthors().clear();
        ac.showlistActors().clear();

        ImageIcon iconRegisterMovie = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\RegisterMovie.jpeg");
        ImageIcon iconAuthor = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Author.jpeg");
        ImageIcon iconActor = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Actor.jpeg");

        // Obtener la imagen del ImageIcon original
        Image rMovie = iconRegisterMovie.getImage();
        Image rAuthor = iconAuthor.getImage();
        Image rActor = iconActor.getImage();

        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
        newWidth = 100;
        newHeight = 100;

        // Redimensionar la imagen
        Image iMovie = rMovie.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
        ImageIcon a = new ImageIcon(iMovie);

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

                panel.add(new JLabel("Name:"));
                panel.add(nameField);
                panel.add(new JLabel("Description:"));
                panel.add(descriptionField);
                panel.add(new JLabel("Duration:"));
                panel.add(durationField);

                int results = JOptionPane.showConfirmDialog(null, panel, "Add Movie", 0, 0, a);

                if (results == JOptionPane.OK_OPTION) {
                    name = nameField.getText();
                    description = descriptionField.getText();
                    duration = durationField.getText();

                    gender = viewVisit.viewGender(gender);

                    if (name.isEmpty() || description.isEmpty() || duration.isEmpty()
                            || gender.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Failed to add movie");
                        exit = verification();
                        if (exit == true) {
                            exit2 = true;
                        }
                        ver = 0;
                    } else {
                        try {
                            dutation2 = Integer.parseInt(duration);

                            newHeight = 40;
                            Image iAuthor = rAuthor.getScaledInstance(newWidth, newHeight,
                                    Image.SCALE_SMOOTH);
                            ImageIcon b = new ImageIcon(iAuthor);
                            do {
                                JPanel panell = new JPanel(new GridLayout(1, 2));
                                JTextField authorField = new JTextField(author);
                                panell.add(new JLabel("Author:"));
                                panell.add(authorField);

                                option = JOptionPane.showConfirmDialog(null, panell, "Continue?", 0,
                                        0, b);

                                exit = addAuthors(authorField);
                            } while (!exit);
                            if (!arrayAuthors().isEmpty()) {
                                Image iActor = rActor.getScaledInstance(newWidth, newHeight,
                                        Image.SCALE_SMOOTH);
                                ImageIcon c = new ImageIcon(iActor);
                                do {
                                    JPanel panell = new JPanel(new GridLayout(1, 2));
                                    JTextField actorField = new JTextField(actor);
                                    panell.add(new JLabel("Actor:"));
                                    panell.add(actorField);

                                    option = JOptionPane.showConfirmDialog(null, panell,
                                            "Continue?", 0, 0, c);
                                    exit = addActors(actorField);
                                } while (!exit);
                            }

                            if (!arrayActors().isEmpty()) {
                                ac.addMovie(name, description, dutation2, arrayAuthors(), gender,
                                        arrayActors(), "Movie", imageUrl);
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
                            option = JOptionPane.showConfirmDialog(null,
                                    "Do you want to add another movie?", "Continue?",
                                    JOptionPane.YES_NO_OPTION);
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
        String nameSeason = "";
        String description = "";
        String descriptionSeason = "";
        String nameChapter = "";
        String durationChapter = "";
        String duration = "";
        int duration2 = 0;
        int duration3 = 0;
        String author = "";
        String actor = "";
        String gender = "";
        String imageUrl = "";

        int ver = 0;
        boolean exit = false;
        boolean exit2 = false;
        int option = 0;
        ac.showlistAuthors().clear();
        ac.showListChaptersTwo().clear();
        ac.showlistActors().clear();

        ImageIcon iconRegisterSerie = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\RegisterMovie.jpeg");
        ImageIcon iconAuthor = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Author.jpeg");
        ImageIcon iconActor = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Actor.jpeg");

        // Obtener la imagen del ImageIcon original
        Image rMovie = iconRegisterSerie.getImage();
        Image rAuthor = iconAuthor.getImage();
        Image rActor = iconActor.getImage();

        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
        newWidth = 100;
        newHeight = 100;

        // Redimensionar la imagen
        Image iMovie = rMovie.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
        ImageIcon a = new ImageIcon(iMovie);

        do {
            if (ver == 1) {
                name = "";
                description = "";
                duration = "";
                gender = "";
                nameSeason = "";
                descriptionSeason = "";
                nameChapter = "";
                durationChapter = "";
                ac.showlistAuthors().clear();
                ac.showListChaptersTwo().clear();
                ac.showlistActors().clear();
            }
            do {
                JPanel panel = new JPanel(new GridLayout(7, 2));
                JTextField nameField = new JTextField(name);
                JTextField descriptionField = new JTextField(description);
                JTextField durationField = new JTextField(duration);
                JTextField nameSField = new JTextField(nameSeason);
                JTextField descriptionSField = new JTextField(descriptionSeason);
                JTextField nameCField = new JTextField(nameChapter);
                JTextField durationCField = new JTextField(durationChapter);

                panel.add(new JLabel("Name"));
                panel.add(nameField);
                panel.add(new JLabel("Description"));
                panel.add(descriptionField);
                panel.add(new JLabel("Duration"));
                panel.add(durationField);
                panel.add(new JLabel("Name Season"));
                panel.add(nameSField);
                panel.add(new JLabel("Description Season"));
                panel.add(descriptionSField);
                panel.add(new JLabel("Name Chapter"));
                panel.add(nameCField);
                panel.add(new JLabel("Duration Chapter"));
                panel.add(durationCField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Add Serie", 0, 0, a);

                if (result == JOptionPane.OK_OPTION) {
                    name = nameField.getText();
                    description = descriptionField.getText();
                    duration = durationField.getText();
                    nameSeason = nameSField.getText();
                    descriptionSeason = descriptionSField.getText();
                    nameChapter = nameCField.getText();
                    gender = viewVisit.viewGender(gender);
                    durationChapter = durationCField.getText();

                    if (name.isEmpty() || description.isEmpty() || duration.isEmpty()
                            || gender.isEmpty() || nameChapter.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Failed to add serie");
                        exit = verification();
                        ver = 0;
                    } else {
                        try {
                            duration2 = Integer.parseInt(duration);
                            duration3 = Integer.parseInt(durationChapter);

                            newHeight = 40;
                            Image iAuthor = rAuthor.getScaledInstance(newWidth, newHeight,
                                    Image.SCALE_SMOOTH);
                            ImageIcon b = new ImageIcon(iAuthor);
                            do {
                                JPanel panell = new JPanel(new GridLayout(2, 2));
                                JTextField authorField = new JTextField(author);
                                panell.add(new JLabel("Author:"));
                                panell.add(authorField);

                                option = JOptionPane.showConfirmDialog(null, panell, "Continue?", 0,
                                        0, b);
                                exit = addAuthors(authorField);
                            } while (!exit);
                            if (!arrayAuthors().isEmpty()) {
                                Image iActor = rActor.getScaledInstance(newWidth, newHeight,
                                        Image.SCALE_SMOOTH);
                                ImageIcon c = new ImageIcon(iActor);
                                do {
                                    exit = false;
                                    JPanel panel2 = new JPanel(new GridLayout(1, 2));
                                    JTextField actorField = new JTextField(actor);
                                    panel2.add(new JLabel("Actor"));
                                    panel2.add(actorField);

                                    option = JOptionPane.showConfirmDialog(null, panel2,
                                            "Continue?", 0, 0, c);
                                    exit = addActors(actorField);
                                } while (!exit);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "The series was not added because there are no authors");
                                exit = verification();
                                ver = 0;
                            }
                            if (!arrayActors().isEmpty()) {
                                if (ac.addSerie(name, description, duration2, arrayAuthors(), gender, arrayActors(),
                                        nameSeason, descriptionSeason, nameChapter, duration3, "Series", imageUrl)) {
                                    JOptionPane.showMessageDialog(null, "Serie added sucessfully");
                                    exit = true;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Serie was not added");
                                    exit = false;
                                }
                            } else if (!arrayChapters().isEmpty()) {
                                JOptionPane.showMessageDialog(null,
                                        "The series was not added because there are no actors");
                                exit = verification();
                                ver = 0;
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null,
                                    "No input a number in duration season or duration chapter");
                            exit = false;
                        }
                        if (duration2 != 0 && exit == true) {
                            option = JOptionPane.showConfirmDialog(null,
                                    "Do you want to add another serie?", "Continue?",
                                    JOptionPane.YES_NO_OPTION);
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

    public void addSeason() {
        String nameSeason = "";
        String descriptionSeason = "";
        String nameChapter = "";
        String durationChapter = "";
        int duration2 = 0;
        int ver = 0;
        boolean exit = false;
        boolean exit2 = false;
        int option = 0;
        String selectedaction = "";
        String showNamesSeries[];
        int position = 0;
        int position2 = 0;

        ImageIcon iconRegisterSerie = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\RegisterMovie.jpeg");
        ImageIcon iconUpdate = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Update.jpeg");

        // Obtener la imagen del ImageIcon original
        Image rMovie = iconRegisterSerie.getImage();
        Image rUpdate = iconUpdate.getImage();

        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
        newWidth = 100;
        newHeight = 100;

        // Redimensionar la imagen
        Image iUpdate = rUpdate.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        Image iMovie = rMovie.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
        ImageIcon a = new ImageIcon(iMovie);
        ImageIcon u = new ImageIcon(iUpdate);

        do {
            showNamesSeries = ac.namesSeries().toArray(new String[tamañoArray(2000)]);
            selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Series", JOptionPane.QUESTION_MESSAGE, u, showNamesSeries, showNamesSeries[0]);
            position = ac.searchSeries(selectedaction);

            if (ver == 1) {
                nameSeason = "";
                descriptionSeason = "";
                nameChapter = "";
                durationChapter = "";
            }
            do {
                JPanel panel = new JPanel(new GridLayout(4, 2));
                JTextField nameSField = new JTextField(nameSeason);
                JTextField descriptionSField = new JTextField(descriptionSeason);
                JTextField nameCField = new JTextField(nameChapter);
                JTextField durationCField = new JTextField(durationChapter);

                panel.add(new JLabel("Name Season"));
                panel.add(nameSField);
                panel.add(new JLabel("Description Season"));
                panel.add(descriptionSField);
                panel.add(new JLabel("Name Chapter"));
                panel.add(nameCField);
                panel.add(new JLabel("Duration Chapter"));
                panel.add(durationCField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Add Season", 0, 0, a);

                if (result == JOptionPane.OK_OPTION) {
                    nameSeason = nameSField.getText();
                    descriptionSeason = descriptionSField.getText();
                    nameChapter = nameCField.getText();
                    durationChapter = durationCField.getText();

                    if (nameChapter.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Failed to add Season");
                        exit = verification();
                        ver = 0;
                    } else {
                        try {
                            duration2 = Integer.parseInt(durationChapter);
                            if (ac.addSeason(position, position2, nameSeason, descriptionSeason, nameChapter,
                                    duration2)) {
                                JOptionPane.showMessageDialog(null, "Season added sucessfully");
                                exit = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Season wasn't added");
                                exit = false;
                            }

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null,
                                    "No input a number in duration season or duration chapter");
                            exit = false;
                        }
                        if (duration2 != 0 && exit == true) {
                            option = JOptionPane.showConfirmDialog(null,
                                    "Do you want to add another season?", "Continue?",
                                    JOptionPane.YES_NO_OPTION);
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

    public void addChapter() {
        String nameChapter = "";
        String durationChapter = "";
        int duration2 = 0;
        int ver = 0;
        boolean exit = false;
        boolean exit2 = false;
        int option = 0;
        String selectedaction = "";
        String showNamesSeries[];
        String selectedaction2 = "";
        String showNamesSeasons2[];
        int position = 0;
        int position2 = 0;

        ImageIcon iconRegisterSerie = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\RegisterMovie.jpeg");
        ImageIcon iconUpdate = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Update.jpeg");

        // Obtener la imagen del ImageIcon original
        Image rMovie = iconRegisterSerie.getImage();
        Image rUpdate = iconUpdate.getImage();

        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
        newWidth = 100;
        newHeight = 100;

        // Redimensionar la imagen
        Image iUpdate = rUpdate.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        Image iMovie = rMovie.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
        ImageIcon a = new ImageIcon(iMovie);
        ImageIcon u = new ImageIcon(iUpdate);

        do {
            showNamesSeries = ac.namesSeries().toArray(new String[tamañoArray(2000)]);
            selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Series", JOptionPane.QUESTION_MESSAGE, u, showNamesSeries, showNamesSeries[0]);
            position = ac.searchSeries(selectedaction);

            showNamesSeasons2 = ac.namesSesons(position).toArray(new String[tamañoArray(position)]);
            selectedaction2 = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Season", JOptionPane.QUESTION_MESSAGE, u, showNamesSeasons2, showNamesSeasons2[0]);
            position2 = ac.searchSeason(selectedaction, selectedaction2);

            if (ver == 1) {
                nameChapter = "";
                durationChapter = "";
            }
            do {
                JPanel panel = new JPanel(new GridLayout(4, 2));
                JTextField nameCField = new JTextField(nameChapter);
                JTextField durationCField = new JTextField(durationChapter);

                panel.add(new JLabel("Name Chapter"));
                panel.add(nameCField);
                panel.add(new JLabel("Duration Chapter"));
                panel.add(durationCField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Add Chapter", 0, 0, a);

                if (result == JOptionPane.OK_OPTION) {
                    nameChapter = nameCField.getText();
                    durationChapter = durationCField.getText();

                    if (nameChapter.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Failed to add chater\n name chapter is empty");
                        exit = verification();
                        ver = 0;
                    } else {
                        try {
                            duration2 = Integer.parseInt(durationChapter);
                            if (ac.addChapter(position, position2, nameChapter, duration2)) {
                                JOptionPane.showMessageDialog(null, "Chapter added sucessfully");
                                exit = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Chapter was not added");
                                exit = false;
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null,
                                    "No input a number in duration chapter");
                            exit = false;
                        }
                        if (duration2 != 0 && exit == true) {
                            option = JOptionPane.showConfirmDialog(null,
                                    "Do you want to add another chapter?", "Continue?",
                                    JOptionPane.YES_NO_OPTION);
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

        ImageIcon iconRegisterMovie = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\RegisterMovie.jpeg");
        ImageIcon iconView = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\register.png");

        // Obtener la imagen del ImageIcon original
        Image rMovie = iconRegisterMovie.getImage();
        Image rView = iconView.getImage();

        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
        newWidth = 100;
        newHeight = 100;

        // Redimensionar la imagen
        Image iMovie = rMovie.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        Image iView = rView.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
        ImageIcon a = new ImageIcon(iMovie);
        ImageIcon b = new ImageIcon(iView);

        do {
            showNamesMovies = ac.namesMovies().toArray(new String[tamañoArray(1000)]);
            selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Movie", JOptionPane.QUESTION_MESSAGE, a, showNamesMovies, showNamesMovies[0]);

            if (selectedaction != null) {
                JOptionPane.showMessageDialog(null,
                        ac.showListMovies().get(ac.searchMovie(selectedaction)).toString(), "Movie",
                        JOptionPane.QUESTION_MESSAGE, b);
                option = JOptionPane.showConfirmDialog(null, "Do you want to view another Movie?",
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

        ImageIcon iconRegisterMovie = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\RegisterMovie.jpeg");
        ImageIcon iconView = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\register.png");

        // Obtener la imagen del ImageIcon original
        Image rMovie = iconRegisterMovie.getImage();
        Image rView = iconView.getImage();

        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
        newWidth = 100;
        newHeight = 100;

        // Redimensionar la imagen
        Image iMovie = rMovie.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        Image iView = rView.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
        ImageIcon a = new ImageIcon(iMovie);
        ImageIcon b = new ImageIcon(iView);

        do {
            showNamesSeries = ac.namesSeries().toArray(new String[tamañoArray(2000)]);
            selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Series", JOptionPane.QUESTION_MESSAGE, a, showNamesSeries, showNamesSeries[0]);

            if (selectedaction != null) {
                JOptionPane.showMessageDialog(null,
                        ac.showListSeries().get(ac.searchSeries(selectedaction)).toString(),
                        "Serie", JOptionPane.QUESTION_MESSAGE, b);
                option = JOptionPane.showConfirmDialog(null, "Do you want to view another Serie?",
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

        ImageIcon iconUpdate = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Update.jpeg");
        ImageIcon iconRegisterMovie = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\RegisterMovie.jpeg");
        ImageIcon iconAuthor = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Author.jpeg");
        ImageIcon iconActor = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Actor.jpeg");

        // Obtener la imagen del ImageIcon original
        Image rUpdate = iconUpdate.getImage();
        Image rMovie = iconRegisterMovie.getImage();
        Image rAuthor = iconAuthor.getImage();
        Image rActor = iconActor.getImage();

        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
        newWidth = 100;
        newHeight = 100;

        // Redimensionar la imagen
        Image iUpdate = rUpdate.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        Image iMovie = rMovie.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
        ImageIcon u = new ImageIcon(iUpdate);
        ImageIcon a = new ImageIcon(iMovie);

        do {
            showNamesMovies = ac.namesMovies().toArray(new String[tamañoArray(1000)]);
            selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Movies", JOptionPane.QUESTION_MESSAGE, u, showNamesMovies, showNamesMovies[0]);
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

                    int result = JOptionPane.showConfirmDialog(null, panel, "New dates Movie", 0, 0, a);

                    if (result == JOptionPane.OK_OPTION) {
                        name = nameField.getText();
                        description = descriptionField.getText();
                        duration = durationField.getText();
                        gender = genderField.getText();

                        if (name.isEmpty() || description.isEmpty() || duration.isEmpty()
                                || gender.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Failed to update serie");
                            exit = verification();
                            ver = 0;
                        } else {
                            try {
                                duration2 = Integer.parseInt(duration);
                                newHeight = 40;
                                Image iAuthor = rAuthor.getScaledInstance(newWidth, newHeight,
                                        Image.SCALE_SMOOTH);
                                ImageIcon b = new ImageIcon(iAuthor);
                                do {
                                    JPanel panell = new JPanel(new GridLayout(1, 2));
                                    JTextField authorField = new JTextField(actor);
                                    panell.add(new JLabel("Author:"));
                                    panell.add(authorField);

                                    option = JOptionPane.showConfirmDialog(null, panell,
                                            "Continue?", 0, 0, b);
                                    exit = addAuthors(authorField);
                                    if (option != JOptionPane.OK_OPTION) {
                                        exit2 = true;
                                    }
                                } while (!exit);
                                if (exit2 != true) {
                                    newHeight = 100;
                                    Image iActor = rActor.getScaledInstance(newWidth, newHeight,
                                            Image.SCALE_SMOOTH);
                                    ImageIcon c = new ImageIcon(iActor);
                                    do {
                                        exit = false;
                                        JPanel panell = new JPanel(new GridLayout(1, 2));
                                        JTextField actorField = new JTextField(actor);
                                        panell.add(new JLabel("Actor:"));
                                        panell.add(actorField);

                                        option = JOptionPane.showConfirmDialog(null, panell,
                                                "Continue?", 0, 0, c);
                                        exit = addActors(actorField);
                                        if (option == JOptionPane.OK_OPTION) {
                                            ac.updateMovie(selectedaction, name, description,
                                                    duration2, arrayAuthors(), arrayActors(),
                                                    gender);
                                            JOptionPane.showMessageDialog(null,
                                                    "Movie update sucessfully");
                                        }
                                    } while (!exit);
                                }
                                exit = true;
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "No input a number");
                                exit = false;
                            }
                            if (duration2 != 0) {
                                option = JOptionPane.showConfirmDialog(null,
                                        "Do you want to update another Movie?", "Continue?",
                                        JOptionPane.YES_NO_OPTION);
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

        ImageIcon iconUpdate = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Update.jpeg");
        ImageIcon iconRegisterMovie = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\RegisterMovie.jpeg");
        ImageIcon iconAuthor = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Author.jpeg");
        ImageIcon iconActor = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Actor.jpeg");
        ImageIcon iconChapter = new ImageIcon("src\\main\\java\\co\\edu\\uptc\\image\\Chapters.jpeg");

        // Obtener la imagen del ImageIcon original
        Image rUpdate = iconUpdate.getImage();
        Image rMovie = iconRegisterMovie.getImage();
        Image rAuthor = iconAuthor.getImage();
        Image rActor = iconActor.getImage();
        Image rChapter = iconChapter.getImage();

        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
        newWidth = 100;
        newHeight = 100;

        // Redimensionar la imagen
        Image iUpdate = rUpdate.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        Image iMovie = rMovie.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
        ImageIcon u = new ImageIcon(iUpdate);
        ImageIcon a = new ImageIcon(iMovie);

        do {
            showNamesSeries = ac.namesSeries().toArray(new String[tamañoArray(2000)]);
            selectedaction = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:",
                    "Series", JOptionPane.QUESTION_MESSAGE, u, showNamesSeries, showNamesSeries[0]);
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

                    int result = JOptionPane.showConfirmDialog(null, panel, "New dates Serie", 0, 0, a);

                    if (result == JOptionPane.OK_OPTION) {
                        name = nameField.getText();
                        description = descriptionField.getText();
                        duration = durationField.getText();
                        gender = genderField.getText();

                        if (name.isEmpty() || description.isEmpty() || duration.isEmpty()
                                || gender.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Failed to update serie");
                            exit = verification();
                            ver = 0;
                        } else {
                            try {
                                duration2 = Integer.parseInt(duration);
                                newHeight = 40;
                                Image iAuthor = rAuthor.getScaledInstance(newWidth, newHeight,
                                        Image.SCALE_SMOOTH);
                                ImageIcon b = new ImageIcon(iAuthor);

                                do {
                                    JPanel panell = new JPanel(new GridLayout(1, 2));
                                    JTextField authorField = new JTextField(actor);
                                    panell.add(new JLabel("Author:"));
                                    panell.add(authorField);

                                    option = JOptionPane.showConfirmDialog(null, panell,
                                            "Continue?", 0, 0, b);
                                    exit = addActors(authorField);
                                    if (option != JOptionPane.OK_OPTION) {
                                        exit2 = true;
                                    }
                                } while (!exit);
                                if (exit2 != true) {
                                    exit2 = false;
                                    Image iActor = rActor.getScaledInstance(newWidth, newHeight,
                                            Image.SCALE_SMOOTH);
                                    ImageIcon c = new ImageIcon(iActor);
                                    do {
                                        JPanel panell = new JPanel(new GridLayout(1, 2));
                                        JTextField actorField = new JTextField(actor);
                                        panell.add(new JLabel("Actor"));
                                        panell.add(actorField);

                                        option = JOptionPane.showConfirmDialog(null, panell,
                                                "Continue?", 0, 0, c);
                                        exit = addActors(actorField);
                                        if (option != JOptionPane.OK_OPTION) {
                                            exit2 = true;
                                        }
                                    } while (!exit);
                                }
                                if (exit2 != true) {
                                    Image iChapter = rChapter.getScaledInstance(newWidth, newHeight,
                                            Image.SCALE_SMOOTH);
                                    ImageIcon d = new ImageIcon(iChapter);

                                    do {
                                        exit = false;
                                        JPanel panel2 = new JPanel(new GridLayout(1, 2));
                                        JTextField chapterField = new JTextField(chapter);
                                        panel2.add(new JLabel("Chapter:"));
                                        panel2.add(chapterField);

                                        option = JOptionPane.showConfirmDialog(null, panel2,
                                                "Continue?", 0, 0, d);
                                        exit = addChapter(chapterField);
                                        if (option == JOptionPane.OK_OPTION) {
                                            ac.updateSeries(selectedaction, name, description,
                                                    duration2, arrayAuthors(), arrayChapters(),
                                                    gender, arrayActors());
                                            JOptionPane.showMessageDialog(null,
                                                    "Serie update sucessfully");
                                        }
                                    } while (!exit);
                                }
                                exit = true;
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "No input a number");
                                exit = false;
                            }
                            if (duration2 != 0) {
                                option = JOptionPane.showConfirmDialog(null,
                                        "Do you want to update another serie?", "Continue?",
                                        JOptionPane.YES_NO_OPTION);
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
        option = JOptionPane.showConfirmDialog(null, "Do you want try agan?", "Continue?",
                JOptionPane.YES_NO_OPTION);
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
        if (num == 1000) {
            return ac.namesMovies().size();
        } else if (num == 2000) {
            return ac.namesSeries().size();
        }
        return ac.namesSesons(num).size();
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
                selectedaction = (String) JOptionPane.showInputDialog(null,
                        "Seleccione una opción:", "Movie", JOptionPane.QUESTION_MESSAGE, null,
                        showNamesMovies, showNamesMovies[0]);
                if (selectedaction != null) {
                    if (ac.deleteMovie(selectedaction) == true) {
                        JOptionPane.showMessageDialog(null, "Movie delete succesfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Movie wasn't delete");
                    }
                    option = JOptionPane.showConfirmDialog(null,
                            "Do you want to delete another Movie?", "Continue?",
                            JOptionPane.YES_NO_OPTION);
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
                selectedaction = (String) JOptionPane.showInputDialog(null,
                        "Seleccione una opción:", "Serie", JOptionPane.QUESTION_MESSAGE, null,
                        showNamesSeries, showNamesSeries[0]);
                if (selectedaction != null) {
                    if (ac.deleteSerie(selectedaction) == true) {
                        JOptionPane.showMessageDialog(null, "Serie delete succesfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Serie wasn't delete");
                    }
                    option = JOptionPane.showConfirmDialog(null,
                            "Do you want to delete another Serie?", "Continue?",
                            JOptionPane.YES_NO_OPTION);
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

    public void archiveUsers() {

    }
}
