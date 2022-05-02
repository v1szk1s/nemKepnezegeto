package kepnezegeto.kepek;
import javafx.scene.image.Image;

import java.io.File;


public class Kep{
    private File kep;

    public Kep(File kep){
        this.kep = kep;
    }
    public String getPath(){
        return kep.getAbsolutePath();
    }
    public File getFile(){
        return kep;
    }
    public Image getAsImage(){
        return new Image(kep.toURI().toString(), 600, 0, true, false);
    }

}
