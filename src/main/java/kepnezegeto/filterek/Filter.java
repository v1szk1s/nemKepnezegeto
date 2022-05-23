package kepnezegeto.filterek;

import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
//TODO filterek checkbox button helyett
public abstract class Filter {
    public Image filter(Image kep){
        int szelesseg = (int) kep.getWidth(), magassag = (int) kep.getHeight();
        WritableImage ujKep = new WritableImage(szelesseg, magassag);
        for (int y = 0; y < magassag; y++) {
            for (int x = 0; x < szelesseg; x++) {
                int pixel = kep.getPixelReader().getArgb(x, y);
                int alpha = ((pixel >> 24) & 0xff);
                int red = ((pixel >> 16) & 0xff);
                int green = ((pixel >> 8) & 0xff);
                int blue = (pixel & 0xff);

                pixel = this.pixelManipulacio(alpha, red, green, blue);
                ujKep.getPixelWriter().setArgb(x, y, pixel);

            }
        }
        return ujKep;
    }

    public abstract String getNev();
    protected abstract int pixelManipulacio(int alpha, int red, int green, int blue);
}
