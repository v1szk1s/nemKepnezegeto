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
import kepnezegeto.kepek.Kep;
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
    private ArrayList<Image> kepek = new ArrayList<>();
    private int imageIndex = 0;
    ImageView iv;
    


    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/kepnezegeto/view/kepnezegeto.fxml"));
        init(stage);


    }

    public void init(Stage window){
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

        Scene scene = new Scene(root, 800, 600);
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
                        kepek.set(imageIndex-1,transz.transzformal(kepek.get(imageIndex-1)));
                        iv = new ImageView(kepek.get(imageIndex-1));
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
            kepek.add(new Image(kep.toURI().toString()));
            iv = new ImageView(kepek.get(imageIndex));
            bg.setCenter(iv);
            imageIndex++;
        }
    }

    public void saveFile(){
        if (kepek.size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nincs kép!");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Mentés");
        File outputFile = fileChooser.showSaveDialog(window);
        File ujFile = new File(outputFile.getAbsolutePath() + ".png");
        BufferedImage bImage = SwingFXUtils.fromFXImage(iv.getImage(), null);
        if(outputFile != null){
            try {
                ImageIO.write(bImage, "png", ujFile);
            }catch (IOException e){}
        }
    }

    public static void main(String[] args) {
        launch();
    }
}