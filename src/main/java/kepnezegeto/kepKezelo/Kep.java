package kepnezegeto.kepKezelo;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Stack;


public class Kep{
    private Image kep;

    public Stack<Image> getHistory() {
        return history;
    }

    private Stack<Image> history;
    private String url;

    public Kep(Image kep){
        this.kep = kep;
        url = kep.getUrl();
        this.history = new Stack<>();
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
    }
}
