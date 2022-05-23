package kepnezegeto.filterek;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class Grayscale extends Filter{


    @Override
    protected int pixelManipulacio(int alpha, int red, int green, int blue) {
        int grayLevel = (int) (0.2162 * red + 0.7152 * green + 0.0722 * blue);
        return (alpha << 24) + (grayLevel << 16) + (grayLevel << 8) + grayLevel;
    }

    @Override
    public String getNev() {
        return "Grayscale";
    }
}
