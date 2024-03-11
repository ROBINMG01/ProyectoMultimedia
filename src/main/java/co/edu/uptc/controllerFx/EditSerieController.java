package co.edu.uptc.controllerFx;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.SerieRepository;
import co.edu.uptc.viewFx.AdminViewFx;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditSerieController {

    private AdminController ac;
    private AdminViewFx avf;
    private Serie serie;
    private ArrayList<String> listAuthors = new ArrayList<>();
    private ArrayList<String> listActors = new ArrayList<>();

    @FXML
    private TextField serieName;

    @FXML
    private TextField serieDescription;

    @FXML
    private TextField serieGender;

    @FXML
    private TextField serieDuration;

    @FXML
    private TextField serieYear;

    @FXML
    private TextField serieAuthor;

    @FXML
    private TextField serieActor;

    @FXML
    private Button saveSerieButton;

    @FXML
    private Button serieButton;

    @FXML
    private Button movieButton;

    @FXML
    private Button authorButton;

    @FXML
    private Button newAuthorButton;

    @FXML
    private Button actorButton;

    @FXML
    private Button newActorButton;

    //@FXML
    //private JCheckBox menuButton;
    @FXML
    private MenuButton menuButton;

    @FXML
    private TableView<Serie> tableView;

    @FXML
    private TableColumn<Serie, String> nameColumn;

    @FXML
    private TableColumn<Serie, String> genderColumn;

    @FXML
    private TableColumn<Serie, Integer> durationColumn;

    @FXML
    private TableColumn<Serie, String> yearColumn;

    private ArrayList<String> listMenu = new ArrayList<>();

    public EditSerieController() {
        this.ac = new AdminController();
        this.avf = new AdminViewFx();
        this.durationColumn = new TableColumn<>();
        this.genderColumn = new TableColumn<>();
        this.nameColumn = new TableColumn<>();
        this.yearColumn = new TableColumn<>();
        this.tableView = new TableView<>();
        this.serieDescription = new TextField();
        this.serieName = new TextField();
        this.serieGender = new TextField();
        this.serieYear = new TextField();
        this.serieDuration = new TextField();
    }

    public void help(Serie serie) {
        String name = serie.getName();

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uptc/Fxml/EditSerie.fxml"));
            Parent root = loader.load();

            serieName = (TextField) loader.getNamespace().get("serieName");

            serieName.setText(serie.getName());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Stage myStage = (Stage) this.register.getScene().getWindow();
        // myStage.close();

    }

    public void active(Serie serie) {
        if (serie != null) {
            serieName.setText(serie.getName());
            serieGender.setText(serie.getGender());
            serieDuration.setText(String.valueOf(serie.getDuration()));
            serieYear.setText(String.valueOf(serie.getYear()));
        }
        // serieDescription.setText("HOLAAAAAAAAA");
    }

    public void initialize(Serie serie) {
        ac.getListSeries().addAll(ac.loadSerie("Series"));

        //menuButton.getItems().addAll(opcion1, opcion2, opcion3);
        // help(serie);

        // if (serie != null) {
        // serieName.setText(serie.getName());
        // serieGender.setText(serie.getGender());
        // serieDuration.setText(String.valueOf(serie.getDuration()));
        // serieYear.setText(String.valueOf(serie.getYear()));
        // }

        if (SerieRepository.getInstance().getSeries().isEmpty()) {
            SerieRepository.getInstance().getSeries().addAll(ac.getListSeries());
            tableView.setItems(SerieRepository.getInstance().getSeries());
        }

        // nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        // genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        // durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        // yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        // TableColumn<serie, Boolean> editColumn = new TableColumn<>("Edit");
        // editColumn.setCellFactory(tc -> new ButtonCell());
        // tableView.getColumns().add(editColumn);

        // tableView.setItems(serieRepository.getInstance().getseries());
    }

    @FXML
    private void showFormCreateseries() throws IOException {
        AdminViewFx.setRoot("Listseries");
    }

    @FXML
    private boolean saveserie() throws IOException {


        /*if (ac.addSerie(serieName.getText(), serieDescription.getText(), serieDurations, listAuthors, serieGender.getText(),
        listActors, serieNameSeason.getText(), serieDescriptionSeason.getText(), serieNameChapter.getText(), serieDurationInt, serieYearInt)) {
            serieRepository.getInstance().addserie(ac.getListseries().get(ac.getListseries().size() - 1));
        }*/

        // Clean fields on success
        clearInputFields();

        AdminViewFx.setRoot("ListseriesAdmin");
        return true;
    }

    @FXML
    private void showFormCreateserie() throws IOException {
        AdminViewFx.setRoot("ListseriesAdmin");
    }

    @FXML
    private void showSerie() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uptc/Fxml/listSeries.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage myStage = (Stage) this.serieButton.getScene().getWindow();
        myStage.close();
        myStage.close();
        // AdminViewFx.setRoot("listSeries");
    }

    @FXML
    private void showMovie() throws IOException {
        // avf.loadFXML("ListseriesAdmin");
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uptc/Fxml/ListMoviesAdmin.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage myStage = (Stage) this.serieButton.getScene().getWindow();
        myStage.close();
        // AdminViewFx.setRoot("ListseriesAdmin");
    }

    @FXML
    private void showEditserie() throws IOException {
        AdminViewFx.setRoot("Editserie");
    }

    @FXML
    public void newAuthor() {
        listAuthors.add(serieAuthor.getText());
        serieAuthor.clear();
    }

    @FXML
    public void newActor() {
        listActors.add(serieActor.getText());
        serieActor.clear();
    }

    private void clearInputFields() {
        serieName.clear();
        serieGender.clear();
        serieDuration.clear();
        serieYear.clear();
        serieAuthor.clear();
        serieActor.clear();
    }
}
