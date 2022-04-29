package kepnezegeto.kepek;


import javafx.scene.image.Image;
import kepnezegeto.filterek.Filter;
import kepnezegeto.tranformaciok.Transzformalas;


import java.util.ArrayList;

public abstract class Kep extends Image{
    private ArrayList<Transzformalas> transzformalasok;
    private ArrayList<Filter> filterek;

    public Kep(String s) {
        super(s);
    }

    public Kep(String s, boolean b) {
        super(s, b);
    }

    public Kep(String s, double v, double v1, boolean b, boolean b1) {
        super(s, v, v1, b, b1);
    }

    public Kep(String s, double v, double v1, boolean b, boolean b1, boolean b2) {
        super(s, v, v1, b, b1, b2);
    }


    public abstract Kep parse();

}
