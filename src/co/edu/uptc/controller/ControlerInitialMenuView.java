package co.edu.uptc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.edu.uptc.model.Role;
import co.edu.uptc.model.User;
import co.edu.uptc.model.UserRegister;
import co.edu.uptc.persistence.Archive;


public class ControlerInitialMenuView extends UserRegister {
    private ArrayList<User> users;
    private User user;

    // se crea el administrador
    public void createAdmin() {
        User admin = new User("ADMN", "Admin", "Admin@gmail.com", "123", Role.admin);
        users.add(admin);
    }

    // inicializar el array de usuarios
    public ControlerInitialMenuView() {
        // usuario predefinidoooooooooooooooooooooooo

        this.users = new ArrayList<User>();
        User uPre = new User("1", "1", "1", "1", Role.user);
        users.add(uPre);
    }

    // AGREGA USUARIO A LA LISTA
    public void user(User user) {

        this.user = user;
    }

    public void userRegister() {
        users.add(user);
    }

    // metodo que verifica que meta un correo correcto
    public int isEmailUnique(String emailToCheck) {
        // Verificar si el correo electrónico tiene uno de los 5 dominios más comunes
        List<String> commonDomains = Arrays.asList("gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "aol.com",
                "uptc.edu.co");
        String[] parts = emailToCheck.split("@");

        if (parts.length == 2) {
            String domain = parts[1].toLowerCase();

            if (!commonDomains.contains(domain)) {
                
                return 1;
            }
        } else {
           
            return 1;
        }

        // El correo electrónico es único
        return 0;
    }

    // verificar que no haya un email repetido
    public int uniqueEmail(String emailToCheck) {
        // Verificar si el correo electrónico es único en la lista
        if (users != null) {
            for (User user : users) {
                if (user.getEmail().equals(emailToCheck)) {
                    // El correo electrónico ya existe en la lista
                    return 2;
                }
            }
        }

        // El correo electrónico es único
        return 0;
    }

    // validar que la contrasenia cumpla con lo minimo
    public int validatePassword(String password) {
        if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            return 0;
        }

        return 3;
    }

    // login buscar que el usuario por email
    public User findUserByEmail(String targetEmail) {
        for (User user : users) {
            if (user.getEmail().equals(targetEmail)) {
                return user; // Se ha encontrado el usuario
            }
        }
        return null; // No se encontró el usuario con el email dado
    }

    // metodo que modifica la informacion de un usuario reguistrado

    public void modifyUser(User user) {
        for (User userr : users) {
            if (userr.equals(user)) {
                userr = user;
            }
        }
    }

    // verificar que no haya un email repetido para modificar el usuario
    public int uniqueEmailUserRegister(String emailToCheck, User userr) {
        // verifica si no hizo cambios
        if (userr.getEmail().equals(emailToCheck)) {
            emailToCheck = "";
        }

        // Verificar si el correo electrónico es único en la lista
        if (users != null) {
            for (User user : users) {
                if (user.getEmail().equals(emailToCheck)) {
                    // El correo electrónico ya existe en la lista
                    return 2;
                }
            }
        } // El correo electrónico es único
        return 0;
    }



    // retornar usuarios 

    public ArrayList<User> users(){
        return users;
    }

    public String encriptar(String texto, int clave) {
        StringBuilder textoEncriptado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);

            if (Character.isLetter(caracter) || Character.isDigit(caracter)) {
                // Verifica si el caracter es una letra o un número
                char base = Character.isUpperCase(caracter) ? 'A' : 'a';

                // Aplica la fórmula del cifrado César y agrega el caracter encriptado al StringBuilder
                textoEncriptado.append((char) (((caracter - base + clave) % 26 + 26) % 26 + base));
            } else {
                // Mantén los caracteres no alfabéticos ni numéricos sin cambios
                textoEncriptado.append(caracter);
            }
        }

        return textoEncriptado.toString();
    }

    public String desencriptar(String textoEncriptado, int clave) {
        // Método para desencriptar un texto encriptado con el cifrado César
        return encriptar(textoEncriptado, -clave);
        // Invoca el método de encriptación con la clave negativa para realizar el descifrado
    }


// metodo que llena la info con arcgivo

public void llenaInfo(){
    Archive a= new Archive();
    this.users=a.readUserInfoFromFile("src\\co\\edu\\uptc\\archive\\Keep.txt");
}



}