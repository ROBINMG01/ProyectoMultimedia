package co.edu.uptc.controllerFx;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class VideoController {

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

    private MediaPlayer mediaPlayer;
    private String videoPath; // Ruta de tu archivo de video

    @FXML
    public void initialize() {
        // Establecer la ruta del video aquí (reemplazar con la ruta real de tu archivo
        // de video)
        videoPath = "src/main/java/co/edu/uptc/image/1124.mp4"; // Ejemplo de ruta

        try {
            // Crear un objeto Media
            Media media = new Media(new File(videoPath).toURI().toString());

            // Crear un MediaPlayer
            mediaPlayer = new MediaPlayer(media);

            // Vincular el media player al MediaView
            mediaView.setMediaPlayer(mediaPlayer);
        } catch (Exception ex) {
            Logger.getLogger(VideoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al cargar el video: " + ex.getMessage());
        }
    }
    @FXML
    public void handleButtonPlay(ActionEvent event) {
        MediaPlayer.Status status = mediaPlayer.getStatus();
        
        if (status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.READY || status == MediaPlayer.Status.STOPPED) {
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
