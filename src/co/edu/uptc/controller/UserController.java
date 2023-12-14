

package co.edu.uptc.controller;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class UserController {
    private ArrayList<Movie> listMovies;
    private ArrayList<Serie> listSeries;
    private boolean exit;
    private ArrayList<Movie> favoriteMovies;
    private ArrayList<Serie> favoriteSeries;

    public UserController(AdminController ad) {
        listMovies = new ArrayList<>();
        listSeries = new ArrayList<>();
        exit = true;
        favoriteMovies = new ArrayList<>();
        favoriteSeries = new ArrayList<>();
    }

    public boolean addMovie(String name, String description, int duration,
            ArrayList<String> authors, ArrayList<String> actors, String gender) {
        Movie movie = new Movie(name, description, duration, authors, actors, gender);
        listMovies.add(movie);
        return true;
    }

    public boolean addSerie(String name, String description, int duration, ArrayList<String> authors,
            ArrayList<String> chapters, ArrayList<String> actors, String gender) {
        Serie serie = new Serie(name, description, duration, authors, chapters, gender, actors);
        listSeries.add(serie);
        return true;
    }

    public ArrayList<Movie> showListMovies() {
        return listMovies;
    }

    public ArrayList<Serie> showListSeries() {
        return listSeries;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public ArrayList<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public ArrayList<Serie> getFavoriteSeries() {
        return favoriteSeries;
    }


public void ReproduccionFrame() {
        // Ver el tráiler de la película
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane pane = new JOptionPane();
                        JProgressBar progressBar = new JProgressBar(0, 100);
                        progressBar.setIndeterminate(false);
                        progressBar.setStringPainted(true);
                        pane.setMessage(new Object[] { "Reproduciendo", progressBar });

                        JDialog dialog = pane.createDialog("Reproduciendo");
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                        Timer timer = new Timer(1000, e -> {
                            int value = progressBar.getValue();
                            if (value < 100) {
                                progressBar.setValue(value + 10);
                            }
                        });
                        timer.setRepeats(true);
                        timer.start();

                        Timer closeTimer = new Timer(10000, e -> {
                            dialog.dispose();
                            timer.stop(); // Stop the timer when closing the dialog
                        });
                        closeTimer.setRepeats(false);
                        closeTimer.start();

                        dialog.setVisible(true);
                    });
    }






}

