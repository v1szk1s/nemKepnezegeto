package kepnezegeto.tranformaciok;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import kepnezegeto.kepek.Kep;
import javafx.embed.swing.SwingFXUtils;


import java.io.File;

public class Tukrozes implements Transzformacio{
    @Override
    public String getNev() {
        return "Tükrözés";
    }
    @Override
    public Image transzformal(Image kep) {
        
        ImageView imageView = new ImageView(kep);
        imageView.setTranslateZ(imageView.getBoundsInLocal().getWidth() / 2.0);
        imageView.setRotationAxis(Rotate.Y_AXIS);
        imageView.setRotate(180);
        return imageView.snapshot(null, null);
        //TODO mentés
        // ImageIO.write(imagem, "png", new File(kep.getUrl()));

    }
}
