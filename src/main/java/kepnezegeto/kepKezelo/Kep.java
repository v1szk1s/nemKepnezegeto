package kepnezegeto.kepKezelo;
import javafx.scene.image.Image;

import java.io.File;


public class Kep{
    private Image kep;
    private String url;

    public Kep(Image kep){
        this.kep = kep;
        url = kep.getUrl();
    }

    public Kep(Image kep, String url){
        this.kep = kep;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public Image getImage(){
        return kep;
    }
    public void setImage(Image img){
        kep = img;
    }
}
