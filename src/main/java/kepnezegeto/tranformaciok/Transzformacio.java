package kepnezegeto.tranformaciok;

import javafx.scene.Node;
import javafx.scene.image.Image;
import kepnezegeto.kepKezelo.Kep;

import java.io.File;

public interface Transzformacio {
    String getNev();
    public Image transzformal(Kep kep);

    Node getUI(Kep kep);
}
