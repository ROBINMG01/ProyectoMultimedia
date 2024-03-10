package co.edu.uptc.model;

public class Favorite {
    private String name;
    private String description;
    private int duration;
    private String gender;
    private String type;

    
    public Favorite() {
    }
    public Favorite(String name, String description, int duration, String gender, String type) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.gender = gender;
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    @Override
    public String toString() {
        return "Favorite [name=" + name + ", description=" + description + ", duration=" + duration + ", gender="
                + gender + "]";
    }
    
    
}