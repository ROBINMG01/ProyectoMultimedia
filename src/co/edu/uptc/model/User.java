package co.edu.uptc.model;

import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
   
    private String email;
    private String password;
    private Role role;
       private ArrayList<Movie> listMovies;
    private ArrayList<Serie> listSeries;

    public User(String firstName, String lastName,  String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
     
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

  

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ArrayList<Movie> getListMovies() {
        return this.listMovies;
    }

    public void setListMovies(ArrayList<Movie> listMovies) {
        this.listMovies = listMovies;
    }

    public ArrayList<Serie> getListSeries() {
        return this.listSeries;
    }

    public void setListSeries(ArrayList<Serie> listSeries) {
        this.listSeries = listSeries;
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password
                + ", role=" + role + ", listMovies=" + listMovies + ", listSeries=" + listSeries + "]";
    }

}