package co.edu.uptc.view;

import java.util.ArrayList;
import java.util.Scanner;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class AdminView {

    static String menu = "1. Add movie\n" + "2. Add series\n" + "3. Edit category movie\n" + "4. Edit category series\n"
        +"5. Update movie\n" + "6. Update series\n" + "7. Delete movie\n" + "8.Delete serie\n" + "9. Exit\n";
    static int option = 0, duration = 0, option2 = 0;
    static String name = "", description = "", actor = "", chapter = "";
    static boolean verefication = false;
    static AdminController Ac = new AdminController();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        menuAdmin();
    }
    
    public static void menuAdmin(){

        do {
            do {
                try {
                    System.out.println(menu);
                    System.out.println("Input option");
                    option = sc.nextInt();
                    sc.nextLine();
                    verefication = true;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid option");
                    sc.nextLine();
                    verefication = false;
                }
            } while (verefication == false);
            switch (option) {
                case 1:
                    if (addMovie()) {
                        System.out.println("Movie add sucesfully");
                    } else {
                        System.out.println("Movie wasn't add sucesfully");
                    }
                    System.out.println("------------------------");
                    break;
                case 2:
                    if (addSerie()) {
                        System.out.println("Movie add sucesfully");
                    } else {
                        System.out.println("Movie wasn't add sucesfully");
                    }
                    System.out.println("------------------------");
                    break;
                case 3:
                    for (Movie movies : Ac.showListMovies()) {
                        System.out.println(movies);
                    }
                    System.out.println("------------------------");
                break;
                case 4:
                    for (Serie series : Ac.showListSeries()) {
                        System.out.println(series);
                    }
                    System.out.println("------------------------");
                    break;
                default:
                    break;
            }
        } while (option != 9);
        sc.close();
    }

    public static boolean addMovie(){
        ArrayList<String> listActors = new ArrayList<>();
        System.out.println("Title");
        name = sc.nextLine();
        System.out.println("Description");
        description = sc.nextLine();
        do {
            try {
                System.out.println("Duration in minutes");
                duration = sc.nextInt();
                sc.nextLine();
                verefication = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid option");
                sc.nextLine();
                verefication = false;
            }
            
        } while (verefication == false);
        do {
            System.out.println("actors");
            actor = sc.nextLine();
            listActors.add(actor);
            do {
                try {
                    System.out.println("Want to add another actor?");
                    System.out.println("1. Yes o 2. Not");
                    option2 = sc.nextInt();
                    sc.nextLine();
                    verefication = true;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid option");
                    sc.nextLine();
                    verefication = false;
                }
            
            } while (verefication == false);
            
            if (option2 == 1) {
                verefication = false;
            } else {
                verefication = true;
            }
        } while (verefication == false);
        System.out.println("HOlaaa");
        return Ac.addMovie(name, description, duration, listActors);
    }

    public static boolean addSerie(){
        ArrayList<String> listActors = new ArrayList<>();
        ArrayList<String> listChapters = new ArrayList<>();
        System.out.println("Title");
        name = sc.nextLine();
        System.out.println("Description");
        description = sc.nextLine();
        do {
            try {
                System.out.println("Duration in minutes");
                duration = sc.nextInt();
                sc.nextLine();
                verefication = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid option");
                sc.nextLine();
                verefication = false;
            }
            
        } while (verefication == false);
        do {
            System.out.println("actors");
            actor = sc.nextLine();
            listActors.add(actor);
            do {
                try {
                    System.out.println("Want to add another actor?");
                    System.out.println("1. Yes o 2. Not");
                    option2 = sc.nextInt();
                    sc.nextLine();
                    verefication = true;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid option");
                    sc.nextLine();
                    verefication = false;
                }
            
            } while (verefication == false);
            
            if (option2 == 1) {
                verefication = false;
            } else {
                verefication = true;
            }
        } while (verefication == false);

        do {
            System.out.println("chapters");
            chapter = sc.nextLine();
            listChapters.add(chapter);
            do {
                try {
                    System.out.println("Want to add another chapter?");
                    System.out.println("1. Yes o 2. Not");
                    option2 = sc.nextInt();
                    sc.nextLine();
                    verefication = true;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid option");
                    sc.nextLine();
                    verefication = false;
                }
            
            } while (verefication == false);
            
            if (option2 == 1) {
                verefication = false;
            } else {
                verefication = true;
            }
        } while (verefication == false);
        System.out.println("HOlaaa");
        return Ac.addSerie(name, description, duration, listActors, listChapters);
    }
}
