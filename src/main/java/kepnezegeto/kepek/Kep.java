package kepnezegeto.kepek;


import kepnezegeto.filterek.Filter;
import kepnezegeto.tranformaciok.Transzformalas;

import java.util.ArrayList;

public abstract class Kep {
    private ArrayList<Transzformalas> transzformalasok;
    private ArrayList<Filter> filterek;
    public abstract Kep parse();


}
