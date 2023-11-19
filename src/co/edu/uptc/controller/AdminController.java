package co.edu.uptc.controller;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class AdminController {
    private Movie movie;
    private Serie serie;
    private ArrayList<Movie> listMovies;
    private ArrayList<Serie> listSeries;

    public ArrayList<Movie> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<Movie> listMovies) {
        this.listMovies = listMovies;
    }

    public ArrayList<Serie> getListSeries() {
        return listSeries;
    }

    public void setListSeries(ArrayList<Serie> listSeries) {
        this.listSeries = listSeries;
    }

    public AdminController() {
        listMovies = new ArrayList<>();
        listSeries = new ArrayList<>();
        movie = new Movie();
        serie = new Serie();
    }

    public boolean addMovie(String name, String description, int duration, ArrayList<String> actors) {
        movie.setName(name);
        movie.setDescription(description);
        movie.setDuration(duration);
        movie.setListActors(actors);

        if (name.equals(movie.getName()) && duration == movie.getDuration()) {
            listMovies.add(new Movie(name, description, duration, actors));
            return true;
        }
        return false;
    }

    public boolean addSerie(String name, String description, int duration, ArrayList<String> actors,
            ArrayList<String> chapters) {
        serie.setName(name);
        serie.setDescription(description);
        serie.setDuration(duration);
        serie.setListActors(actors);
        serie.setListChapters(chapters);

        if (name.equals(serie.getName()) && duration == serie.getDuration()) {
            listSeries.add(new Serie(name, description, duration, actors, chapters));
            return true;
        }
        return false;
    }

    public ArrayList<Movie> showListMovies() {
        return listMovies;
    }

    public ArrayList<Serie> showListSeries() {
        return listSeries;
    }

    public void viewSeries(ArrayList<Serie> listSeries, Serie serie, String mensaje) {
        // Crear un nuevo JPanel
        JPanel panel = new JPanel();

        // Crear un nuevo JList
        JList<Serie> jList = new JList<Serie>(listSeries.toArray(new Serie[0]));

        // Establecer el renderizador de celdas del JList
        jList.setCellRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
                        cellHasFocus);
                Serie serie = (Serie) value;
                label.setText(serie.getName());
                return label;
            }
        });

        // Agregar un ListSelectionListener al JList
        jList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                Serie selectedSerie = (Serie) jList.getSelectedValue();
                JOptionPane.showMessageDialog(panel,
                        "Name: " + selectedSerie.getName() + "\nDescription: " + selectedSerie.getDescription()
                                + "\nDuration: " + selectedSerie.getDuration());
            }
        });

        // Agregar el JList a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(jList);

        // Agregar el JScrollPane al JPanel
        panel.add(scrollPane);

        // Mostrar el JPanel en una ventana emergente JOptionPane
        JOptionPane.showMessageDialog(null, panel, mensaje, JOptionPane.PLAIN_MESSAGE);
    }

    public void viewMovies(ArrayList<Movie> listMovies, Movie movie, String mensaje) {
        // Crear un nuevo JPanel
        JPanel panel = new JPanel();

        // Crear un nuevo JList
        JList<Movie> jList = new JList<Movie>(listMovies.toArray(new Movie[0]));

        // Agregar un ListSelectionListener al JList
        jList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                Movie selectedMovie = (Movie) jList.getSelectedValue();
                JOptionPane.showMessageDialog(panel,
                        "Name: " + selectedMovie.getName() + "\nDescription: " + selectedMovie.getDescription()
                                + "\nDuration" + selectedMovie.getDuration());
            }
        });

        // Agregar el JList a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(jList);

        // Agregar el JScrollPane al JPanel
        panel.add(scrollPane);

        // Mostrar el JPanel en una ventana emergente JOptionPane
        JOptionPane.showMessageDialog(null, panel, mensaje, JOptionPane.PLAIN_MESSAGE);
    }

}
