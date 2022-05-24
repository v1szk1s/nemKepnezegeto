package kepnezegeto.tranformaciok;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import kepnezegeto.kepKezelo.Kep;
import org.w3c.dom.Text;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Atmeretezes implements Transzformacio{
    private int szelesseg;
    private int magassag;

    @Override
    public String getNev() {
        return "Átméretezés";
    }

    @Override
    public Image transzformal(Kep kep) {

        BufferedImage imagem = new BufferedImage(szelesseg, magassag, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = imagem.createGraphics();
        graphics2D.drawImage(SwingFXUtils.fromFXImage(kep.getImage(), null), 0, 0, szelesseg, magassag, null);
        graphics2D.dispose();
        try {
            ImageIO.write(imagem, kep.getExtension(), new File(kep.getUrl()));
        }
        catch (Exception e){
            //todo
            System.out.println("Hiba a kep irasakor");
        }
        return SwingFXUtils.toFXImage(imagem,null);
    }

    @Override
    public Node getUI(Kep kep) {
        return null;

    }
}
