package kepnezegeto.kepKezelo;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import kepnezegeto.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Kepkezelo {
    private ArrayList<Kep> kepek;

    public ArrayList<ImageView> getPreviewek(){
        ArrayList<ImageView>al = new ArrayList<>();
        for(Kep kep: kepek){
            al.add(kep.getPreview());
        }
        return al;
    }

    public Kepkezelo(){
        kepek = new ArrayList<>();
    }

    public ArrayList<Kep> getKepek(){

        return kepek;
    }

    public Image getKep(int index){
        return kepek.get(index).getImage();
    }

    public void set(int index, Image img){
        kepek.get(index).setImage(img);

    }

    public void add(Image img){
        Kep kep = new Kep(img);
        kepek.add(kep);
        HBox.setMargin(kep.getPreview(), new Insets(0, 10, 0, 10));
        
        kep.getPreview().setOnMouseClicked(e -> {

            Main.setImageIndex(getPreviewek().indexOf(e.getTarget()));

        });
        

    }



    public void save(int index, File file){
        try{

        //BufferedImage imagem;
        //ImageIO.write(SwingFXUtils.fromFXImage(kepek.get(index), null), "png", new File(kepek.get(index).getUrl()));
            ArrayList<String>exts = new ArrayList<>(List.of(new String[]{"png", "jpg", "gif"}));
            String[]fileParts = file.getName().split("\\.");
            String ext;
            if(!exts.contains(fileParts[fileParts.length-1])){
                file = new File(file.getAbsolutePath() + "." + kepek.get(index).getExtension());
                ext = kepek.get(index).getExtension();
            }else{
                ext = fileParts[fileParts.length-1];
            }
            BufferedImage bImage = SwingFXUtils.fromFXImage(kepek.get(index).getImage(), null);
            ImageIO.write(bImage, ext, file);
        }catch(Exception err){
            Alert hiba = new Alert(AlertType.ERROR);
            hiba.setContentText("Nem sikerült elmenteni, valamiért." + err);
            hiba.showAndWait();

        }
    }

}
