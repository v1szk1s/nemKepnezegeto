package kepnezegeto.filterek;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import kepnezegeto.kepKezelo.Kep;

public class Grayscale {

    public static Image alkalmazFilter(Image kep) {
        PixelReader pixelReader = kep.getPixelReader();
        int szelesseg = (int) kep.getWidth();
        int magassag = (int) kep.getHeight();

        WritableImage grayImage = new WritableImage(szelesseg, magassag);

        for (int y = 0; y < magassag; y++) {
            for (int x = 0; x < szelesseg; x++) {
                int pixel = pixelReader.getArgb(x, y);

                int alpha = ((pixel >> 24) & 0xff);
                int red = ((pixel >> 16) & 0xff);
                int green = ((pixel >> 8) & 0xff);
                int blue = (pixel & 0xff);

                int grayLevel = (int) (0.2162 * red + 0.7152 * green + 0.0722 * blue);
                int gray = (alpha << 24) + (grayLevel << 16) + (grayLevel << 8) + grayLevel;

                grayImage.getPixelWriter().setArgb(x, y, gray);
            }
        }

        return (Image)grayImage;
    }
}
