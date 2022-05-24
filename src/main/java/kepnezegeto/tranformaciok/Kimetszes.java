package kepnezegeto.tranformaciok;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import kepnezegeto.kepKezelo.Kep;


public class Kimetszes implements Transzformacio{
    private int x, y, szelesseg, magassag;
    @Override
    public String getNev() {
        return "Metszés";
    }

    @Override
    public Image transzformal(Kep kep) {
        return new WritableImage(kep.getImage().getPixelReader(), x, y, szelesseg, magassag);
    }

    @Override
    public Node getUI(Kep kep, EventHandler<ActionEvent> eh) {
        Label xLabel = new Label("X");
        Label yLabel = new Label("Y");
        Label szelessegLabel = new Label("Szélesség");
        Label magassagLabel = new Label("Magasság");
        TextField xTextField = new TextField("0");
        TextField yTextField = new TextField("0");
        TextField szelessegTextField = new TextField(String.valueOf((int)kep.getImage().getWidth()));
        TextField magassagTextField = new TextField(String.valueOf((int)kep.getImage().getHeight()));
        Button megseButton = new Button("Mégse");
        Button transzformalButton = new Button("Transzformál");
        GridPane root = new GridPane();

        megseButton.setOnAction(eh);
        transzformalButton.setOnAction(e->{
            try {
                x = Integer.parseInt(xTextField.getText());
                y = Integer.parseInt(yTextField.getText());
                szelesseg = Integer.parseInt(szelessegTextField.getText());
                magassag = Integer.parseInt(magassagTextField.getText());

                kep.setImage(transzformal(kep));
                megseButton.fireEvent(e);
            }catch (Exception ex){
                //TODO lekezel
                System.out.println("hello");
            }
        });
        xTextField.setOnKeyTyped(e->{
            try{
                int xValue = Integer.parseInt(xTextField.getText()), szelessegValue = Integer.parseInt(szelessegTextField.getText());
                if( xValue < 0 || xValue >= kep.getImage().getWidth()){
                    xTextField.setText("0");
                }
                if( xValue + szelessegValue > kep.getImage().getWidth()){
                    szelessegTextField.setText("0");
                }

            }catch (Exception ex){
                xTextField.setText("");
            }
        });

        yTextField.setOnKeyTyped(e->{
            try{

                int yValue = Integer.parseInt(yTextField.getText()), magassagValue = Integer.parseInt(magassagTextField.getText());
                if( yValue < 0 || yValue >= kep.getImage().getHeight()){
                    yTextField.setText("0");
                }
                if( yValue + magassagValue > kep.getImage().getWidth()){
                    magassagTextField.setText("0");
                }
            }catch (Exception ex){
                yTextField.setText("");
            }
        });
        szelessegTextField.setOnKeyTyped(e -> {
            try{
                int xValue = Integer.parseInt(xTextField.getText()), szelessegValue = Integer.parseInt(szelessegTextField.getText());
                if( szelessegValue < 0 || szelessegValue > kep.getImage().getWidth()){
                    szelessegTextField.setText("0");
                }
                if( xValue + szelessegValue > kep.getImage().getWidth()){
                    szelessegTextField.setText("0");
                }

            }catch (Exception ex){
                szelessegTextField.setText("");
            }
        });

        magassagTextField.setOnKeyTyped(e->{
            try{

                int yValue = Integer.parseInt(yTextField.getText()), magassagValue = Integer.parseInt(magassagTextField.getText());
                if( magassagValue < 0 || magassagValue >= kep.getImage().getHeight()){
                    magassagTextField.setText("0");
                }
                if( yValue + magassagValue > kep.getImage().getWidth()){
                    magassagTextField.setText("0");
                }
            }catch (Exception ex){
                magassagTextField.setText("");
            }
        });


        root.add(xLabel,0,0);
        root.add(xTextField, 1, 0);
        root.add(yLabel, 0, 1);
        root.add(yTextField, 1, 1);
        root.add(szelessegLabel, 0, 2);
        root.add(szelessegTextField, 1, 2);
        root.add(magassagLabel, 0,3);
        root.add(magassagTextField,1 ,3);
        root.add(megseButton, 0,4);
        root.add(transzformalButton, 1, 4);
        return root;
    }

}
