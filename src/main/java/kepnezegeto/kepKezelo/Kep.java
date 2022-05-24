package kepnezegeto.kepKezelo;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;


public class Kep{
    private Image kep;
    private ImageView preview;
    public Stack<Image> getHistory() {
        return history;
    }

    private Stack<Image> history;
    private String url;

    public Kep(Image kep){
        this.kep = kep;
        url = kep.getUrl();
        this.history = new Stack<>();
        preview = new ImageView(kep);
        preview.setPreserveRatio(true);
        preview.setFitWidth(200);
        preview.setFitHeight(100);
    }
    public String getExtension(){
        String[] tmp =  url.split("\\.");
        return (tmp.length > 1) ? tmp[tmp.length-1] : "";
    }

    public String getUrl() {
        return url;
    }

    public Image getImage(){
        return kep;
    }
    public void setImage(Image img){

        kep = img;
        preview.setImage(img);
    }

    public ImageView getPreview() {
        return preview;
    }
}
