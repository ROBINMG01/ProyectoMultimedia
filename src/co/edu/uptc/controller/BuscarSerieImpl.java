package co.edu.uptc.controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import co.edu.uptc.utilitaries.Utilitaries;

public class BuscarSerieImpl {
    private Utilitaries utilitaries;

    public BuscarSerieImpl() {
        utilitaries = new Utilitaries();
    }

    public void buscar() {
        // Catálogo de películas y series con año de lanzamiento y género.
        ArrayList<String> catalog = new ArrayList<>();
        catalog.addAll(utilitaries.getMovieCatalog());
        catalog.addAll(utilitaries.getSeriesCatalog());

        // Opciones de búsqueda
        String[] options = {"Search by name", "Search by genre", "Search by release year"};

        // Muestra las opciones de búsqueda en un cuadro de diálogo
        String selectedOption =
                (String) JOptionPane.showInputDialog(null, "Select a search option:",
                        "Search", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        // Comprobar si se seleccionó "Cancelar" en el cuadro de diálogo
        if (selectedOption == null) {
            JOptionPane.showMessageDialog(null, "You have canceled the search.");
            return;
        }

        // Realiza la búsqueda en base a la opción seleccionada
        String result = "";
        switch (selectedOption) {
            case "Search by name":
                String searchedItem = JOptionPane
                        .showInputDialog("Enter the name of the item you want to search:");
                for (String item : catalog) {
                    if (item.toLowerCase().contains(searchedItem.toLowerCase())) {
                        result += item + "\n";
                    }
                }
                break;
            case "Search by genre":
                String searchedGenre = JOptionPane
                        .showInputDialog("Enter the genre of the item you want to search:");
                for (String item : catalog) {
                    if (item.toLowerCase().contains(searchedGenre.toLowerCase())) {
                        result += item + "\n";
                    }
                }
                break;
            case "Search by release year":
                String searchedYear = JOptionPane.showInputDialog(
                        "Enter the release year of the item you want to search:");
                for (String item : catalog) {
                    if (item.toLowerCase().contains(searchedYear.toLowerCase())) {
                        result += item + "\n";
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(null,
                        "Invalid option. Please select a valid option.");
                return;
        }

        // Mostrar el resultado de la búsqueda
        if (!result.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Search results:\n" + result);
        } else {
            JOptionPane.showMessageDialog(null, "No results found for the search.");
        }
    }
}
