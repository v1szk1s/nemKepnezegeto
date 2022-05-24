package kepnezegeto.tranformaciok;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import kepnezegeto.kepKezelo.Kep;

import java.io.File;

public class Kimetszes implements Transzformacio{
    @Override
    public String getNev() {
        return "Metszés";
    }

    @Override
    public Image transzformal(Kep kep) {
        return null;
    }

    @Override
    public Node getUI(Kep kep, EventHandler<ActionEvent> eh) {
        return null;
    }

}
