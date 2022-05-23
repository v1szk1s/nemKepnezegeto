package kepnezegeto.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class kepnezegetoController {
    int counter = 0;
    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    void buttonAction(ActionEvent event) {
        label.setText("Counter: " + counter);
        counter++;
    }

}
