package kepnezegeto.tranformaciok;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import kepnezegeto.kepKezelo.Kep;
import javafx.embed.swing.SwingFXUtils;


import java.io.File;

public class Tukrozes implements Transzformacio{
    @Override
    public String getNev() {
        return "Tükrözés";
    }

    @Override
    public Image transzformal(Kep kep) {
        ImageView imageView = new ImageView(kep.getImage());
        imageView.setTranslateZ(imageView.getBoundsInLocal().getWidth() / 2.0);
        imageView.setRotationAxis(Rotate.Y_AXIS);
        imageView.setRotate(180);
        return imageView.snapshot(null, null);
        //TODO mentés
        // ImageIO.write(imagem, "png", new File(kep.getUrl()));
    }

    @Override
    public Node getUI(Kep kep, EventHandler<ActionEvent> eh) {
        return null;
    }


}
