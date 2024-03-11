package co.edu.uptc.controllerFx;

import java.io.IOException;

import co.edu.uptc.model.Movie;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ButtonCell extends TableCell<Movie, Boolean> {
    private final AdminControllerFx acf;
    private final EditMovieController emc;
    private final Movie movie;
    private final HBox hbox;
    private final Button firstButton;
    private final Button secondButton;

    public ButtonCell() {
        this.acf = new AdminControllerFx();
        this.emc = new EditMovieController();
        this.movie = new Movie();
        this.hbox = new HBox();
        this.firstButton = new Button("Botón 1");
        this.secondButton = new Button("Botón 2");

        final ImageView imagenBotton = new ImageView(new Image("/co/edu/uptc/image/Movie.jpeg"));
        final ImageView imagenBotton2 = new ImageView(new Image("/co/edu/uptc/image/Serie.jpeg"));

        imagenBotton.setFitWidth(15);
        imagenBotton.setFitHeight(15);

        imagenBotton2.setFitWidth(15);
        imagenBotton2.setFitHeight(15);

        firstButton.setPrefWidth(5);
        firstButton.setPrefHeight(3);
        firstButton.setGraphic(imagenBotton);

        secondButton.setPrefWidth(5);
        secondButton.setPrefHeight(3);
        secondButton.setGraphic(imagenBotton2);

        firstButton.setOnAction(event -> {
            
            Movie movie = getTableView().getItems().get(getIndex());
            //emc.active(movie);
            //emc.initialize(movie);
            try {
                if (movie != null) {
                    Stage myStage = (Stage) this.firstButton.getScene().getWindow();
                            myStage.close();
                    acf.fileEditMovie(movie);
                }
            } catch (IOException e) {
                e.printStackTrace(); // O manejo de la excepción según sea necesario
            }
        });

        secondButton.setOnAction(event -> {
            Movie movie = getTableView().getItems().get(getIndex());
            //emc.initialize(movie);
            if (movie != null) {
                acf.deleteMovie(movie);
            }
        });

        hbox.getChildren().addAll(firstButton, secondButton);
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
