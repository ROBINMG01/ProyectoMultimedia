package co.edu.uptc.controllerFx;

import co.edu.uptc.model.User;
public class Prueba {
    private static Prueba instance;
    private User user;

    public Prueba() {
        // constructor privado
    }

    public static Prueba getInstance() {
        if (instance == null) {
            instance = new Prueba();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}