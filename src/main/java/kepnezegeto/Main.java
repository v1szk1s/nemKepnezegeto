package kepnezegeto;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kepnezegeto.filterek.Grayscale;
import kepnezegeto.kepKezelo.Kepkezelo;
import kepnezegeto.kepek.Jpg;
import kepnezegeto.tranformaciok.Forgatas;
import kepnezegeto.tranformaciok.Transzformacio;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.*;
import javafx.embed.swing.SwingFXUtils;

public class Main extends Application {
    private Stage window;
    private BorderPane root;
    private ObservableList<Image> kepek = FXCollections.<Image>observableArrayList();
    private int imageIndex = 0;
    ImageView imageView;
    private Text text = new Text("text");


    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/kepnezegeto/view/kepnezegeto.fxml"));
        init(stage);

        kepek.addListener(new ListChangeListener<Image>() {
            @Override
            public void onChanged(Change<? extends Image> change) {
                if(kepek.size() > 0) {
                    imageView = new ImageView(kepek.get(kepek.size()-1));
                    root.setCenter(imageView);
                }
            }
        });

    }

    public void init(Stage window){
        ArrayList<Transzformacio> transzformaciok = new ArrayList<>();
        transzformaciok.add(new Forgatas());


        root = new BorderPane();
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

        root.setTop(menuBar);
        StringBuilder builder = new StringBuilder();
        root.setCenter(text);
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

                }
            }

        }

    }

    public void openFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Képek megnyitása");
        File kep = fileChooser.showOpenDialog(window);
        if(kep != null){
            kepek.add(new Image(kep.toURI().toString(), 600, 0, true, false));
        }
    }

    public void saveFile(){
        if (imageView == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nincs kép!");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Mentés");
        File outputFile = fileChooser.showSaveDialog(window);
        File ujFile = new File(outputFile.getAbsolutePath() + ".png");
        BufferedImage bImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);
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