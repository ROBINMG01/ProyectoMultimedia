package co.edu.uptc.controllerFx;

import java.io.IOException;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ButtonCellSerie extends TableCell<Serie, Boolean> {
    private final AdminControllerSerieFx acsf;
    private final EditSerieController esc;
    private final Serie serie;
    private final HBox hbox;
    private final Button firstButton;
    private final Button secondButton;

    public ButtonCellSerie() {
        this.acsf = new AdminControllerSerieFx();
        this.esc = new EditSerieController();
        this.serie = new Serie();
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
            
            Serie serie = getTableView().getItems().get(getIndex());
            //emc.active(movie);
            //emc.initialize(movie);
            try {
                if (serie != null) {
                    Stage myStage = (Stage) this.firstButton.getScene().getWindow();
                            myStage.close();
                    acsf.fileEditSerie(serie);
                }
            } catch (IOException e) {
                e.printStackTrace(); // O manejo de la excepción según sea necesario
            }
        });

        secondButton.setOnAction(event -> {
            Serie serie = getTableView().getItems().get(getIndex());
            //emc.initialize(movie);
            if (serie != null) {
                acsf.deleteMovie(serie);
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
