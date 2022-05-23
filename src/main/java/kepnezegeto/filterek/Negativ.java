package kepnezegeto.filterek;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class Negativ extends Filter{



    @Override
    protected int pixelManipulacio(int alpha, int red, int green, int blue) {

        return (alpha << 24) + ((0xff - red) << 16) + ((0xff - green) << 8) + (0xff - blue);
    }

    public String getNev() {
        return "Negatív";
    }
}
