package kepnezegeto.megjelenites;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class KezeloFeluletController {

    @FXML
    protected TextField eleresiUt;



    @FXML
    protected void valami() {
        System.out.println("hello");
    }

    @FXML
    protected void fajlMegnyit(MouseEvent event) throws IOException {

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene ujAblak = new Scene( new FXMLLoader(KezeloFelulet.class.getResource("kepszerkesztoScene.fxml")).load(), 800, 600);
        stage.setScene(ujAblak);
        /*
        if(!eleresiUt.getText().isBlank()) {
            Image megnyitottKep = (new Jpg(eleresiUt.getText()).createImage());
            megnyitottKep = Grayscale.alkalmazFilter(megnyitottKep);
            kepMegjelenit.setImage(megnyitottKep);

        }
*/
    }


}