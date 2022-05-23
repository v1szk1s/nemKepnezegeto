package kepnezegeto.kepKezelo;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import kepnezegeto.kepek.Kep;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Kepkezelo {
    private ArrayList<Image> kepek;

    public Kepkezelo(){
        kepek = new ArrayList<>();
    }

    public ArrayList<Image> getKepek(){
        return kepek;
    }

    public String getExtension(String url){
        String[] tmp =  url.split("\\.");
        return (tmp.length > 1) ? "." + tmp[tmp.length-1] : "";
    }


    public void save(int index, File file){
        try{
        //BufferedImage imagem;
        //ImageIO.write(SwingFXUtils.fromFXImage(kepek.get(index), null), "png", new File(kepek.get(index).getUrl()));
            BufferedImage bImage = SwingFXUtils.fromFXImage(kepek.get(index), null);
            file = new File(file.getAbsolutePath() + getExtension(kepek.get(index).getUrl()));
            ImageIO.write(bImage,getExtension(kepek.get(index).getUrl()), file);
        }catch(Exception err){
            Alert hiba = new Alert(AlertType.ERROR);
            hiba.setContentText("Nem sikerült elmenteni, valamiért." + err);
            hiba.showAndWait();

        }
    }

}
