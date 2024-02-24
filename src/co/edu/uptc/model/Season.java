package co.edu.uptc.model;

import java.util.ArrayList;

public class Season {

    private String name;
    private ArrayList<Chapters> listChapters;
    private String description;


    public Season(String name, ArrayList<Chapters> listChapters, String description) {
        this.name = name;
        this.listChapters = listChapters;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Chapters> getListChapters() {
        return listChapters;
    }

    public void setListChapters(ArrayList<Chapters> listChapters) {
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
        return "Season{" +
                "name='" + name + '\'' +
                ", listChapters=" + listChapters +
                ", description='" + description + '\'' +
                '}';
    }
}
