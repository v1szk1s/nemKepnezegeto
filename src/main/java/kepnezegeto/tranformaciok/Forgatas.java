package kepnezegeto.tranformaciok;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kepnezegeto.kepek.Kep;
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
    public void transzformal(File kep) {

        try {
            BufferedImage imagem = ImageIO.read(kep);
            Image image = SwingFXUtils.toFXImage(imagem, null);
            ImageView imageView = new ImageView(image);
            imageView.setRotate(90);
            imagem = SwingFXUtils.fromFXImage(imageView.snapshot(null, null), null);
            ImageIO.write(imagem, "png", kep);
        }catch (Exception e){
            System.out.println(e.toString());
        }

        //ImageView iv = new ImageView(kep);

    }
}
