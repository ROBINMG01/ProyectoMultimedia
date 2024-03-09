package co.edu.uptc.model;

public class Favorite {
    private String name;
    private String description;
    // Agrega aquí cualquier otro dato que necesites para un favorito

    public Favorite(String name, String description) {
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return "Favorite [name=" + name + ", description=" + description + "]";
    }

    
}