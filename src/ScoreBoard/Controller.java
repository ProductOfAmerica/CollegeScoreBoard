package ScoreBoard;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.util.Optional;

import static ScoreBoard.Main.*;

public class Controller {
    private LinkedList<ScoreMonitor> scoreMonitors;

    public Controller() {
        scoreMonitors = new LinkedList<>();
        scoreMonitors.add(new ScoreMonitor(staticAnderson));
        scoreMonitors.add(new ScoreMonitor(staticBrooks));
        scoreMonitors.add(new ScoreMonitor(staticBuck));
        scoreMonitors.add(new ScoreMonitor(staticFerg));
        scoreMonitors.add(new ScoreMonitor(staticHoward));
        scoreMonitors.add(new ScoreMonitor(staticJoe));
        scoreMonitors.add(new ScoreMonitor(staticLowrey));
        scoreMonitors.add(new ScoreMonitor(staticRackham));
        scoreMonitors.add(new ScoreMonitor(staticSyl));
    }

    @FXML
    private ComboBox houses;

    @FXML
    private void plusButtonAction() {
        if (!validateHouseSelected())
            return;
        scoreMonitors.get(getComboBoxIndex()).addScore();
    }

    @FXML
    private void minusButtonAction() {
        if (!validateHouseSelected())
            return;
        scoreMonitors.get(getComboBoxIndex()).subScore();
    }

    @FXML
    private void resetAllButtonAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("2016 House Olympics");
        alert.setHeaderText("Are you sure you want to reset all houses?");
        alert.setContentText("Please choose:");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            scoreMonitors.forEach(ScoreMonitor::resetScore);
        }
    }

    private boolean validateHouseSelected() {
        if (getComboBoxIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("2016 House Olympics - ERROR");
            alert.setHeaderText(null);
            alert.setContentText("You must select a house before using this feature.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private int getComboBoxIndex() {
        String houseName = (String) houses.getValue();
        if (houseName != null)
            switch (houseName) {
                case "Anderson":
                    return 0;
                case "Brooks":
                    return 1;
                case "Buck":
                    return 2;
                case "Ferg":
                    return 3;
                case "Howard":
                    return 4;
                case "Joe":
                    return 5;
                case "Lowrey":
                    return 6;
                case "Rackham":
                    return 7;
                case "Syl":
                    return 8;
            }
        return -1;
    }

    private class ScoreMonitor {
        private int score = 0;
        private Text text;

        ScoreMonitor(Text text) {
            this.text = text;
        }

        void resetScore() {
            text.setText(String.valueOf(this.score = 0));
        }

        void addScore() {
            text.setText(String.valueOf(this.score += 1));
        }

        void subScore() {
            text.setText(String.valueOf(this.score -= 1));
        }
    }
}