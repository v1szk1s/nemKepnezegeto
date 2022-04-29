package kepnezegeto.megjelenites;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;


public class KepszerkesztoController {


    public ImageView kepMegjelenit;
    public Slider forgatasMertek;
    public Label forgatasLabel;
    public void initialize() {
        kepMegjelenit.setImage(new Image("C:/Users/Csaba/Desktop/prog1/src/39.jpg"));
        forgatasMertek.valueProperty().addListener((observable, oldValue, newValue) -> {
            int csuszkaErtek = (int)Math.round((Double)newValue);
            kepMegjelenit.setRotate(csuszkaErtek);
            forgatasLabel.setText(String.valueOf(csuszkaErtek));

        });

    }
    public void valami(InputMethodEvent inputMethodEvent) {
    }
}
