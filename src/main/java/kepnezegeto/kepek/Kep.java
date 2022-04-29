package kepnezegeto.kepek;



import javafx.scene.image.Image;
import kepnezegeto.filterek.Filter;
import kepnezegeto.tranformaciok.Transzformalas;


import java.util.ArrayList;

public abstract class Kep{
    protected Image feldolgozottKep;
    Kep(String eleresiUt){
        feldolgozottKep = parse(eleresiUt);
    }
    private Image parse(String eleresiUt){
        return new Image(eleresiUt);
    }
    public Image createImage(){
        return feldolgozottKep;
    }

}
