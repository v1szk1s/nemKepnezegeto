package kepnezegeto.tranformaciok;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    public Node getUI(Kep kep, EventHandler<ActionEvent> eh) {
        Label szelessegLabel = new Label("Szélesség");
        Label magassagLabel = new Label("Magasság");
        TextField szelessegTextField = new TextField();
        TextField magassagTextField = new TextField();
        Button megseButton = new Button("Mégse");
        Button transzformalButton = new Button("Transzformál");
        GridPane root = new GridPane();

        megseButton.setOnAction(eh);

        root.add(szelessegLabel,0,0);
        root.add(szelessegTextField, 1, 0);
        root.add(magassagLabel, 0, 1);
        root.add(magassagTextField, 1, 1);
        root.add(megseButton, 0,2);
        root.add(transzformalButton, 1, 2);
        return root;
    }
}
