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

        return SwingFXUtils.toFXImage(imagem,null);
    }

    @Override
    public Node getUI(Kep kep, EventHandler<ActionEvent> eh) {
        Label szelessegLabel = new Label("Szélesség");
        Label magassagLabel = new Label("Magasság");
        TextField szelessegTextField = new TextField(String.valueOf((int)kep.getImage().getWidth()));
        TextField magassagTextField = new TextField(String.valueOf((int)kep.getImage().getHeight()));
        Button megseButton = new Button("Mégse");
        Button transzformalButton = new Button("Transzformál");
        GridPane root = new GridPane();

        megseButton.setOnAction(eh);
        transzformalButton.setOnAction(e->{
            try {
                szelesseg = Integer.parseInt(szelessegTextField.getText());
                magassag = Integer.parseInt(magassagTextField.getText());
                kep.setImage(transzformal(kep));
                megseButton.fireEvent(e);
            }catch (Exception ex){
                //TODO lekezel
                System.out.println("hello");
            }

        });
        szelessegTextField.setOnKeyTyped(ev ->{
            try {
                ;
                double temp = kep.getImage().getWidth() / Integer.parseInt(szelessegTextField.getText());
                magassagTextField.setText(String.valueOf(Math.round((kep.getImage().getHeight() / temp))));
            }
            catch (Exception e){
                //TODO lekezelni
                System.out.println("hello");
            }

        });
        magassagTextField.setOnKeyTyped(ev -> {
            try {

                double temp = kep.getImage().getHeight() / Integer.parseInt(magassagTextField.getText());
                szelessegTextField.setText(String.valueOf(Math.round((kep.getImage().getWidth() / temp))));
            }
            catch (Exception e){
                //TODO lekezelni
                System.out.println("hello");
            }
        });



        root.add(szelessegLabel,0,0);
        root.add(szelessegTextField, 1, 0);
        root.add(magassagLabel, 0, 1);
        root.add(magassagTextField, 1, 1);
        root.add(megseButton, 0,2);
        root.add(transzformalButton, 1, 2);
        return root;
    }
}
