package co.edu.uptc.controllerFx;

import java.io.IOException;

import co.edu.uptc.model.Movie;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ButtonCell extends TableCell<Movie, Boolean> {
    private AdminControllerFx acf = new AdminControllerFx();
    private EditMovieController emc = new EditMovieController();
    private Movie movie = new Movie();
    private final HBox hbox = new HBox();
    private final Button firstButton = new Button("Botón 1");
    private final Button secondButton = new Button("Botón 2");

    final ImageView imagenBotton = new ImageView(
            new Image("file:" + "src\\main\\java\\co\\edu\\uptc\\image\\Movie.jpeg"));
    final ImageView imagenBotton2 = new ImageView(
            new Image("file:" + "src\\main\\java\\co\\edu\\uptc\\image\\Serie.jpeg"));

    public ButtonCell() {
        hbox.getChildren().addAll(firstButton, secondButton);
        imagenBotton.setFitWidth(15);// Cambia el ancho de la imagen
        imagenBotton.setFitHeight(15);// Cambia el alto de la imagen

        imagenBotton2.setFitWidth(15);// Cambia el ancho de la imagen
        imagenBotton2.setFitHeight(15);// Cambia el alto de la imagen

        // Establecer el tamaño del botón
        firstButton.setPrefWidth(5);
        firstButton.setPrefHeight(3);

        // Establecer la imagen en el botón
        firstButton.setGraphic(imagenBotton);

        // Establecer el tamaño del botón
        secondButton.setPrefWidth(5);
        secondButton.setPrefHeight(3);

        // Establecer la imagen en el botón
        secondButton.setGraphic(imagenBotton2);

        firstButton.setOnAction(event -> {
            try {
                Movie movie = getTableView().getItems().get(getIndex());
                acf.FileEditMovie(movie);
            } catch (IOException e) {
                e.printStackTrace(); // O manejo de la excepción según sea necesario
            }
        });

        secondButton.setOnAction(event -> {
            // Lógica para el segundo botón
            System.out.println("Botón 2 clickeado");
        });
    }

    @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(hbox);
        }
    }
}
