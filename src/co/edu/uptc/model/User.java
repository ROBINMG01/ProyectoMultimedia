package co.edu.uptc.model;

import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private ArrayList<Movie> listMoviesFavorites;
    private ArrayList<Serie> listSeriesFavorites;

    public User(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.listMoviesFavorites = new ArrayList<>();
        this.listSeriesFavorites = new ArrayList<>();
    }

    public User() {
        this.listMoviesFavorites = new ArrayList<>();
        this.listSeriesFavorites = new ArrayList<>();
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

    public ArrayList<Movie> getListMoviesFavorites() {
        return listMoviesFavorites;
    }

    public void setListMoviesFavorites(ArrayList<Movie> listMoviesFavorites) {
        this.listMoviesFavorites = listMoviesFavorites;
    }

    public ArrayList<Serie> getListSeriesFavorites() {
        return listSeriesFavorites;
    }

    public void setListSeriesFavorites(ArrayList<Serie> listSeriesFavorites) {
        this.listSeriesFavorites = listSeriesFavorites;
    }

    public boolean assignMovieToFavorite(User user, Movie movie) {
        if (!user.getListMoviesFavorites().contains(movie)) {
            user.getListMoviesFavorites().add(movie);
            return true;
        }
        return false;
    }

    public boolean assignSerieToFavorite(User user, Serie serie) {
        if (!user.getListSeriesFavorites().contains(serie)) {
            user.getListSeriesFavorites().add(serie);
            return true;
        }
        return false;
    }

    // metodo que elimona una pelicula

    public void deleteMovie(String namemovie, User user) {

        ArrayList<Movie> m = user.getListMoviesFavorites();
        Movie mm = new Movie();
        for (Movie movie : m) {
            if (movie.getName().equals(namemovie)) {
                mm = movie;
            }
        }
        m.remove(mm);
        user.setListMoviesFavorites(m);
    }

    // metodo que elimina una serie

    public void deleteSerie(String namemovie, User user) {

        ArrayList<Serie> m = user.getListSeriesFavorites();
        Serie mm = new Serie();
        for (Serie movie : m) {
            if (movie.getName().equals(namemovie)) {
                mm = movie;
            }
        }
        m.remove(mm);
        user.setListSeriesFavorites(m);
    }

    @Override
    public String toString() {
        return "{" +
                " firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", role='" + getRole() + "'" +
                ", listMovies='" + getListMoviesFavorites() + "'" +
                ", listSeries='" + getListSeriesFavorites() + "'" +
                "}";
    }

}
