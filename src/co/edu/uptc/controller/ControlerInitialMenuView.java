package co.edu.uptc.controller;

import java.util.*;

import co.edu.uptc.model.Role;
import co.edu.uptc.model.User;
import co.edu.uptc.model.UserRegister;
import co.edu.uptc.util.FileManagement;

public class ControlerInitialMenuView extends UserRegister {
    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    private User user;
    private static FileManagement fileManagement;

    // se crea el administrador
    public void createAdmin() {
        User admin = new User("ADMN", "Admin", "Admin@gmail.com", "123", Role.admin);
        users.add(admin);
    }

    // inicializar el array de usuarios
    public ControlerInitialMenuView() {
        // usuario predefinidoooooooooooooooooooooooo
        fileManagement = new FileManagement();
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
        // Guardar los usuarios en el archivos
        saveUsers(users, "Users");
    }

    // metodo que verifica que meta un correo correcto
    public int isEmailUnique(String emailToCheck) {
        // Verificar si el correo electrónico tiene uno de los dominios específicos
        List<String> commonDomains = Arrays.asList("gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "aol.com");

        String[] parts = emailToCheck.split("@");

        // Verificar si hay un nombre antes del @
        if (parts.length != 2 || parts[0].isEmpty()) {
            // El correo electrónico no tiene un nombre antes del @ o no tiene el formato correcto
            return 1;
        }

        String domain = parts[1].toLowerCase();

        if (!commonDomains.contains(domain)) {
            // El correo electrónico no tiene uno de los dominios específicos
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

    public ArrayList<User> users() {
        return users;
    }

    public String encriptar(String texto, int clave) {
        StringBuilder textoEncriptado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);

            if (Character.isLetter(caracter) || Character.isDigit(caracter)) {
                // Verifica si el caracter es una letra o un número
                char base = Character.isUpperCase(caracter) ? 'A' : 'a';

                // Aplica la fórmula del cifrado César y agrega el caracter encriptado al
                // StringBuilder
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
        // Invoca el método de encriptación con la clave negativa para realizar el
        // descifrado
    }

    // metodo que llena la info con arcgivo

    public void saveUsers(List<User> listUsers, String file) {
        fileManagement.writeJsonToFile(file, listUsers);
    }

    public List<User> loadUsers(String file) {
        return fileManagement.loadUsersFromJson(file);
    }


//metodo que pone fecha limite de subscripcion

    public Date dateFinSuscripcion(User user) {
        // Obtener la fecha de suscripción del usuario
        Date fechaInicio = user.getDateDeSubscription();

        // Crear un objeto Calendar e inicializarlo con la fecha de inicio
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);

        // Agregar 30 días a la fecha de inicio
        calendar.add(Calendar.DAY_OF_MONTH, 30);

        // Obtener la fecha resultante
        Date fechaFin = calendar.getTime();

        return fechaFin;
    }


// metdodo que guarda info de usuarios

    public void saveInfoUser(){
        // Guardar los usuarios en el archivos
        saveUsers(users, "Users");
    }
    }




