package co.edu.uptc.view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.ControlerInitialMenuView;
import co.edu.uptc.model.Role;
import co.edu.uptc.model.User;

public class InitialMenuView {
    static ControlerInitialMenuView controler = new ControlerInitialMenuView();

    static AdminController adminController = new AdminController();

    public static void main(String[] args) {
        AdminView av = new AdminView(adminController, controler);
        try {
            adminController.getListMovies().addAll(adminController.loadMovie("Movie"));
            controler.getUsers().addAll(controler.loadUsers("Users"));
            adminController.getListSeries().addAll(adminController.loadSerie("Series"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        // crear el admin

        // carga info de usuarios por archivos

        // Archivo guardar info

        // login
        // valida login
        // menu

        // predeterminado id
        int au = 0;
        String optionsHome[] = { "Login", "Register", "Visit" };

        // Repetir el menu de inicio
        int exit = 0;
        do {
            // Cambiar el texto del botón de Cancelar por "Salir de la apliciòn "
            UIManager.put("OptionPane.cancelButtonText", "Exit the application");
            UIManager.put("OptionPane.okButtonText", "select");

            // icono de la imagen
            ImageIcon icon = new ImageIcon("src\\co\\edu\\uptc\\image\\logo UPTCine.jpg");

            // Obtener la imagen del ImageIcon original
            Image originalImage = icon.getImage();

            // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
            int newWidth = 250;
            int newHeight = 250;

            // Redimensionar la imagen
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // Crear un nuevo ImageIcon a partir de la imagen redimensionada
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            String seleccion = (String) JOptionPane.showInputDialog(null, "Select an option: ",
                    "Opciones de Inicio", JOptionPane.QUESTION_MESSAGE, resizedIcon, optionsHome,
                    optionsHome[0]);
            // para que influya en todos
            UIManager.put("OptionPane.cancelButtonText", "Back");
            UIManager.put("OptionPane.okButtonText", "Accept");
            if (seleccion == null) {
                seleccion = "exit";
            }
            switch (seleccion) {
                // INGRESA A LA APLICACION DEPENDEN DEL ROL
                case "Login": {
                    boolean exits = false;
                    int auu = 0;
                    String email = "";
                    do {
                        if (auu == 0) {
                            email = "";
                        }

                        JPanel panel = new JPanel();
                        JTextField emailField = new JTextField(email, 10);
                        JPasswordField passwordField = new JPasswordField(10);

                        // Agregar los componentes al panel
                        panel.add(new JLabel("Email:"));
                        panel.add(emailField);
                        panel.add(new JLabel("Password:"));
                        panel.add(passwordField);

                        // Cambiar el texto del botón
                        UIManager.put("OptionPane.okButtonText", "get in");
                        UIManager.put("OptionPane.cancelButtonText", "Back");
                        // para que aparezca en vertical
                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                        // color del panel
                        panel.setBackground(Color.orange);

                        // icono de la imagen

                        ImageIcon iconLogin = new ImageIcon("src\\co\\edu\\uptc\\image\\login.png");

                        // Obtener la imagen del ImageIcon original
                        Image login = iconLogin.getImage();

                        // Definir el tamaño deseado para la imagen (por ejemplo, 200x200 píxeles)
                        newWidth = 100;
                        newHeight = 100;

                        // Redimensionar la imagen
                        Image dlogin = login.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                        // Crear un nuevo ImageIcon a partir de la imagen redimensionada
                        ImageIcon a = new ImageIcon(dlogin);

                        int resultado = JOptionPane.showConfirmDialog(null, panel,
                                "Ingrese sus datos", JOptionPane.OK_CANCEL_OPTION, 1, a);

                        if (resultado == JOptionPane.OK_OPTION) {
                            // Obtener los valores ingresados por el usuario
                            email = emailField.getText();

                            String password = passwordField.getText();
                            if (!email.isEmpty() && !password.isEmpty()) {
                                User userr = controler.findUserByEmail(email);
                                if (userr != null) {
                                    if (userr.getPassword().equals(password)) {

                                        if (userr.getRole() == Role.user) {
                                            /////// aca va la vista del usuaario reguistrado

                                            UserRegisterView ur = new UserRegisterView(adminController, userr,
                                                    controler, av);

                                            ur.userRegisterView();
                                        } else if (userr.getRole() == Role.admin) {

                                            av.menuAdmin();
                                        }
                                    } else {
                                        auu = 1;
                                        JOptionPane.showMessageDialog(null, "Incorrect password");

                                    }
                                } else {
                                    auu = 1;
                                    JOptionPane.showMessageDialog(null,
                                            "there is no registered user");

                                }
                            } else {
                                auu = 1;
                                JOptionPane.showMessageDialog(null,
                                        "I don't fill in the spaces correctly ");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Operation cancelled");
                            exits = true;
                        }
                    } while (!exits);
                }

                    break;

                case "Register": {
                    boolean exits = false;
                    String firstName = "";
                    String lastName = "";
                    String error = "";
                    String email = "";

                    do {
                        if (au == 0) {
                            firstName = "";
                            lastName = "";
                            email = "";
                            error = "";
                        }

                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                        JTextField firstNameField = new JTextField(firstName, 10);
                        JPasswordField passwordField = new JPasswordField(10);
                        JPasswordField confirmPasswordField = new JPasswordField(10);
                        JTextField lastNameField = new JTextField(lastName, 10);
                        JTextField emailField = new JTextField(email, 10);

                        panel.add(new JLabel("First Name:"));
                        panel.add(firstNameField);
                        panel.add(new JLabel("Last Name:"));
                        panel.add(lastNameField);
                        panel.add(new JLabel("Email:"));
                        panel.add(emailField);
                        panel.add(new JLabel("Password:"));
                        panel.add(passwordField);
                        panel.add(new JLabel("Confirm Password:"));
                        panel.add(confirmPasswordField);

                        JLabel errorLabel = new JLabel(error);
                        errorLabel.setForeground(Color.RED);
                        panel.add(errorLabel);

                        panel.setBackground(Color.ORANGE);

                        ImageIcon iconChef = new ImageIcon("src\\co\\edu\\uptc\\image\\register.png");
                        Image chefImg = iconChef.getImage();
                        newWidth = 150;
                        newHeight = 150;
                        Image chefImgs = chefImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                        ImageIcon imgchef = new ImageIcon(chefImgs);

                        int resultado = JOptionPane.showConfirmDialog(null, panel,
                                "Ingrese sus datos", JOptionPane.OK_CANCEL_OPTION, 1, imgchef);

                        if (resultado == JOptionPane.OK_OPTION) {
                            firstName = firstNameField.getText().trim();
                            lastName = lastNameField.getText().trim();
                            email = emailField.getText().trim();
                            String password = new String(passwordField.getPassword());
                            String confirmPassword = new String(confirmPasswordField.getPassword());

                            if (!firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()
                                    && !confirmPassword.isEmpty()) {
                                if (password.equals(confirmPassword)) {
                                    int emailRevi = controler.isEmailUnique(email);
                                    int validePassworMin = controler.validatePassword(confirmPassword);
                                    int uniqueEmail = controler.uniqueEmail(email);

                                    if (emailRevi == 0 && validePassworMin == 0 && uniqueEmail == 0) {
                                        controler.user(new User(firstName, lastName, email,
                                                password, Role.user));
                                        controler.userRegister();
                                        au = 0;
                                        JOptionPane.showMessageDialog(null, "Registered user");
                                    } else if (uniqueEmail == 2) {
                                        au = 1;
                                        email = "";
                                        error = "Email used error";
                                    } else if (emailRevi == 1) {
                                        au = 1;
                                        email = "";
                                        error = "Invalid email format error";
                                    } else if (validePassworMin == 3) {
                                        au = 1;
                                        error = "The password does not match what was expected";
                                    }
                                } else {
                                    au = 1;
                                    error = "Passwords do not match";
                                }
                            } else {
                                au = 1;
                                error = "Fill in all the fields correctly";
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Operation cancelled");
                            exits = true;
                        }
                    } while (!exits);
                    break;
                }

                case "Visit": {
                    ViewVisit viewVisit = new ViewVisit(adminController);
                    viewVisit.visitView();
                    break;
                }
                case "exit": {
                    JOptionPane.showMessageDialog(null, "successful exit");
                    exit = -1;
                    break;
                }

            }
        } while (exit == 0);

    }

}
