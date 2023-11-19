package co.edu.uptc.view;

import java.util.ArrayList;
import java.util.Scanner;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Serie;
import co.edu.uptc.utilitarian.Utilitarian;

public class Runner {
    public static void main(String[] args) {

        String gender = "";
        int w = 0, op = 0;
        Scanner sc = new Scanner(System.in);
        AdminController adminController = new AdminController();
        Utilitarian utilitarian = new Utilitarian();
        utilitarian.predefinedSeries(adminController.getListSeries());
        while (w != 1) {
            System.out.println("Elije la opcion a realizar \n1. Para ver series\1 \n2. ver peliculas\2 \n3.Salir\3");
            op = getValidInteger(1, 3);
            switch (op) {
                case 1:
                    while (w != 2) {

                        System.out.println(
                                "Por favor seleccione el genero \nAccion \nDrama \nAventura \nDiversion o '0' para volver");
                        gender = sc.nextLine();
                        if (gender.equalsIgnoreCase("0")) {
                            break;
                        }
                        adminController.filterSeries(adminController.getListSeries(), gender);
                        ArrayList<Serie> newList = adminController.filterSeries(adminController.getListSeries(),
                                gender);
                                System.out.println();
                        for (Serie serie2 : newList) {
                            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");
                            System.out.println("Título: " + serie2.toString());
                            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");

                        }
                    }
                    for (Serie series : adminController.showListSeries()) {
                        System.out.println(series);
                    }

                    break;
                case 2:

                    break;
                case 3:
                    System.exit(3);
                    break;

                default:
                    System.out.println("Option invalided");
                    break;
            }
        }

        sc.close();

    }

    public static int getValidInteger(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int valor = 0, close = 0;
        boolean valido = false;
        while (!valido) {
            try {
                valor = Integer.parseInt(sc.nextLine());
                if (valor >= min && valor <= max) {
                    valido = true;
                } else {
                    System.out.println("Number out of range. It must be between: " + min + " and " + max);
                }
            } catch (NumberFormatException npe) {
                System.out.println("Invalid information, please enter a valid number");
            }
            if (close == 1) {
                sc.close();
            }

        }

        return valor;
    }

}
