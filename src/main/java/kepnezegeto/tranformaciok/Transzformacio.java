package kepnezegeto.tranformaciok;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import kepnezegeto.kepKezelo.Kep;

import java.awt.event.ActionListener;
import java.io.File;

public interface Transzformacio {
    String getNev();
    public Image transzformal(Kep kep);

    Node getUI(Kep kep, EventHandler<ActionEvent> eh);


}
