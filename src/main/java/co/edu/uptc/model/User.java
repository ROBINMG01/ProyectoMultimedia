package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private ArrayList<Movie> listMoviesFavorites;
    private ArrayList<Serie> listSeriesFavorites;

    private Date dateDeSubscription;
    private Date finDateDeSubscription;
    private String actieSusciption;

    public User(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.listMoviesFavorites = new ArrayList<>();
        this.listSeriesFavorites = new ArrayList<>();
        this.actieSusciption = "desactive";
        this.dateDeSubscription = new Date();
        this.finDateDeSubscription = new Date();

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

    public Date getDateDeSubscription() {
        return dateDeSubscription;
    }

    public void setDateDeSubscription(Date dateDeSubscription) {
        this.dateDeSubscription = dateDeSubscription;
    }

    public String getActieSusciption() {
        return actieSusciption;
    }

    public void setActieSusciption(String actieSusciption) {
        this.actieSusciption = actieSusciption;
    }

    public Date getFinDateDeSubscription() {
        return finDateDeSubscription;
    }

    public void setFinDateDeSubscription(Date finDateDeSubscription) {
        this.finDateDeSubscription = finDateDeSubscription;
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

        Movie mm = new Movie();
        for (Movie movie : listMoviesFavorites) {
            if (movie.getName().equals(namemovie)) {
                mm = movie;
            }
        }
        listMoviesFavorites.remove(mm);
        user.setListMoviesFavorites(listMoviesFavorites);
    }

    // metodo que elimina una serie

    public void deleteSerie(String nameSerie, User user) {

        Serie mm = new Serie();
        for (Serie movie : listSeriesFavorites) {
            if (movie.getName().equals(nameSerie)) {
                mm = movie;
            }
        }
        listSeriesFavorites.remove(mm);
        user.setListSeriesFavorites(listSeriesFavorites);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", listMoviesFavorites=" + listMoviesFavorites +
                ", listSeriesFavorites=" + listSeriesFavorites +
                ", dateDeSubscription=" + dateDeSubscription +
                ", finDateDeSubscription=" + finDateDeSubscription +
                ", actieSusciption='" + actieSusciption + '\'' +
                '}';
    }

    public String toStringPDF() {
        return "Purchase invoice" +
                "\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
                "\nfirstName: " + firstName +
                "\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
                "\n lastName: " + lastName +
                "\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
                "\n email: " + email +
                "\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
                "\n dateDeSubscription: " + dateDeSubscription +
                "\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
                "\n finDateDeSubscription: " + finDateDeSubscription +
                "\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
                "\n actieSusciption: " + actieSusciption;
    }
}