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
    private ArrayList<Object> favorites;

    public User(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.listMovies = new ArrayList<>();
        this.listSeries = new ArrayList<>();
        this.favorites = new ArrayList<>();
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
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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

    public ArrayList<Object> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Object> favorites) {
        this.favorites = favorites;
    }

    public void addMovie(Movie movie) {
        listMovies.add(movie);
    }

    public void addSerie(Serie serie) {
        listSeries.add(serie);
    }
// metodo que elimona una pelicula 

    public void deleteMovie(String namemovie, User user) {
     
       ArrayList<Movie> m=user.getListMovies();
    Movie mm= new Movie();
        for (Movie movie : m) {
            if (movie.getName().equals(namemovie)) {
                mm=movie;
            }
        }
        m.remove(mm);
        user.setListMovies(m);
    }


//metodo que elimina una serie 

    public void deleteSerie(String namemovie, User user) {
     
       ArrayList<Serie> m=user.getListSeries();
    Serie mm= new Serie();
        for (Serie movie : m) {
            if (movie.getName().equals(namemovie)) {
                mm=movie;
            }
        }
        m.remove(mm);
        user.setListSeries(m);
    }
    @Override
    public String toString() {
        return "{" +
                " firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", role='" + getRole() + "'" +
                ", listMovies='" + getListMovies() + "'" +
                ", listSeries='" + getListSeries() + "'" +
                ", favorites='" + getFavorites() + "'" +
                "}";
    }

}
