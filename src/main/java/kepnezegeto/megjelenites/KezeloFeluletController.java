package kepnezegeto.megjelenites;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import kepnezegeto.filterek.Grayscale;
import kepnezegeto.kepek.Jpg;
import kepnezegeto.kepek.Kep;

public class KezeloFeluletController {
    @FXML
    public Slider forgatasMertek;
    public Label forgatasLabel;

    @FXML
    protected TextField eleresiUt;

    @FXML
    protected ImageView kepMegjelenit;

    @FXML
    protected void valami() {
        System.out.println("hello");
    }
    public void initialize() {

        forgatasMertek.valueProperty().addListener((observable, oldValue, newValue) -> {
            int csuszkaErtek = (int)Math.round((Double)newValue);
            kepMegjelenit.setRotate(csuszkaErtek);
            forgatasLabel.setText(String.valueOf(csuszkaErtek));

        });

    }
    @FXML
    protected void fajlMegnyit(){
        if(!eleresiUt.getText().isBlank()) {
            Image megnyitottKep = (new Jpg(eleresiUt.getText()).createImage());
            megnyitottKep = Grayscale.alkalmazFilter(megnyitottKep);
            kepMegjelenit.setImage(megnyitottKep);

        }

    }


}