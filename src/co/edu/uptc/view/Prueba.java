package co.edu.uptc.view;

import javax.swing.*;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Prueba extends JFrame {

    private static final long serialVersionUID = 1L;
    private static AdminController Ac = new AdminController();
    private static JTextField inputField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Admin View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        inputField = new JTextField();
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput(outputArea);
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.EAST);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private static void processInput(JTextArea outputArea) {
        String inputText = inputField.getText().trim();
        outputArea.append("Input: " + inputText + "\n");

        // TODO: Replace this switch statement with your logic
        switch (inputText) {
            case "1":
                if (addMovie()) {
                    showMessage("Movie added successfully");
                } else {
                    showMessage("Failed to add movie");
                }
                break;
            case "2":
                if (addSerie()) {
                    showMessage("Series added successfully");
                } else {
                    showMessage("Failed to add series");
                }
                break;
            case "3":
                StringBuilder movieListText = new StringBuilder();
                for (Movie movies : Ac.showListMovies()) {
                    movieListText.append(movies.toString()).append("\n");
                }

                // Create a JFrame to display the movie list
                JFrame movieListFrame = new JFrame("Movie List");
                movieListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                movieListFrame.setSize(400, 300);

                // Create a JTextArea to display the movie list
                JTextArea movieListTextArea = new JTextArea(movieListText.toString());
                movieListTextArea.setEditable(false);

                // Add the JTextArea to a JScrollPane for scrolling if needed
                JScrollPane scrollPane = new JScrollPane(movieListTextArea);

                // Add the JScrollPane to the JFrame
                movieListFrame.getContentPane().add(scrollPane);

                // Make the JFrame visible
                movieListFrame.setVisible(true);
                break;
            case "4":
                StringBuilder serieListText = new StringBuilder();
                for (Serie serie : Ac.showListSeries()) {
                    serieListText.append(serie.toString()).append("\n");
                }

                // Create a JFrame to display the movie list
                JFrame serieListFrame = new JFrame("Serie List");
                serieListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                serieListFrame.setSize(400, 300);

                // Create a JTextArea to display the movie list
                JTextArea serieListTextArea = new JTextArea(serieListText.toString());
                serieListTextArea.setEditable(false);

                // Add the JTextArea to a JScrollPane for scrolling if needed
                JScrollPane scrollPaneL = new JScrollPane(serieListTextArea);

                // Add the JScrollPane to the JFrame
                serieListFrame.getContentPane().add(scrollPaneL);

                // Make the JFrame visible
                serieListFrame.setVisible(true);
                break;
            case "5":
                if (updateMovie()) {
                    showMessage("Series update successfully");
                } else {
                    showMessage("Failed to update series");
                }
                break;
            case "6":
                if (updateSerie()) {
                    showMessage("Series update successfully");
                } else {
                    showMessage("Failed to update series");
                }
                break;
            // Add cases for other options

            default:
                showMessage("Invalid option");
                break;
        }

        inputField.setText(""); // Clear the input field after processing
    }

    private static boolean addMovie() {
        // Implement the logic to add a movie using Ac.addMovie method
        // and return the appropriate boolean value
        // ...

        // return true; // Placeholder, replace with actual value
        // You may want to prompt the user for input using JOptionPane
        String name = JOptionPane.showInputDialog("Enter movie title:");
        String description = JOptionPane.showInputDialog("Enter movie description:");
        String duration = JOptionPane.showInputDialog("Enter movie duration (in minutes):");
        String listActors = JOptionPane.showInputDialog("Enter movie actors (comma-separated):");

        // Validate input (add your validation logic here)

        // Call the addMovie method in your AdminController
        boolean result = Ac.addMovie(name, description, duration, listActors);

        if (result) {
            showMessage("Movie added successfully");
        } else {
            showMessage("Failed to add movie");
        }

        return result;

    }

    private static boolean addSerie() {
        // Implement the logic to add a series using Ac.addSerie method
        // and return the appropriate boolean value
        // ...

        // return true; // Placeholder, replace with actual value
        // You may want to prompt the user for input using JOptionPane
        String name = JOptionPane.showInputDialog("Enter series title:");
        String description = JOptionPane.showInputDialog("Enter series description:");
        String duration = JOptionPane.showInputDialog("Enter series duration (in minutes):");
        String listActors = JOptionPane.showInputDialog("Enter series actors (comma-separated):");
        String chapters = JOptionPane.showInputDialog("Enter series chapters:");

        // Validate input (add your validation logic here)

        // Call the addSerie method in your AdminController
        boolean result = Ac.addSerie(name, description, duration, listActors, chapters);

        if (result) {
            showMessage("Series added successfully");
        } else {
            showMessage("Failed to add series");
        }

        return result;
    }

    public static boolean updateMovie() {
        // You may want to prompt the user for input using JOptionPane
        String nameUpdate = JOptionPane.showInputDialog("Enter the name of the movie to update:");

        // Validate input (add your validation logic here)

        // Check if the movie to update exists
        if (Ac.searchMovie(nameUpdate) != -1) {
            // Prompt the user for new movie details
            String name = JOptionPane.showInputDialog("Enter new movie title:");
            String description = JOptionPane.showInputDialog("Enter new movie description:");
            String duration = JOptionPane.showInputDialog("Enter new movie duration (in minutes):");
            String listActors = JOptionPane.showInputDialog("Enter new movie actors (comma-separated):");

            // Validate input (add your validation logic here)

            // Call the updateMovie method in your AdminController
            boolean result = Ac.updateMovie(nameUpdate, name, description, duration, listActors);

            if (result) {
                showMessage("Movie updated successfully");
            } else {
                showMessage("Failed to update movie");
            }

            return result;
        } else {
            showMessage("Movie not found");
            return false;
        }
    }

    public static boolean updateSerie() {
        // You may want to prompt the user for input using JOptionPane
        String nameUpdate = JOptionPane.showInputDialog("Enter the name of the series to update:");

        // Validate input (add your validation logic here)

        // Check if the series to update exists
        if (Ac.searchSeries(nameUpdate) != -1) {
            // Prompt the user for new series details
            String name = JOptionPane.showInputDialog("Enter new series title:");
            String description = JOptionPane.showInputDialog("Enter new series description:");
            String duration = JOptionPane.showInputDialog("Enter new series duration (in minutes):");
            String listActors = JOptionPane.showInputDialog("Enter new series actors (comma-separated):");
            String chapters = JOptionPane.showInputDialog("Enter new series chapters:");

            // Validate input (add your validation logic here)

            // Call the updateSeries method in your AdminController
            boolean result = Ac.updateSeries(nameUpdate, name, description, duration, listActors, chapters);

            if (result) {
                showMessage("Series updated successfully");
            } else {
                showMessage("Failed to update series");
            }

            return result;
        } else {
            showMessage("Series not found");
            return false;
        }

    }

    private static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
