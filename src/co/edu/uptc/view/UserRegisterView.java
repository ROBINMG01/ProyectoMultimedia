package co.edu.uptc.view;

import javax.swing.JOptionPane;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.BuscarSerieImpl;
import co.edu.uptc.controller.UserController;

public class UserRegisterView {
    private UserController userController;

    public UserRegisterView(AdminController ad) {
        this.userController = new UserController(ad);
    }

    public void userRegisterView() {

        UserController userController = new UserController();
        BuscarSerieImpl buscarSerieImpl = new BuscarSerieImpl();

        boolean exit = false;
        while (!exit) {
            String option = JOptionPane
                    .showInputDialog("Proyecto Multimedia\n" + "[1]. Ver catálogo de películas\n"
                            + "[2]. Ver catálogo de series\n" + "[3]. Buscar series y películas\n"
                            + "[4]. Ver mis favoritos\n" + "[5]. Configuración de la cuenta\n"
                            + "[6]. Salir\n" + "\nIngrese el número de la opción deseada:");

            switch (option) {
                case "1":
                    userController.showMovieCatalog();
                    break;
                case "2":
                    userController.showSeriesCatalog();
                    break;
                case "3":
                    buscarSerieImpl.buscar();
                    break;
                case "4":
                    userController.showFavorites();
                    break;
                case "5":
                    userController.showAccountSettings();
                    break;
                case "6":
                    exit = true;
                    JOptionPane.showMessageDialog(null, "Hasta luego, ¡vuelve pronto!", "Despedida",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opción inválida. Por favor, seleccione una opción válida.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }
}
