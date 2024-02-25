package co.edu.uptc.view;

import java.util.ArrayList;
import java.util.Arrays;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;

public class Prueba {


    public static void main(String[] args) {
        

        String name = "", descripcion = "", genero = "";
        int duracion = 0, index = 0;
        ArrayList<String> listAutores = new ArrayList<>();
        ArrayList<String> listActores = new ArrayList<>();
        ArrayList<Season> listSeason = new ArrayList<>();
        ArrayList<Chapter> listCapitulos = new ArrayList<>();

        ArrayList<String> listAutores2 = new ArrayList<>();
        ArrayList<String> listActores2 = new ArrayList<>();
        ArrayList<Season> listSeason2 = new ArrayList<>();
        ArrayList<Chapter> listCapitulos2 = new ArrayList<>();


        name = "loki";
        descripcion = "serie1";
        genero = "genero1";
        duracion = 120;

        listAutores.add("autor1");
        listAutores.add("autor2");

        listActores.add("Actor1");
        listActores.add("Actor2");

        listCapitulos.add(new Chapter("capitulo1", 100));
        listCapitulos.add(new Chapter("capitulo2", 110));

        listSeason.add(new Season("Tempo1", "Descripcion tempo1", listCapitulos));

        AdminController ac = new AdminController();

        ac.addSerie(name, descripcion, duracion, listAutores, genero, listActores, "Tempo1","Descripcion tempo1", "capitulo1", 100);

        name = "Avengers";
        descripcion = "serie2";
        genero = "genero2";
        duracion = 120;

        listAutores2.add("autor3");
        listAutores2.add("autor4");

        listActores2.add("Actor3");
        listActores2.add("Actor4");

        listCapitulos2.add(new Chapter("capitulo3", 200));
        listCapitulos2.add(new Chapter("capitulo4", 210));

        listSeason2.add(new Season("Tempo2", "Descripcion tempo2", listCapitulos2));
        ac.addSerie(name, descripcion, duracion, listAutores2, genero, listActores2, "Tempo2","Descripcion tempo2", "capitulo2", 200);

        ac.addSeason(0, 1, "Tempo2", "Continuacion", "capitulo1.1", 300);


        for (Serie list : ac.showListSeries()) {
            System.out.println(list);
        }
    }
}