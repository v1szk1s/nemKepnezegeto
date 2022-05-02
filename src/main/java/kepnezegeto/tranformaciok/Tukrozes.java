package kepnezegeto.tranformaciok;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import kepnezegeto.kepek.Kep;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Tukrozes implements Transzformacio{
    @Override
    public String getNev() {
        return "Tükrözés";
    }
    @Override
    public void transzformal(File kep) {
        try {
            BufferedImage imagem = ImageIO.read(kep);
            Image image = SwingFXUtils.toFXImage(imagem, null);
            ImageView imageView = new ImageView(image);
            imageView.setTranslateZ(imageView.getBoundsInLocal().getWidth() / 2.0);
            imageView.setRotationAxis(Rotate.Y_AXIS);
            imageView.setRotate(180);
            imagem = SwingFXUtils.fromFXImage(imageView.snapshot(null, null), null);
            ImageIO.write(imagem, "png", kep);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
