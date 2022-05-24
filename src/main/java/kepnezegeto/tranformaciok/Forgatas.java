package kepnezegeto.tranformaciok;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kepnezegeto.kepKezelo.Kep;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Forgatas implements Transzformacio{
    @Override
    public String getNev() {
        return "Forgatás";
    }

    @Override
    public Image transzformal(Kep kep) {
        ImageView imageView = new ImageView(kep.getImage());
        imageView.setRotate(90);
        return imageView.snapshot(null, null);


        //ImageView iv = new ImageView(kep);
    }

    @Override
    public Node getUI(Kep kep, EventHandler<ActionEvent> eh) {
        return null;
    }


}
