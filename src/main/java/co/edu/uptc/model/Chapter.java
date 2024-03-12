package co.edu.uptc.model;

public class Chapter {

    private String name;
    private int duration;
    private String trailerUrl;

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public Chapter() {
    }

    public Chapter(String name, int duration, String trailerUrl) {
        this.name = name;
        this.duration = duration;
        this.trailerUrl = trailerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Chapter: " +
                "\nName: '" + name + '\'' +
                ", Duration: " + duration;
    }
}
