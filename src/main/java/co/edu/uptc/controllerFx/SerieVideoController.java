package co.edu.uptc.controllerFx;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.MouseEvent;

public class SerieVideoController {

    @FXML
    private MediaView mediaView;

    @FXML
    private Button buttonPlay;

    @FXML
    private Button buttonStop;

    @FXML
    private Button buttonAdelantar;

    @FXML
    private Button buttonRetroceder;
    @FXML
    private Button buttonBack;

    private MediaPlayer mediaPlayer;
    private String videoPath; // Ruta de tu archivo de video

    @FXML
    public void initialize() {
        Timeline hideButtonsTimeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
            buttonPlay.setVisible(false);
            buttonStop.setVisible(false);
            buttonAdelantar.setVisible(false);
            buttonRetroceder.setVisible(false);
            buttonBack.setVisible(false);
        }));
        hideButtonsTimeline.setCycleCount(1);

        mediaView.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            buttonPlay.setVisible(true);
            buttonStop.setVisible(true);
            buttonAdelantar.setVisible(true);
            buttonRetroceder.setVisible(true);
            buttonBack.setVisible(true);
            hideButtonsTimeline.playFromStart();
        });
    }

    public void playVideo(String videoPath) {

        this.videoPath = videoPath;

        try {
            // Crear un objeto Media
            Media media = new Media(new File(videoPath).toURI().toString());

            // Crear un MediaPlayer
            mediaPlayer = new MediaPlayer(media);

            // Vincular el media player al MediaView
            mediaView.setMediaPlayer(mediaPlayer);

            // Reproducir el video automáticamente al cargarlo
            mediaPlayer.play();
            buttonPlay.setText("Pause");

            // Añadir un Listener para detectar el final del video y detener la reproducción
            mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.stop();
                buttonPlay.setText("Play");
            });
        } catch (Exception ex) {
            Logger.getLogger(SerieVideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void handleButtonBack(ActionEvent event) {
        abrirVista1();
    }

    private void abrirVista1() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Fxml/SerieCatalogView.fxml"));

            MovieCatalogController movieCatalogController = new MovieCatalogController();
            fxmlLoader.setController(movieCatalogController);

            Parent movieCatalogView = fxmlLoader.load();

            // Crear una nueva ventana para la vista del catálogo de películas
            Stage stage = new Stage();
            stage.setTitle("Catálogo de películas");
            stage.setScene(new Scene(movieCatalogView));
            stage.show();

            Stage myStage = (Stage) this.buttonBack.getScene().getWindow();

            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleButtonPlay(ActionEvent event) {
        MediaPlayer.Status status = mediaPlayer.getStatus();

        if (status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.READY
                || status == MediaPlayer.Status.STOPPED) {
            mediaPlayer.play();
            buttonPlay.setText("Pause");
        } else {
            mediaPlayer.pause();
            buttonPlay.setText("Play");
        }
    }

    @FXML
    public void handleButtonStop(ActionEvent event) {
        mediaPlayer.stop();
        buttonPlay.setText("Play");
    }

    @FXML
    public void handleButtonAdelantar(ActionEvent event) {
        double currentTime = mediaPlayer.getCurrentTime().toSeconds();
        mediaPlayer.seek(Duration.seconds(currentTime + 5));
    }

    @FXML
    public void handleButtonRetroceder(ActionEvent event) {
        double currentTime = mediaPlayer.getCurrentTime().toSeconds();
        mediaPlayer.seek(Duration.seconds(currentTime - 5));
    }

    // Método para cerrar el MediaPlayer al cerrar la aplicación
    public void setStage(Stage stage) {
        stage.setOnCloseRequest((WindowEvent e) -> {
            mediaPlayer.dispose();
        });
    }
}