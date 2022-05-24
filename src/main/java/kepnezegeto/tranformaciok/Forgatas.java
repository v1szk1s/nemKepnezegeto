package kepnezegeto.tranformaciok;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import kepnezegeto.kepKezelo.Kep;


public class Forgatas implements Transzformacio {
    private boolean jobbra;

    @Override
    public String getNev() {
        return "Forgatás";
    }

    @Override
    public Image transzformal(Kep kep) {
        ImageView imageView = new ImageView(kep.getImage());
        imageView.setRotate((jobbra) ? 90 : -90);
        return imageView.snapshot(null, null);

        //ImageView iv = new ImageView(kep);
    }

    @Override
    public Node getUI(Kep kep, EventHandler<ActionEvent> eh) {
        Button jobbButton = new Button("Jobbra");
        Button balButton = new Button("Balra");
        Button megseButton = new Button("Mégse");

        VBox root = new VBox(10);
        GridPane.setMargin(megseButton, new Insets(10, 10, 10, 10));
        megseButton.setOnAction(eh);
        jobbButton.setOnAction(e -> {
            jobbra = true;
            kep.setImage(transzformal(kep));
            megseButton.fireEvent(e);
        });

        balButton.setOnAction(e -> {
            jobbra = false;
            kep.setImage(transzformal(kep));
            megseButton.fireEvent(e);
        });

        root.getChildren().addAll(jobbButton, balButton, megseButton);
        return root;
    }

}
