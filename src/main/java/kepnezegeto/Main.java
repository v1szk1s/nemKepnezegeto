package kepnezegeto;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kepnezegeto.kepKezelo.Kepkezelo;
import kepnezegeto.kepek.*;
import kepnezegeto.tranformaciok.Forgatas;
import kepnezegeto.tranformaciok.Transzformacio;
import kepnezegeto.tranformaciok.Tukrozes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.*;
import javafx.embed.swing.SwingFXUtils;


public class Main extends Application {
    private Stage window;
    private StackPane root;
    private BorderPane bg;
    private Kepkezelo kepkezelo = new Kepkezelo();
    private int imageIndex = -1;
    ImageView iv = new ImageView();
    


    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/kepnezegeto/view/kepnezegeto.fxml"));
        init(stage);


    }

    public void init(Stage window){


        iv.setPreserveRatio(true);
        iv.setFitWidth(1150);
        iv.setFitHeight(750);
        ArrayList<Transzformacio> transzformaciok = new ArrayList<>(); // itt lehetne megcsinalni azt, hogy kesobb be lehessen importalni
        transzformaciok.add(new Forgatas());
        transzformaciok.add(new Tukrozes());
        
        root = new StackPane();
        
        bg = new BorderPane();

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Fájl");
        MenuItem openMenuItem = new MenuItem("Megnyitás");
        MenuItem saveMenuItem = new MenuItem("Mentés");
        fileMenu.getItems().addAll(openMenuItem, saveMenuItem);

        Menu transzMenu = new Menu("Szerkeszt");
        for(var t:transzformaciok){
            transzMenu.getItems().add(new MenuItem(t.getNev()));
        }
        menuBar.getMenus().addAll(fileMenu, transzMenu);
        bg.setTop(menuBar);

        Text text = new Text("Fájl menüpontban Megnyitás opcióval tudsz képet megnyitni...");
        bg.setCenter(text);
        root.getChildren().add(bg);

        Scene scene = new Scene(root, 1200, 800);
        window.setTitle("Képnézegető");
        window.setResizable(false);
        window.setScene(scene);
        window.show();

        openMenuItem.setOnAction(e -> openFile());
        saveMenuItem.setOnAction(e -> saveFile());

        for (var item:transzMenu.getItems()){
            for(var transz:transzformaciok){
                if(item.getText().equals(transz.getNev())){
                    item.setOnAction(e -> {
                        Image currImage = kepkezelo.getKepek().get(imageIndex);
                        String path = currImage.getUrl();
                        kepkezelo.getKepek().set(imageIndex,transz.transzformal(currImage));
                        iv.setImage(currImage);
                        bg.setCenter(iv);
                    });
                }
            }

        }

    }

    public void openFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Képek megnyitása");
        File kep = fileChooser.showOpenDialog(window);

        if(kep != null){
            kepkezelo.getKepek().add(new Image(kep.toURI().toString()));
            iv.setImage(kepkezelo.getKepek().get(++imageIndex));


            bg.setCenter(iv);

        }
    }

    public void saveFile(){
        if (kepkezelo.getKepek().size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nincs kép!");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Mentés");
        File outputFile = fileChooser.showSaveDialog(window);

        //BufferedImage bImage = SwingFXUtils.fromFXImage(iv.getImage(), null);
        if(outputFile != null){
            //try {
                //ImageIO.write(bImage, "png", ujFile);
            //}catch (IOException e){}

            kepkezelo.save(imageIndex, outputFile);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}