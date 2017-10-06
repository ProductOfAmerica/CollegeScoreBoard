package ScoreBoard;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {
    @FXML
    private Text brooks;
    @FXML
    private Text buck;
    @FXML
    private Text howard;
    @FXML
    private Text joe;
    @FXML
    private Text anderson;
    @FXML
    private Text lowrey;
    @FXML
    private Text ferg;
    @FXML
    private Text syl;
    @FXML
    private Text rakcham;

    static Text staticBrooks, staticBuck, staticHoward, staticJoe, staticAnderson, staticLowrey, staticFerg, staticSyl, staticRackham;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        staticBrooks = brooks;
        staticBuck = buck;
        staticHoward = howard;
        staticJoe = joe;
        staticAnderson = anderson;
        staticLowrey = lowrey;
        staticFerg = ferg;
        staticSyl = syl;
        staticRackham = rakcham;
    }

    @Override
    public void start(Stage displayStage) throws Exception {
        Stage controllerStage = new Stage();
        validateDate();

        /*
          Display stage
         */
        displayStage.setTitle("2016 House Olympics - Credit: Lee Tarnow");
        Scene dispScene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Main.fxml")), displayStage.getWidth(), displayStage.getHeight());
        dispScene.setOnKeyPressed(ke -> {
            if (ke.getCode() == KeyCode.F11) {
                if (!displayStage.isFullScreen())
                    displayStage.setFullScreen(true);
            }
        });
        displayStage.setOnCloseRequest(we -> controllerStage.close());
        displayStage.setScene(dispScene);
        displayStage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.F10));
        displayStage.show();
        displayStage.toFront();


        /*
          Controller stage
         */
        Scene cntrlScene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Controller.fxml")), controllerStage.getWidth(), controllerStage.getHeight());
        controllerStage.setOnCloseRequest(we -> displayStage.close());
        controllerStage.setTitle("Controller - Credit: Lee Tarnow");
        controllerStage.setScene(cntrlScene);
        controllerStage.setResizable(false);
        controllerStage.show();
    }

    private void validateDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        if (sdf.parse("11/2016").compareTo(sdf.parse(sdf.format(new Date()))) != 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("2016 House Olympics - ERROR");
            alert.setHeaderText(null);
            alert.setContentText("This program is no longer usable.");
            alert.showAndWait();

            System.exit(0);
        }
    }
}