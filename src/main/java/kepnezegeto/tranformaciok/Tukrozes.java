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
import javafx.scene.transform.Rotate;
import kepnezegeto.kepKezelo.Kep;
import javafx.embed.swing.SwingFXUtils;


import java.io.File;

public class Tukrozes implements Transzformacio{
    private boolean tukrozX;
    @Override
    public String getNev() {
        return "Tükrözés";
    }

    @Override
    public Image transzformal(Kep kep) {
        ImageView imageView = new ImageView(kep.getImage());
        imageView.setTranslateZ(imageView.getBoundsInLocal().getWidth() / 2.0);
        imageView.setRotationAxis((tukrozX) ?  Rotate.Y_AXIS : Rotate.X_AXIS);
        imageView.setRotate(180);
        return imageView.snapshot(null, null);
        //TODO mentés
        // ImageIO.write(imagem, "png", new File(kep.getUrl()));
    }

    @Override
    public Node getUI(Kep kep, EventHandler<ActionEvent> eh) {
        Button xButton = new Button("Vízszintes tükrözés");
        Button yButton = new Button("Függőleges tükrözés");
        Button megseButton = new Button("Mégse");

        VBox root = new VBox();
        megseButton.setOnAction(eh);
        xButton.setOnAction(e -> {
            tukrozX = true;
            kep.setImage(transzformal(kep));
            megseButton.fireEvent(e);
        });

        yButton.setOnAction(e -> {
            tukrozX = false;
            kep.setImage(transzformal(kep));
            megseButton.fireEvent(e);
        });
        VBox.setMargin(xButton, new Insets(10, 10, 10, 10));
        VBox.setMargin(yButton, new Insets(10, 10, 10, 10));
        VBox.setMargin(megseButton, new Insets(10, 10, 10, 10));
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(xButton, yButton, megseButton);
        return root;
    }


}
