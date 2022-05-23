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
import kepnezegeto.kepKezelo.Kep;
import kepnezegeto.kepKezelo.Kepkezelo;

import kepnezegeto.tranformaciok.Forgatas;
import kepnezegeto.tranformaciok.Transzformacio;
import kepnezegeto.tranformaciok.Tukrozes;

import kepnezegeto.filterek.*;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import javax.imageio.*;
import javafx.embed.swing.SwingFXUtils;


public class Main extends Application {
    private Stage window;
    private StackPane root;
    private BorderPane bg;
    private Kepkezelo kepkezelo = new Kepkezelo();
    private int imageIndex = -1;
    private ImageView iv = new ImageView();
    private Scene scene;
    private MenuBar menuBar;
    private Menu fileMenu, transzMenu, filterMenu;
    private MenuItem openMenuItem, saveMenuItem, undoMenuItem;

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

        ArrayList<Filter> filterek = new ArrayList<>();
        filterek.add(new Grayscale());
        filterek.add(new Negativ());
        
        root = new StackPane();
        
        bg = new BorderPane();

        menuBar = new MenuBar();
        fileMenu = new Menu("Fájl");
        openMenuItem = new MenuItem("Megnyitás");
        saveMenuItem = new MenuItem("Mentés");
        undoMenuItem = new MenuItem("Visszavonás");
        undoMenuItem.setDisable(true);
        fileMenu.getItems().addAll(openMenuItem, saveMenuItem);

        transzMenu = new Menu("Szerkeszt");
        for(var t:transzformaciok){
            transzMenu.getItems().add(new MenuItem(t.getNev()));
        }
        transzMenu.getItems().add(undoMenuItem);

        filterMenu = new Menu("Filterek");
        for(var t:filterek){
            filterMenu.getItems().add(new MenuItem(t.getNev()));
        }


        menuBar.getMenus().addAll(fileMenu, transzMenu, filterMenu);
        bg.setTop(menuBar);

        Text text = new Text("Fájl menüpontban Megnyitás opcióval tudsz képet megnyitni...");
        bg.setCenter(text);
        root.getChildren().add(bg);

        scene = new Scene(root, 1200, 800);
        window.setTitle("Képnézegető");
        window.setResizable(false);
        window.setScene(scene);
        window.show();

        openMenuItem.setOnAction(e -> openFile());
        saveMenuItem.setOnAction(e -> saveFile());
        undoMenuItem.setOnAction(e -> undoAction());
        for (var item:transzMenu.getItems()){
            for(var transz:transzformaciok){
                if(item.getText().equals(transz.getNev())){
                    item.setOnAction(e -> {
                        Stack<Image> history = kepkezelo.getKepek().get(imageIndex).getHistory();
                        if(history.size() < 10) {
                            history.push(kepkezelo.getKep(imageIndex));
                            undoMenuItem.setDisable(false);
                        }
                        kepkezelo.set(imageIndex, transz.transzformal(kepkezelo.getKep(imageIndex)));
                        iv.setImage(kepkezelo.getKep(imageIndex));
                        bg.setCenter(iv);
                    });
                }
            }
        }

        for (var item:filterMenu.getItems()){
            for(var filter:filterek){
                if(item.getText().equals(filter.getNev())){
                    item.setOnAction(e -> {
                        Stack<Image> history = kepkezelo.getKepek().get(imageIndex).getHistory();
                        if(history.size() < 10) {
                            history.push(kepkezelo.getKep(imageIndex));
                            undoMenuItem.setDisable(false);
                        }
                        kepkezelo.set(imageIndex, filter.filter(kepkezelo.getKep(imageIndex)));
                        iv.setImage(kepkezelo.getKep(imageIndex));
                        bg.setCenter(iv);
                    });
                }
            }
        }

    }

    private void undoAction() {
        kepkezelo.set(imageIndex, kepkezelo.getKepek().get(imageIndex).getHistory().pop());
        iv.setImage(kepkezelo.getKep(imageIndex));
        bg.setCenter(iv);
        if(kepkezelo.getKepek().get(imageIndex).getHistory().size() <= 0){
            undoMenuItem.setDisable(true);
        }
    }

    public void openFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Képek megnyitása");
        File kep = fileChooser.showOpenDialog(window);

        if(kep != null){
            kepkezelo.add(new Image(kep.toURI().toString()));
            imageIndex++;
            iv.setImage(kepkezelo.getKep(imageIndex));


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