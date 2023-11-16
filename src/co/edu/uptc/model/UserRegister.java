package co.edu.uptc.model;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserRegister {
    public static void main(String[] args) {
        // Mostrar el menú hasta que el usuario elija la opción de salir
        boolean salir = false;
        while (!salir) {
            // Mostrar el menú y obtener la opción seleccionada por el usuario
            String opcion = JOptionPane.showInputDialog("Bienvenido al proyecto de multimedia\n"
                    + "[1]. Ver caálogo de pelíulas\n" + "[2] Vr catálogo e series\n"
                    + "[3]. Bscar serie\n" + "[4] Ver mis faoritos\n"
                    + "[5]. Ver mi historial de reproduccons\n"
                    + "[6]. Configuración de la cuenta\n" + "[7]. Salir\n"
                    + "Ingrese el número de la opción deseada:");

            // Realizar una acción basada en la opción seleccionada
            switch (opcion) {
                case "1":
                    mostrarCatalogoPeliculas();
                    break;
                case "2":                    mostrarCatalogoSerie();
                    break;
                case "3":
                    buscarSerie();
                    break;
                case "4":
                    mostrarFavoritos();
                    break;
                case "5":
                    mostrarHistorial();
                    break;
                case "6":
                    mostrarConfiguracion();
                    break;
                case "7":
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    static void mostrarCatalogoPeliculas() {
        // Catálogo de película con año de lanzamientoy género
        ArrayList<String> peliculas = new ArrayList<>();
        peliculas.add("Avengers: Endgame (2019, Acción/Aventura)");
        peliculas.add("The Dark Knight (2008, Acción/Crimen)");
        peliculas.add("Inception (2010, Acción/Ciencia ficción)");
        peliculas.add("Pulp Fiction (1994, Crimen/Drama)");
        peliculas.add("Fight Club (1999, Drama/Thriller)");
        peliculas.add("The Shawshank Redemption (1994, Drama/Crimen)");
        peliculas.add("The Godfather (1972, Drama/Crimen)");
        peliculas.add("Interstellar (2014, Drama/Ciencia ficción)");
        peliculas.add("The Matrix (1999, Acción/Ciencia ficción)");
        peliculas.add("Forrest Gump (1994, Drama/Comedia)");
        peliculas
                .add("The Lord of the Rings: The Fellowship of the Ring (2001, Aventura/Fantasía)");
        peliculas.add("The Lion King (1994, Animación/Aventura)");
        peliculas.add("Titanic (1997, Drama/Romance)");
        peliculas.add("Star Wars: Episode IV - A New Hope (977, Acción/Aventura)");
        peliculas.add("Jurassic Park (1993, Aventura/Ciencia ficción)");

        // Mostrar el catálogo de películas en un cuadro de diálogo
        String peliculaSeleccionada = (String) JOptionPane.showInputDialog(null,
                "Seleccione una película:", "Catálogo de Películas", JOptionPane.PLAIN_MESSAGE,
                null, peliculas.toArray(), peliculas.get(0));

       // Verificar si se seleccionó una película o se canceló
        if (peliculaSeleccionada != null) {
            // Mostrar la película seleccionada en un cuadro de diálogo
            JOptionPane.showMessageDialog(null,
                    "Ha seleccionado la película: " + peliculaSeleccionada);
        } else 
            // Mostrar mensaje de salida de la opción            
            JOptionPane.showMessageDialog(null,
                "Ha salido de la opción de ver el catálogo de películas.");
        }
    

    public static void mostrarCatalogoSerie() {
        // Catálogo de series con año de lanzamiento y género
        ArrayList<String> series = new ArrayList<>();
        series.add("Stranger Things (2016, Drama/Fantasía)");
        series.add("Breaking Bad (2008, Drama/Crimen)");
        series.add("Friends (1994, Comedia)");
        series.add("Game of Thrones (2011, Drama/Fantasía)");
        series.add("The Office (2005, Comedia)");
        series.add("The Crown (2016, Drama/Histórico)");
        series.add("Narcos (2015, Drama/Crimen)");
        series.add("Black Mirror (2011, Drama/Ciencia ficción)");
        series.add("The Big Bang Theory (2007, Comedia)");
        series.add("Sherlock (2010, Drama/Crimen)");
        series.add("Peaky Blinders (2013, Drama/Crimen)");
        series.add("The Walking Dead (2010, Drama/Terror)");
        series.add("Money Heist (2017, Drama/Crimen)");
        series.add("The Witcher (2019, Drama/Fantasía)");
        series.add("The Mandalorian (2019, Drama/Ciencia ficción)");

        // Mostrar el catálogo de series en un cuadro de diálogo
        String serieSeleccionada = (String) JOptionPane.showInputDialog(null,
                "Seleccione una serie:", "Catálogo de Series", JOptionPane.PLAIN_MESSAGE, null,
                series.toArray(), series.get(0));

        // Verificar si se seleccionó una serie o se canceló
        if (serieSeleccionada != null) {
            // Mostrar la serie seleccionada en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, "Ha seleccionado la serie: " + serieSeleccionada);
        } else {
            // Mostrar mensaje de salida de la opción
            JOptionPane.showMessageDialog(null,
                    "Ha salido de la opción de ver el catálogo de series.");
        }
    }

    public static void buscarSerie() {
        // Catálogo de series con año de lanzamiento y género
        ArrayList<String> series = new ArrayList<>();
        series.add("Stranger Things (2016, Drama/Fantasía)");
        series.add("Breaking Bad (2008, Drama/Crimen)");
        series.add("Friends (1994, Comedia)");
        series.add("Game of Thrones (2011, Drama/Fantasía)");
        series.add("The Office (2005, Comedia)");
        series.add("The Crown (2016, Drama/Histórico)");
        series.add("Narcos (2015, Drama/Crimen)");
        series.add("Black Mirror (2011, Drama/Ciencia ficción)");
        series.add("The Big Bang Theory (2007, Comedia)");
        series.add("Sherlock (2010, Drama/Crimen)");
        series.add("Peaky Blinders (2013, Drama/Crimen)");
        series.add("The Walking Dead (2010, Drama/Terror)");
        series.add("Money Heist (2017, Drama/Crimen)");
        series.add("The Witcher (2019, Drama/Fantasía)");
        series.add("The Mandalorian (2019, Drama/Ciencia ficción)");

        // Opciones de búsqueda
        String[] opciones =
                {"Buscar por nombre", "Buscar por género", "Buscar por año de lanzamiento"};

        // Mostrar las opciones de búsqueda en un cuadro de diálogo
        String opcionSeleccionada = (String) JOptionPane.showInputDialog(null,
                "Seleccione una opción de búsqueda:", "Búsqueda de Series",
                JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

        // Verificar si se ha seleccionado "Cancelar" en el cuadro de diálogo
        if (opcionSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Ha cancelado la búsqueda de series.");
            return;
        }

        // Realizar la búsqueda basada en la opción seleccionada
        String resultado = "";
        switch (opcionSeleccionada) {
            case "Buscar por nombre":
                String serieBuscada = JOptionPane
                        .showInputDialog("Ingrese el nombre de la serie que desea buscar:");
                for (String serie : series) {
                    if (serie.toLowerCase().contains(serieBuscada.toLowerCase())) {
                        resultado += serie + "\n";
                    }
                }
                break;
            case "Buscar por género":
                String generoBuscado = JOptionPane
                        .showInputDialog("Ingrese el género de la serie que desea buscar:");
                for (String serie : series) {
                    if (serie.toLowerCase().contains(generoBuscado.toLowerCase())) {
                        resultado += serie + "\n";
                    }
                }
                break;
            case "Buscar por año de lanzamiento":
                String anioBuscado = JOptionPane.showInputDialog(
                        "Ingrese el año de lanzamiento de la serie que desea buscar:");
                for (String serie : series) {
                    if (serie.toLowerCase().contains(anioBuscado.toLowerCase())) {
                        resultado += serie + "\n";
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(null,
                        "Opción inválida. Por favor, seleccione una opción válida.");
                return;
        }

        // Mostrar el resultado de la búsqueda
        if (!resultado.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Resultados de la búsqueda:\n" + resultado);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados para la búsqueda.");
        }
    }


    public static void mostrarFavoritos() {
        // Lista de favoritos
        ArrayList<String> favoritos = new ArrayList<>();

        boolean volverAlMenu = false;
        while (!volverAlMenu) {
            // Opciones de administración de favoritos
            String[] opciones = {"Ver favoritos", "Agregar favorito", "Eliminar favorito",
                    "Modificar favorito"};

            // Mostrar las opciones de administración de favoritos en un cuadro de diálogo
            String opcionSeleccionada = (String) JOptionPane.showInputDialog(null,
                    "Seleccione una opción:", "Administración de Favoritos",
                    JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            // Realizar la acción basada en la opción seleccionada
            switch (opcionSeleccionada) {
                case "Ver favoritos":
                    // Mostrar los favoritos en un cuadro de diálogo
                    String mensaje = "Favoritos:\n";
                    for (String favorito : favoritos) {
                        mensaje += favorito + "\n";
                    }
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                case "Agregar favorito":
                    // Solicitar al usuario que ingrese el favorito a agregar
                    String nuevoFavorito =
                            JOptionPane.showInputDialog("Ingrese el favorito que desea agregar:");
                    if (nuevoFavorito != null) {
                        favoritos.add(nuevoFavorito);
                        JOptionPane.showMessageDialog(null,
                                "Se ha agregado el favorito correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Ha cancelado la adición del favorito.");
                    }
                    break;
                case "Eliminar favorito":
                    // Mostrar los favoritos en un cuadro de diálogo y solicitar al usuario que
                    // seleccione el favorito a eliminar
                    String favoritoAEliminar = (String) JOptionPane.showInputDialog(null,
                            "Seleccione el favorito que desea eliminar:", "Eliminar Favorito",
                            JOptionPane.PLAIN_MESSAGE, null, favoritos.toArray(), favoritos.get(0));
                    if (favoritoAEliminar != null) {
                        favoritos.remove(favoritoAEliminar);
                        JOptionPane.showMessageDialog(null,
                                "Se ha eliminado el favorito correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Ha cancelado la eliminación del favorito.");
                    }
                    break;
                case "Modificar favorito":
                    // Mostrar los favoritos en un cuadro de diálogo y solicitar al usuario que
                    // seleccione el favorito a modificar
                    String favoritoAModificar = (String) JOptionPane.showInputDialog(null,
                            "Seleccione el favorito que desea modificar:", "Modificar Favorito",
                            JOptionPane.PLAIN_MESSAGE, null, favoritos.toArray(), favoritos.get(0));
                    if (favoritoAModificar != null) {
                        // Solicitar al usuario que ingrese el nuevo valor para el favorito
                        // seleccionado
                        String nuevoValor = JOptionPane.showInputDialog(
                                "Ingrese el nuevo valor para el favorito seleccionado:");
                        if (nuevoValor != null) {
                            int indice = favoritos.indexOf(favoritoAModificar);
                            favoritos.set(indice, nuevoValor);
                            JOptionPane.showMessageDialog(null,
                                    "Se ha modificado el favorito correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Ha cancelado la modificación del favorito.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Ha cancelado la modificación del favorito.");
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }

            // Verificar si se ha seleccionado "Cancelar" en el cuadro de diálogo
            if (opcionSeleccionada == null) {
                volverAlMenu = true;}
        }
    }

    public static void mostrarHistorial() {
        // Historial de reproducciones
        String[] historial = {"Serie 1 - Episodio 1", "Serie 2 - Episodio 3",
                "Serie 3 - Episodio 2", "Serie 4 - Episodio 5", "Serie 5 - Episodio 4"};

        // Mostrar el historial de reproducciones en un cuadro de diálogo
        String mensaje = "Historial de reproducciones:\n";
        for (String reproduccion : historial) {
            mensaje += reproduccion + "\n";
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }

    public static void mostrarConfiguracion() {
        // Opciones de configuración
        String[] opciones =
                {"Cambiar contraseña", "Cambiar idioma", "Cambiar calidad de reproducción"};

        // Mostrar las opciones de configuración en un cuadro de diálogo
        String mensaje = "Configuración de la cuenta:\n";
        for (String opcion : opciones) {
            mensaje += opcion + "\n";
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }
}