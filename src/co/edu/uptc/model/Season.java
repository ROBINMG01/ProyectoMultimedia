package co.edu.uptc.model;

import java.util.ArrayList;

public class Season {

    private String name;
    private ArrayList<Chapter> listChapters;
    private String description;


    public Season() {
    }

    public Season(String name, String description, ArrayList<Chapter> listChapters) {
        this.name = name;
        this.description = description;
        this.listChapters = listChapters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Chapter> getListChapters() {
        return listChapters;
    }

    public void setListChapters(ArrayList<Chapter> listChapters) {
        this.listChapters = listChapters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nSeason [name=" + name  + ", description=" + description + ", listChapters=" + listChapters +"]";
    }
}
