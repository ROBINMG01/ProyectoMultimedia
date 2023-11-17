package co.edu.uptc.model;

import javax.swing.JOptionPane;
import co.edu.uptc.controller.UserController;

public class UserRegister {

    public static void main(String[] args) {
        UserController userController = new UserController();

        // Muestra el menú hasta que el usuario elija la opción de salir
        boolean exit = false;
        while (!exit) {
            // Muestra el menú y obtiene la opción seleccionada por el usuario.
            String option = JOptionPane.showInputDialog("Welcome to the multimedia project\n"
                    + "[1]. View movie catalog\n" + "[2] View series catalog\n"
                    + "[3]. Search series\n" + "[4] View my favorites\n"
                    + "[5]. View my playback history\n" + "[6]. Account settings\n" + "[7]. Exit\n"
                    + "Enter the number of the desired option:");

            // Realizar una acción basada en la opción seleccionada
            switch (option) {
                case "1":
                    userController.showMovieCatalog();
                    break;
                case "2":
                    userController.showSeriesCatalog();
                    break;
                case "3":
                    userController.searchSeries();
                    break;
                case "4":
                    userController.showFavorites();
                    break;
                case "5":
                    userController.showPlaybackHistory();
                    break;
                case "6":
                    userController.showAccountSettings();
                    break;
                case "7":
                    exit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid option. Please select a valid option.");
                    break;
            }
        }
    }
}
